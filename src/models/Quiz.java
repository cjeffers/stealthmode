package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletRequest;

/**
 * Quiz superclass
 * Stores:
 * name
 * description
 * indicator - show in multiple pages or not
 * indicator - whether quiz is timed or not
 * date the quiz was made
 *
 */
public class Quiz extends AbstractModel{

	/**
	 * Name of the MySQL table that holds the quizzes
	 */
	public static final String QUIZ_TABLENAME = "quizzes";

	private static final String ID_COLNAME = "id";
	private static final String NAME_COLNAME = "name";
	private static final String DESCRIPTION_COLNAME = "description";
	private static final String TIMED_COLNAME = "timed";
	private static final String MULTIPLEPAGES_COLNAME = "multiple_pages";
	private static final String DATE_COLNAME = "creation_time";
	private static final String CREATOR_ID_COLNAME = "creator_id";


	/**
	 * Constructs a quiz from an AbstractModel
	 * Assumes it is in the database
	 * @param AbstractModel
	 */
	public Quiz(AbstractModel am) {
		super(QUIZ_TABLENAME, am.getMap(), true);
	}

	/**
	 * Constructs a new quiz
	 * @param givenName name of quiz
	 * @param givenTimed whether the quiz is timed or not
	 * @param givenMultiplePages whether the quiz consists of multiple pages
	 * @param givenDateMade the date the quiz was made
	 */
	public Quiz(String theName, String theDescription, boolean theTimed, boolean theMultiplePages, long theDateMade, int creatorID){
		super(QUIZ_TABLENAME);
		setName(theName);
		setDescription(theDescription);
		setTimed(theTimed);
		setMultiplePages(theMultiplePages);
		setDateMade(theDateMade);
        setCreatorID(creatorID);
	}


	// Basic data getters

	/**
	 * @return id
	 */
	public Integer getID() {
		Object id = getValue(ID_COLNAME);
        if (id != null) return (Integer) id;
        return null;
	}

    /**
     * @return creator_id
     */
    public Integer getCreatorID() {
        return (Integer) getValue(CREATOR_ID_COLNAME);
    }

	/**
	 * @return quiz name
	 */
	public String getName() {
		return (String) getValue(NAME_COLNAME);
	}

	/**
	 * @return quiz description
	 */
	public String getDescription() {
		return (String) getValue(DESCRIPTION_COLNAME);
	}

	/**
	 * @return whether or not the quiz is timed
	 */
	public boolean isTimed() {
        if (((Integer)getValue(TIMED_COLNAME)) == 0) return false;
        return true;
	}

	/**
	 * @return whether or not to display on multiple pages
	 */
	public boolean hasMultiplePages() {
        //if (((Integer)getValue(MULTIPLEPAGES_COLNAME)) == 0) return false;
        //return true;
        return (Boolean)getValue(MULTIPLEPAGES_COLNAME);
	}

	/**
	 * @return the date made as a long
	 */
	public long getDateMade() {
		return (Long) getValue(DATE_COLNAME);
	}

	/**
	 * Gets the questions associated with this quiz
	 * Returns null if the id is not yet set
	 * @return list of questions
	 */
	public List<Question> getQuestions() {
		Integer id = getID();
		if (id != null) {
			return Question.findByQuizID(id);
		}
		return null;
	}


	// Basic data setters

	/**
	 * @param name
	 */
	public void setName(String name) {
		setValue(NAME_COLNAME, name);
	}

    /**
     * @param creatorID
     */
    public void setCreatorID(int creatorID) {
        setValue(CREATOR_ID_COLNAME, creatorID);
    }

	/**
	 * @param description
	 */
	public void setDescription(String desc) {
		setValue(DESCRIPTION_COLNAME, desc);
	}

	/**
	 * @param whether or not the quiz is timed
	 */
	public void setTimed(boolean timed) {
        if (timed) {
            setValue(TIMED_COLNAME, 1);
        } else {
            setValue(TIMED_COLNAME, 0);
        }
	}

	/**
	 * @param whether or not to display the quiz on multiple pages
	 */
	public void setMultiplePages(boolean multPages) {
        if (multPages) {
            setValue(MULTIPLEPAGES_COLNAME, 1);
        } else {
            setValue(MULTIPLEPAGES_COLNAME, 0);
        }
	}

	/**
	 * @param the date the quiz was made
	 */
	public void setDateMade(long date) {
		setValue(DATE_COLNAME, date);
	}

	/**
	 * Saves the quiz in the database
	 */
	public void commit() {
	//save();
	}

	public static int getTotalQuizzesTaken(){
		return Result.findAll().size();
	}
	
	public void clearHistory(){
		List<Result> toDelete = Result.findByQuiz(getID());
		for (int i = 0; i < toDelete.size(); i++){
			toDelete.get(i).delete();
		}
	}
	
	public static void clearHistoryByID(int ID){
		Quiz toDeleteHistory = Quiz.findByID(ID);
		toDeleteHistory.clearHistory();
	}
	    
	    public static void deleteQuiz(int ID){
	    	Quiz toDelete = Quiz.findByID(ID);
	    	toDelete.delete();
	    }

	/**
	 * Returns a quiz with the given id
	 * Returns null if it doesn't exist
	 * @param id
	 * @return quiz
	 */
	public static Quiz findByID(int id) {
		AbstractModel am = AbstractModel.getByID(QUIZ_TABLENAME, id);
        if (am != null) return new Quiz(am);
        return null;
	}
	

	/**
	 * Given a question, find the quiz associated with it
	 * Returns null if the question does not exist
	 * @param question id
	 * @return quiz
	 */
	public static Quiz findByQuestionID(int id) {
		Question question = (Question) AbstractModel.getByID(Question.QUESTION_TABLENAME, id);
		if (!question.equals(null)) {
			return ((Quiz) Quiz.findByID(question.getQuizID()));
		}
		return null;
	}

	/**
	 * Returns all quizzes made on the certain date
	 * Returns an empty list if none match the date
	 * @param date
	 * @return list of quizzes
	 */
	public static List<Quiz> findByDate(long date) {
		List<AbstractModel> ams = AbstractModel.getByValue(QUIZ_TABLENAME, DATE_COLNAME, date);
		return convertAMListToQuizzes(ams);
	}

	
	/**
	 * Returns all quizzes made by a certain user
	 * Returns an empty list if none match the date
	 * @param user's id
	 * @return list of quizzes
	 */
	public static List<Quiz> findByCreator(int id) {
		List<AbstractModel> ams = AbstractModel.getByValue(QUIZ_TABLENAME, CREATOR_ID_COLNAME, id);
		return convertAMListToQuizzes(ams);
	}
	
    public static List<Quiz> findAll() {
        List<AbstractModel> ams = AbstractModel.getAll(QUIZ_TABLENAME);
        return convertAMListToQuizzes(ams);
    }

	/**
	 * Converts a list of AbstractModels to a list of Quizzes
	 */
	private static List<Quiz> convertAMListToQuizzes(List<AbstractModel> ams) {
		List<Quiz> quizzes = new ArrayList<Quiz>(ams.size());
        for (AbstractModel am : ams) {
            quizzes.add(new Quiz(am));
        }
        return quizzes;
	}

	/**
	 * Given a ServletRequest, checks the score
	 * of the quiz
	 */
	public int checkScore(ServletRequest request) {
		int score = 0;

		List<Question> questions = getQuestions();
		for (Question question : questions) {
			if (question.checkAnswer(request)) score++;
		}

		return score;
	}

	/**
	 * Overrides toString()
	 * Format:
	 * id OR "not saved"
	 * name - date made
	 * # of questions
	 * description
	 * timed - true/false
	 * multiple pages - true/false
	 */
	@Override
	public String toString() {
		String str;
		Integer id = getID();
		if (id.equals(null)) {
			str = "not saved";
		} else {
			str = id.toString();
		}
		str += "\n" + getName() + " - " + getDateMade();
		str += "\n" + "# of questions - " + getQuestions().size();
		str += "\n" + getDescription();
		str += "\n" + "timed - " + isTimed();
		str += "\n" + "multiple pages = " + hasMultiplePages();
		return str;
	}


    static final Comparator<Result> TIME_SORT =
            new Comparator<Result>() {
   	 		public int compare(Result e1, Result e2) {
   	 		return (int) (e2.getTakenAt() - e1.getTakenAt());
   	 		}
   };

   	/**
   	 * Returns all results of a quiz, sorted by time taken last (latest results first in list).
   	 * @return a sorted list of results
   	 */

   	public List<Result> getRecentResults(){
   		List<Result> result = Result.findByQuiz(getID());
   		Collections.sort(result, TIME_SORT);
   		return result;
   	}

 static final Comparator<Result> SCORE_SORT =
         new Comparator<Result>() {
	 		public int compare(Result e1, Result e2) {
	 			if (e1.getScore() == e2.getScore()) return (int) (e1.getDuration() - e2.getDuration());
	 		return e2.getScore() - e1.getScore();
	 		}
};

	/**
	 * Returns all results of a quiz, sorted by score (highest scores first).
	 * @return a sorted list of results
	 */

	public List<Result> getScores(){
		List<Result> result = Result.findByQuiz(getID());
		Collections.sort(result, SCORE_SORT);
		return result;
	}
	

   	/**
   	 * Returns average score on a quiz.
   	 * @return the average score
   	 */

   	public double averageScore(){
   		List<Result> results = Result.findByQuiz(getID());
   		if (results.size() == 0) return 0;
   		double totalScore = 0;
   		for (int i = 0; i < results.size(); i++){
   			Result curr = results.get(i);
   			totalScore += curr.getScore();
   		}
   		return totalScore/results.size();
   	}

   	/**
   	 * Returns average time taken on a quiz.
   	 * @return the average time
   	 */

   	public int averageTime(){
   		List<Result> results = Result.findByQuiz(getID());
   		if (results.size() == 0) return 0;
   		int totalTime = 0;
   		for (int i = 0; i < results.size(); i++){
   			Result curr = results.get(i);
   			totalTime += curr.getDuration();
   		}
   		return totalTime/results.size();
   	}

   	private static int getPopularity(int quizID){
   		return Result.findByQuiz(quizID).size();
   	}

    static final Comparator<Quiz> POPULARITY_SORT =
            new Comparator<Quiz>() {
   	 		public int compare(Quiz e1, Quiz e2) {
   	 		return getPopularity(e2.getID()) - getPopularity(e1.getID());
   	 		}
   };

   
   /**
    * Shows all the quizzes, sorted by most popular
    * @return a list of quizzes
    */
   	public static List<Quiz> getTopQuizzes(){
   		List<Quiz> quizzes = findAll();
   		Collections.sort(quizzes, POPULARITY_SORT);
   		return quizzes;
   	}

    static final Comparator<Quiz> CREATION_TIME_SORT =
            new Comparator<Quiz>() {
   	 		public int compare(Quiz e1, Quiz e2) {
   	 		return (int) (e2.getDateMade() - e1.getDateMade());
   	 		}
   };
   
   
   /**
    * Shows all the quizzes made, in order sorted by time made
    * @return a sorted list of quizzes
    */
   	public static List<Quiz> recentlyCreatedQuizzes(){
   		List<Quiz> quizzes = findAll();
   		Collections.sort(quizzes, CREATION_TIME_SORT);
   		return quizzes;
   	}
}
