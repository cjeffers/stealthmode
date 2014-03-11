package models;

public abstract class AbstractQuestion extends Question {
	
	// constructors
	public AbstractQuestion(AbstractModel am) {
		super(am);
	}
	
	/**
     * Creates new Question from scratch.
     */
    public AbstractQuestion(int quizID, String type, String prompt) {
        super(quizID, type, prompt);
    }

    /**
     * Creates a new Question from scratch without a prompt.
     */
    public AbstractQuestion(int quizID, String type) {
        super(quizID, type);
    }
	
	/**
	 * @return the type of the question
	 * in human-readable format
	 */
	public abstract String getHumanText();

	/**
	 * @return the default prompt for the
	 * question
	 */
	public abstract String getDefaultPrompt();
	
	
}
