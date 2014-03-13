package models;

import java.util.*;

import javax.servlet.ServletRequest;


/**
 * Question superclass, for use in Quizzes.
 * Stores a prompt, the id of the quiz it belongs to, and its type
 * (as a string) as explicitly named columns. Besides that, there are
 * 10 general-use content text fields which can be used by subclasses
 * for whatever they need.
 *
 * As far as database searching, supports findByQuizID, findByID, and
 * findByType, as other columns don't need to be searchable.
 */
public class Question extends AbstractModel {

    /**
     * Name of the MySQL table that holds questions
     */
    public static final String QUESTION_TABLENAME = "questions";

    private static final String ID_COLNAME = "id";
    private static final String QUIZID_COLNAME = "quiz_id";
    private static final String TYPE_COLNAME = "q_type";
    private static final String PROMPT_COLNAME = "prompt";
    private static final String CONTENT_COLNAME_START = "content";

    /**
     * Creates Question from AbstractModel.
     * Assumes is in database already.
     */
    public Question(AbstractModel am) {
        super(QUESTION_TABLENAME, am.getMap(), true);
    }

    /**
     * Creates new Question from scratch.
     */
    public Question(int quizID, String type, String prompt) {
        super(QUESTION_TABLENAME);
        setValue(QUIZID_COLNAME, quizID);
        setValue(TYPE_COLNAME, type);
        setPrompt(prompt);
    }

    /**
     * Creates a new Question from scratch without a prompt.
     */
    public Question(int quizID, String type) {
        super(QUESTION_TABLENAME);
        setValue(QUIZID_COLNAME, quizID);
        setValue(TYPE_COLNAME, type);
    }


    /*
     * Helper to convert a list of AbstractModels into a list of Questions.
     */
    private static List<Question> convertAMListToQuestions(List<AbstractModel> ams) {
        List<Question> questions = new ArrayList<Question>(ams.size());
        for (AbstractModel am : ams) {
            questions.add(new Question(am));
        }
        return questions;
    }

    /* database methods */
    /**
     * Return a list of questions belonging to the specified quiz.
     */
    public static List<Question> findByQuizID(int quizID) {
        List<AbstractModel> ams = AbstractModel.getByValue(QUESTION_TABLENAME, QUIZID_COLNAME, quizID);
        return convertAMListToQuestions(ams);
    }

    /**
     * Return all questions with the specified type.
     */
    public static List<Question> findByType(String type) {
        List<AbstractModel> ams = AbstractModel.getByValue(QUESTION_TABLENAME, TYPE_COLNAME, (Object)type);
        return convertAMListToQuestions(ams);
    }

    /**
     * Get a question with the given ID, null if it doesn't exist.
     */
    public static Question findByID(int id) {
        AbstractModel am = AbstractModel.getByID(QUESTION_TABLENAME, id);
        if (am != null) return new Question(am);
        return null;
    }

    /* question info */

    /**
     * Get the id of the quiz this question belongs to.
     */
    public int getQuizID() {
        return (Integer) getValue(QUIZID_COLNAME);
    }

    public Integer getID() {
        Object id = getValue(ID_COLNAME);
        if (id != null) return (Integer) id;
        return null;
    }

    /**
     * Get a string with the question's type.
     */
    public String getType() {
        return (String) getValue(TYPE_COLNAME);
    }

    /**
     * Get and set the prompt for this question.
     */
    public String getPrompt() {
        return (String) getValue(PROMPT_COLNAME);
    }
    public void setPrompt(String newPrompt) {
        setValue(PROMPT_COLNAME, newPrompt);
    }


    /* question content getters and setters */

    /**
     * Get and set the content strings (0-9) for this question.
     */
    public String getContent0() {
        return (String) getValue(CONTENT_COLNAME_START + "_0");
    }
    public void setContent0(String str) {
        setValue(CONTENT_COLNAME_START + "_0", str);
    }

    public String getContent1() {
        return (String) getValue(CONTENT_COLNAME_START + "_1");
    }
    public void setContent1(String str) {
        setValue(CONTENT_COLNAME_START + "_1", str);
    }

    public String getContent2() {
        return (String) getValue(CONTENT_COLNAME_START + "_2");
    }
    public void setContent2(String str) {
        setValue(CONTENT_COLNAME_START + "_2", str);
    }

    public String getContent3() {
        return (String) getValue(CONTENT_COLNAME_START + "_3");
    }
    public void setContent3(String str) {
        setValue(CONTENT_COLNAME_START + "_3", str);
    }

    public String getContent4() {
        return (String) getValue(CONTENT_COLNAME_START + "_4");
    }
    public void setContent4(String str) {
        setValue(CONTENT_COLNAME_START + "_4", str);
    }

    public String getContent5() {
        return (String) getValue(CONTENT_COLNAME_START + "_5");
    }
    public void setContent5(String str) {
        setValue(CONTENT_COLNAME_START + "_5", str);
    }

    public String getContent6() {
        return (String) getValue(CONTENT_COLNAME_START + "_6");
    }
    public void setContent6(String str) {
        setValue(CONTENT_COLNAME_START + "_6", str);
    }

    public String getContent7() {
        return (String) getValue(CONTENT_COLNAME_START + "_7");
    }
    public void setContent7(String str) {
        setValue(CONTENT_COLNAME_START + "_7", str);
    }

    public String getContent8() {
        return (String) getValue(CONTENT_COLNAME_START + "_8");
    }
    public void setContent8(String str) {
        setValue(CONTENT_COLNAME_START + "_8", str);
    }

    public String getContent9() {
        return (String) getValue(CONTENT_COLNAME_START + "_9");
    }
    public void setContent9(String str) {
        setValue(CONTENT_COLNAME_START + "_9", str);
    }
    
    /*
     * Default answer-handling methods.  Override these
     * if these implementations are incorrect for your
     * specific sub-class
     */
    
    /**
     * Returns the answer as a String
     * Assumes the answer is in the first
     * general-purpose column
     * @return answer
     */
    public String getAnswer() {
    	return getContent0();
    }
    
    /**
     * Checks the answer against a request
     * Assumes the answer is in the first
     * general-purpose column and that the
     * parameter is called "answer"
     * @return boolean
     */
    public boolean checkAnswer(ServletRequest request) {
    	String parameter = "question_" + Integer.toString(getID()) + "_answer";
    	return (request.getParameter(parameter).toLowerCase().equals(getContent0().toLowerCase()));
    }
    
}
