package models.test;

import java.util.*;
import java.sql.*;

import static org.junit.Assert.*;
import models.AbstractModel;

import org.junit.*;

public class AbstractModelTest {

    static Connection con;

	@Before
	public void setUp() throws Exception {
        con = AbstractModel.getConnection();
	}

    @Test
    public void testGetConnection() throws Exception {
        con = AbstractModel.getConnection();
        assertFalse(con.isClosed());
    }

    @Test
    public void testGetByID() {
        AbstractModel am = AbstractModel.getByID("metropolises", 1);
        assertTrue(am.getValue("metropolis").equals("Mumbai"));
        am = AbstractModel.getByID("metropolises", 5);
        assertTrue((Long) am.getValue("population") == 5780000);
    }

    @Test
    public void testGetByValue() {
        // get many equal
        List<AbstractModel> NACities = AbstractModel.getByValue("metropolises", "continent", (Object) "North America");
        assertTrue(NACities.size() == 3);
        assertTrue(NACities.get(0).getValue("metropolis").equals("New York"));

        // comparator version
        List<AbstractModel> bigCities = AbstractModel.getByValue("metropolises", "population", 5000000, " > ");
        assertTrue(bigCities.size() == 5);
        assertTrue(bigCities.get(0).getValue("continent").equals("Asia"));
    }

    @Test
    public void testGetOneByValue() {
        // get equal
        AbstractModel london = AbstractModel.getOneByValue("metropolises", "metropolis", (Object)"london");
        assertTrue((Long)london.getValue("population") == 8580000);

        // comparator
        AbstractModel smallest = AbstractModel.getOneByValue("metropolises", "population", 2000000, " < ");
        assertTrue(smallest.getValue("metropolis").equals("Rostov-on-Don"));
    }

    @Test
    public void testGetWhere() {
        List<AbstractModel> europeanBigCities = AbstractModel.getWhere("continent='Europe' AND population > 5000000",
                "metropolises");
        assertTrue(europeanBigCities.size() == 1);
    }

    @Test
    public void testSetValue() {
        AbstractModel newYork = AbstractModel.getByID("metropolises", 3);
        assertTrue(newYork.getValue("metropolis").equals("New York"));
        newYork.setValue("metropolis", "Philadelphia");
        assertTrue(newYork.getValue("metropolis").equals("Philadelphia"));
    }

    @Test
    public void testSaveNew() {
        // save new instance creates thing
        assertTrue(AbstractModel.getOneByValue("metropolises", "metropolis", (Object) "Greeley") == null);
        AbstractModel greeley = new AbstractModel("metropolises");
        greeley.setValue("metropolis", "Greeley");
        greeley.setValue("continent", "North America");
        greeley.setValue("population", 100000);
        greeley.save();
        AbstractModel g = AbstractModel.getOneByValue("metropolises", "metropolis", (Object) "Greeley");
        assertTrue(g.getValue("metropolis").equals("Greeley"));
        g.delete();
    }

    @Test
    public void testSaveUpdate() {
        // save old things
        AbstractModel am = AbstractModel.getOneByValue("metropolises", "metropolis", (Object)"London");
        long oldPop = (Long) am.getValue("population");
        long newPop = 1000000000;
        assertTrue(oldPop == 8580000);
        am.setValue("population", newPop);
        am.save();

        AbstractModel london = AbstractModel.getOneByValue("metropolises", "metropolis", (Object)"London");
        assertTrue((Long)london.getValue("population") == newPop);
        london.setValue("population", oldPop);
        london.save();
    }

    @Test
    public void testDeleteNew() {
        List<AbstractModel> cities = AbstractModel.getAll("metropolises");
        assertTrue(cities.size() == 8);

        // delete new instance does nothing
        AbstractModel greeley = new AbstractModel("metropolises");
        greeley.setValue("metropolis", "Greeley");
        greeley.setValue("continent", "North America");
        greeley.setValue("population", 100000);
        greeley.delete();

        cities = AbstractModel.getAll("metropolises");
        assertTrue(cities.size() == 8);
    }

    @Test
    public void testDeleteOld() {
        // delete old instance removes from DB
        AbstractModel sf = AbstractModel.getOneByValue("metropolises", "metropolis", (Object)"San Francisco");
        sf.delete();

        AbstractModel dne = AbstractModel.getOneByValue("metropolises", "metropolis", (Object)"San Francisco");
        assertTrue(dne == null);

        sf.save();
    }

    @Test
    public void testGetAll() {
        // actually get all of them
        List<AbstractModel> cities = AbstractModel.getAll("metropolises");
        assertTrue(cities.size() == 8);
    }

	@AfterClass
	public static void tearDown() throws Exception {
        AbstractModel.closeConnection();
	}
}
