package models.test;

import java.util.*;
import java.sql.*;

import static org.junit.Assert.*;
import models.AbstractModel;

import org.junit.*;

public class AbstractModelTest {

    static Connection con;

	@BeforeClass
	public static void setUp() throws Exception {
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
        assertTrue((Long) am.getValue("population") == 2715000);
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
        AbstractModel newYork = AbstractModel.getByID("metropolises", 2);
        assertTrue(newYork.getValue("metropolis").equals("New York"));
        newYork.setValue("metropolis", "Philadelphia");
        assertTrue(newYork.getValue("metropolis").equals("Philadelphia"));
    }

    @Test
    public void testSave() {
        // save new instance creates thing
        // save old instance updates
    }

    @Test
    public void testDelete() {
        // delete new instance does nothing
        // delete old instance removes from DB
    }

    @Test
    public void testGetAll() {
        // actually get all of them
    }

	@AfterClass
	public static void tearDown() throws Exception {
        AbstractModel.closeConnection();
	}
}
