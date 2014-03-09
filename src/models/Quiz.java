package models;

import java.util.ArrayList;
import java.util.List;

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
	private static final String DATE_COLNAME = "date";
	
	
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
	public Quiz(String theName, String theDescription, boolean theTimed, boolean theMultiplePages, long theDateMade){
		super("quizzes");
		setName(theName);
		setDescription(theDescription);
		setTimed(theTimed);
		setMultiplePages(theMultiplePages);
		setDateMade(theDateMade);
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
		return (Boolean) getValue(TIMED_COLNAME);
		
	}
	
	/**
	 * @return whether or not to display on multiple pages
	 */
	public boolean hasMultiplePages() {
		return (Boolean) getValue(MULTIPLEPAGES_COLNAME);
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
	 * @param description
	 */
	public void setDescription(String desc) {
		setValue(DESCRIPTION_COLNAME, desc);
	}
	
	/**
	 * @param whether or not the quiz is timed
	 */
	public void setTimed(boolean timed) {
		setValue(TIMED_COLNAME, timed);
	}
	
	/**
	 * @param whether or not to display the quiz on multiple pages
	 */
	public void setMultiplePages(boolean multPages) {
		setValue(MULTIPLEPAGES_COLNAME, multPages);
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
	
	// Database searching static methods
	
	
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
	 * Converts a list of AbstractModels to a list of Quizzes
	 */
	private static List<Quiz> convertAMListToQuizzes(List<AbstractModel> ams) {
		List<Quiz> quizzes = new ArrayList<Quiz>(ams.size());
        for (AbstractModel am : ams) {
            quizzes.add(new Quiz(am));
        }
        return quizzes;
	}
}
