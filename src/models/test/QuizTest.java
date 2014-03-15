package models.test;

import models.Quiz;
import models.AbstractModel;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

public class QuizTest {

	Quiz q;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Quiz.getConnection();
	}
	
	@Before
	public void setUp() {
		q = new Quiz("test name", "test description", true, true, 100, 1);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
        Quiz.closeConnection();
	}
	
	//constructors
	@Test
	public void testBasicConstructor() {
		assertEquals(q.getName(), "test name");
		assertEquals(q.getDescription(), "test description");
		assertTrue(q.isRandom());
		assertTrue(q.hasMultiplePages());
		assertEquals(q.getDateMade(), 100);
	}
	
	@Test
	public void testAbstractModel() {
		AbstractModel am = new AbstractModel("quizzes");
		am.setValue("description", "hi there");
		am.setValue("name", "oh, hello");
		q = new Quiz(am);
		assertEquals(q.getDescription(), "hi there");
		assertEquals(q.getName(), "oh, hello");
	}
	
	//editing values
	@Test
	public void testEdits() {
		q.setName("new name");
		q.setDescription("new description");
		q.setRandom(false);
		q.setMultiplePages(false);
		q.setDateMade(200);
		
		assertEquals(q.getName(), "new name");
		assertEquals(q.getDescription(), "new description");
		assertFalse(q.isRandom());
		assertFalse(q.hasMultiplePages());
		assertEquals(q.getDateMade(), 200);
	}
}
