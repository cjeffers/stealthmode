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
    public void testGetByID() {
        AbstractModel am = AbstractModel.getByID("metropolises", 1);
        assertTrue(am.getValue("metropolis").equals("Mumbai"));
        am = AbstractModel.getByID("metropolises", 5);
        assertTrue((Long) am.getValue("population") == 2715000);
    }

    @Test
    public void testGetByValue() {
        List<AbstractModel> NACities = AbstractModel.getByValue("metropolises", "continent", (Object) "North America");
        assertTrue(NACities.size() == 3);
        assertTrue(NACities.get(0).getValue("metropolis").equals("New York"));
        List<AbstractModel> bigCities = AbstractModel.getByValue("metropolises", "population", 5000000, " > ");
        assertTrue(bigCities.size() == 5);
    }

	//@Test
	//public void testGetByValue() {
		//List<String> met = Metropolis.getByValue("Metropolis", "London");
        //assertTrue(met != null);
        //assertTrue(met.get(0).equals("London"));
	//}

    //@Test
    //public void testGetAll() {
        //List<List<String>> all = Metropolis.getAll();
        //assertTrue(all != null);
        //assertTrue(all.size() == 10);
        //assertTrue(all.get(0).get(0).equals("Mumbai"));
        //assertTrue(all.get(8).get(2).equals("12000"));
    //}

	@AfterClass
	public static void tearDown() throws Exception {
        AbstractModel.closeConnection();
	}
}
