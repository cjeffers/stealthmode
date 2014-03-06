package models;

import java.util.*;

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
        setValue("quiz_id", quizID);
        setValue("type", type);
        setPrompt(prompt);
    }

    /**
     * Creates a new Question from scratch without a prompt.
     */
    public Question(int quizID, String type) {
        super(QUESTION_TABLENAME);
        setValue("quiz_id", quizID);
        setValue("type", type);
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
        List<AbstractModel> ams = AbstractModel.getByValue(QUESTION_TABLENAME, "quiz_id", quizID);
        return convertAMListToQuestions(ams);
    }

    /**
     * Return all questions with the specified type.
     */
    public static List<Question> findByType(String type) {
        List<AbstractModel> ams = AbstractModel.getByValue(QUESTION_TABLENAME, "type", (Object)type);
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
        return (Integer) getValue("quiz_id");
    }

    /**
     * Get a string with the question's type.
     */
    public String getType() {
        return (String) getValue("type");
    }

    /**
     * Get and set the prompt for this question.
     */
    public String getPrompt() {
        return (String) getValue("prompt");
    }
    public void setPrompt(String newPrompt) {
        setValue("prompt", newPrompt);
    }


    /* question content getters and setters */

    /**
     * Get and set the content strings (0-9) for this question.
     */
    public String getContent0() {
        return (String) getValue("content_0");
    }
    public void setContent0(String str) {
        setValue("content_0", str);
    }

    public String getContent1() {
        return (String) getValue("content_1");
    }
    public void setContent1(String str) {
        setValue("content_1", str);
    }

    public String getContent2() {
        return (String) getValue("content_2");
    }
    public void setContent2(String str) {
        setValue("content_2", str);
    }

    public String getContent3() {
        return (String) getValue("content_3");
    }
    public void setContent3(String str) {
        setValue("content_3", str);
    }

    public String getContent4() {
        return (String) getValue("content_4");
    }
    public void setContent4(String str) {
        setValue("content_4", str);
    }

    public String getContent5() {
        return (String) getValue("content_5");
    }
    public void setContent5(String str) {
        setValue("content_5", str);
    }

    public String getContent6() {
        return (String) getValue("content_6");
    }
    public void setContent6(String str) {
        setValue("content_6", str);
    }

    public String getContent7() {
        return (String) getValue("content_7");
    }
    public void setContent7(String str) {
        setValue("content_7", str);
    }

    public String getContent8() {
        return (String) getValue("content_8");
    }
    public void setContent8(String str) {
        setValue("content_8", str);
    }

    public String getContent9() {
        return (String) getValue("content_9");
    }
    public void setContent9(String str) {
        setValue("content_9", str);
    }
}
