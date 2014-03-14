package models;

import java.util.*;

public class Result extends AbstractModel {

	/**
	 * Name of the MySQL table that holds the quizzes
	 */
	public static final String RESULT_TABLENAME = "results";

	public static final String ID_COLNAME = "id";
	public static final String QUIZ_ID_COLNAME = "quiz_id";
	public static final String USER_ID_COLNAME = "user_id";
	public static final String SCORE_COLNAME = "score";
	public static final String NUM_QUESTIONS_COLNAME = "num_quest";
	public static final String TAKEN_COLNAME = "taken_at";
	public static final String DURATION_COLNAME = "duration";

	/**
	 * Creates a Result from a ResultSet
	 * @param theTableName
	 * @param theRS
	 */
	public Result(AbstractModel am) {
		super(RESULT_TABLENAME, am.getMap(), true);
	}



	/**
	 * Creates a Result from all given parameters
	 * @param quiz id
	 * @param user id
	 * @param score (as an int)
	 * @param number of questions
	 * @param time taken (long)
	 * @param duration (long)
	 */
	public Result(int quizID, int userID, int score, int numQuestions, long takenAt, long duration) {
		super(RESULT_TABLENAME);
		setQuizID(quizID);
		setUserID(userID);
		setScore(score);
		setNumQuestions(numQuestions);
		setTakenAt(takenAt);
		setDuration(duration);
	}

    // finders
    /**
     * Find all results for the given user.
     * @param userID the id of the user to get results for.
     * @return a list of results for the given user, not necessarily sorted.
     */
    public static List<Result> findByUser(int userID) {
        List<AbstractModel> ams = AbstractModel.getByValue(RESULT_TABLENAME, USER_ID_COLNAME, userID);
        return convertAMListToResults(ams);
    }

    /**
     * Find all results for the given quiz.
     * @param quizID the id of the quiz to get results for
     * @return a list of the results for the given quiz, not necessarily sorted.
     */
    public static List<Result> findByQuiz(int quizID) {
        List<AbstractModel> ams = AbstractModel.getByValue(RESULT_TABLENAME, QUIZ_ID_COLNAME, quizID);
        return convertAMListToResults(ams);
    }

    /**
     * Find all results for the given quiz and user.
     * @param quizID the quiz to get results for
     * @param userID the user to get results for
     * @return a list of all results which are the given user taking the given quiz.
     */
    public static List<Result> findByQuizAndUser(int quizID, int userID) {
        String whereQuery = QUIZ_ID_COLNAME + "=" + quizID + " AND " +
                            USER_ID_COLNAME + "=" + userID;
        List<AbstractModel> ams = AbstractModel.getWhere(whereQuery, RESULT_TABLENAME);
        return convertAMListToResults(ams);
    }

    public static Result findByID(int resultID) {
        return new Result(AbstractModel.getByID(RESULT_TABLENAME, resultID));
    }

	private static List<Result> convertAMListToResults(List<AbstractModel> ams) {
		List<Result> results = new ArrayList<Result>(ams.size());
        for (AbstractModel am : ams) {
            results.add(new Result(am));
        }
        return results;
	}

	// setters

	/**
	 * @param quiz id
	 */
	public void setQuizID(int quizID) {
		setValue(QUIZ_ID_COLNAME, quizID);
	}

	/**
	 * @param user id
	 */
	public void setUserID(int userID) {
		setValue(USER_ID_COLNAME, userID);
	}

	/**
	 * @param score
	 */
	public void setScore(int score) {
		setValue(SCORE_COLNAME, score);
	}

	/**
	 * @param number of questions
	 */
	public void setNumQuestions(int numQuestions) {
		setValue(NUM_QUESTIONS_COLNAME, numQuestions);
	}

	/**
	 * @param time taken at
	 */
	public void setTakenAt(long takenAt) {
		setValue(TAKEN_COLNAME, takenAt);
	}

	/**
	 * @param time taken to complete quiz
	 */
	public void setDuration(long duration) {
		setValue(DURATION_COLNAME, duration);
	}

	//getters

	/**
	 * @return quiz id
	 */
	public int getID() {
		return (Integer) getValue(ID_COLNAME);
	}

	/**
	 * @return id
	 */
	public int getQuizID() {
		return (Integer) getValue(QUIZ_ID_COLNAME);
	}

	/**
	 * @return user id
	 */
	public int getUserID() {
		return (Integer) getValue(USER_ID_COLNAME);
	}

	/**
	 * @return score
	 */
	public int getScore() {
		return (Integer) getValue(SCORE_COLNAME);
	}

	/**
	 * @return number of questions
	 */
	public int getNumQuestions() {
		return (Integer) getValue(NUM_QUESTIONS_COLNAME);
	}

	/**
	 * @return time taken at
	 */
	public long getTakenAt() {
		return (Long) getValue(TAKEN_COLNAME);
	}

	/**
	 * @return time taken to complete quiz
	 */
	public long getDuration() {
		return (Long) getValue(DURATION_COLNAME);
	}
}
