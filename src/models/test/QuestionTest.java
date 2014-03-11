package models.test;

import models.Question;
import models.AbstractModel;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

public class QuestionTest {

    Question q;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

    @Before
    public void setUp() {
        AbstractModel.getConnection();
        q = new Question(3, "basic", "Answer the question.");
        q.setValue("content_0", "Who's awesome?");
        q.setValue("content_1", "You're awesome!");
    }

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
        AbstractModel.closeConnection();
	}

    // constructors
	@Test
	public void testQuestionAbstractModel() {
        AbstractModel am = new AbstractModel("questions");
        am.setValue("q_type", "basic");
        am.setValue("prompt", "blah");
        am.setValue("quiz_id", 10);
        q = new Question(am);

        assertTrue(q.getType().equals("basic"));
        assertTrue(q.getQuizID() == 10);
        assertTrue(q.getPrompt().equals("blah"));
	}

	@Test
	public void testQuestionIntStringString() {
        q = new Question(1, "basic", "Answer the question.");
        assertTrue(q.getType().equals("basic"));
        assertTrue(q.getQuizID() == 1);
        assertTrue(q.getPrompt().equals("Answer the question."));
	}

	@Test
	public void testQuestionIntString() {
        q = new Question(1, "basic");
        assertTrue(q.getType().equals("basic"));
        assertTrue(q.getQuizID() == 1);
        assertTrue(q.getPrompt() == null);
    }

    // findBy*
	@Test
	public void testFindByQuizID() {
        List<Question> quiz1 = Question.findByQuizID(1);
        assertTrue(quiz1.size() == 2);
        quiz1 = Question.findByQuizID(-1);
        assertTrue(quiz1.size() == 0);
	}

	@Test
	public void testFindByType() {
        List<Question> basic = Question.findByType("basic");
        assertTrue(basic.size() == 4);
        basic = Question.findByType("NotAType");
        assertTrue(basic.size() == 0);
	}

	@Test
	public void testFindByID() {
        q = Question.findByID(1);
        assertTrue(q.getPrompt().equals("You don't know my life!"));
        q = Question.findByID(-1);
        assertTrue(q == null);
	}

    // getters and setters
	@Test
	public void testGetQuizID() {
        assertTrue(q.getQuizID() == 3);
	}

	@Test
	public void testGetType() {
        assertTrue(q.getType().equals("basic"));
	}

    @Test
    public void testGetID() {
        assertTrue(q.getID() == null); // hasn't been saved into DB
        q = new Question(Question.getOneByValue("questions", "prompt", (Object)"You don't know my life!"));
        assertTrue(q.getID() == 1);
    }

	@Test
	public void testGetPrompt() {
        assertTrue(q.getPrompt().equals("Answer the question."));
	}

	@Test
	public void testSetPrompt() {
        String newPrompt = "i'm the new prompt";
        assertFalse(q.getPrompt().equals(newPrompt));
        q.setPrompt(newPrompt);
        assertTrue(q.getPrompt().equals(newPrompt));
	}

	@Test
	public void testSetGetContent() {
        q.setContent0("dang0");
        q.setContent1("dang1");
        q.setContent2("dang2");
        q.setContent3("dang3");
        q.setContent4("dang4");
        q.setContent5("dang5");
        q.setContent6("dang6");
        q.setContent7("dang7");
        q.setContent8("dang8");
        q.setContent9("dang9");
        assertTrue(q.getContent0().equals("dang0"));
        assertTrue(q.getContent1().equals("dang1"));
        assertTrue(q.getContent2().equals("dang2"));
        assertTrue(q.getContent3().equals("dang3"));
        assertTrue(q.getContent4().equals("dang4"));
        assertTrue(q.getContent5().equals("dang5"));
        assertTrue(q.getContent6().equals("dang6"));
        assertTrue(q.getContent7().equals("dang7"));
        assertTrue(q.getContent8().equals("dang8"));
        assertTrue(q.getContent9().equals("dang9"));
	}


    @Test
    public void testSaveNew() {
        assertTrue(Question.findByID(9) == null);
        q.save();
        q = Question.findByID(9);
        assertTrue(q.getContent1().equals("You're awesome!"));
        q.delete();
    }

    @Test
    public void testSaveUpdate() {
        String newAns = "The Beatles, obvi.";
        q = Question.findByID(1);
        assertTrue(q.getContent1().equals("Led Zeppelin, duh."));
        q.setContent1(newAns);
        q.save();
        q = Question.findByID(1);
        assertTrue(q.getContent1().equals(newAns));
        q.setContent1("Led Zeppelin, duh.");
        q.save();
    }

    @Test
    public void testDelete() {
        List<Question> basic = Question.findByType("basic");
        assertTrue(basic.size() == 4);
        q = basic.get(0);
        q.delete();
        basic = Question.findByType("basic");
        assertTrue(basic.size() == 3);
        q.save();
    }

}
