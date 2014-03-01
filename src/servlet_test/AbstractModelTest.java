package servlet_test;

import java.util.*;
import static org.junit.Assert.*;

import org.junit.*;

public class AbstractModelTest {

	@BeforeClass
	public static void setUp() throws Exception {
        AbstractModel am = new AbstractModel("metropolises", AbstractModel.getConnection());
	}

	@Test
	public void testGetByValue() {
		List<String> met = Metropolis.getByValue("Metropolis", "London");
        assertTrue(met != null);
        assertTrue(met.get(0).equals("London"));
	}

    @Test
    public void testGetAll() {
        List<List<String>> all = Metropolis.getAll();
        assertTrue(all != null);
        assertTrue(all.size() == 10);
        assertTrue(all.get(0).get(0).equals("Mumbai"));
        assertTrue(all.get(8).get(2).equals("12000"));
    }

	@AfterClass
	public static void tearDown() throws Exception {
        AbstractModel.closeConnection();
	}
}
