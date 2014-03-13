package models.test;

import models.*;

import java.util.*;
import static org.junit.Assert.*;

import org.junit.*;

public class ResultTest {

    List<Result> results;

	@Before
	public void setUp() throws Exception {
        AbstractModel.getConnection();
        results = null;
	}


	@AfterClass
	public static void tearDownClass() throws Exception {
        AbstractModel.closeConnection();
	}

	@Test
	public void testFindByUser() {
        results = Result.findByUser(1);
        assertTrue(results.size() == 3);
        results = Result.findByUser(2);
        assertTrue(results.size() == 1);
	}

    @Test
    public void testFindByQuiz() {
        results = Result.findByQuiz(1);
        assertTrue(results.size() == 2);
        results = Result.findByQuiz(2);
        assertTrue(results.size() == 2);
    }

    @Test
    public void testFindByQuizAndUser() {
        results = Result.findByQuizAndUser(1, 1);
        assertTrue(results.size() == 2);
        results = Result.findByQuizAndUser(1, 2);
        assertTrue(results.size() == 0);
        results = Result.findByQuizAndUser(2, 2);
        assertTrue(results.size() == 1);
    }

}
