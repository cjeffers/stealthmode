package models.test;

import models.*;

import java.util.*;

import static org.junit.Assert.*;

import org.junit.*;

public class ResultsManipulationTest {

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
        List<Result> result = User.findByID(1).getScoresForQuiz(1);
        System.out.println("User 1's scores for quiz 1: ");
        for (int i = 0; i < result.size(); i++){
        	System.out.println(result.get(i).getScore());
        }
        List<Result> recentScores = User.findByID(1).getRecentResults();
        System.out.println("User 1's recent scores");
        for (int i = 0; i < recentScores.size(); i++){
        	System.out.println("QUIZ id: " + recentScores.get(i).getQuizID() + " And Time Made: " + recentScores.get(i).getTakenAt());
        }
        List<Quiz> mostPopularQuizzes = Quiz.getTopQuizzes();
        System.out.println("Most popular quizzes");
        for (int i = 0; i < mostPopularQuizzes.size(); i++){
        	System.out.println("Quiz name: " + mostPopularQuizzes.get(i).getName());
        }
        List<Result> topScores = Quiz.findByID(1).getScores();
        System.out.println("The users who have gotten the best scores for quiz 1");
        for (int i = 0; i < topScores.size(); i++){
        	System.out.println("Username: " + User.findByID(topScores.get(i).getUserID()).getUserName());
        }
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
