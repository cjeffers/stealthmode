package models;

import javax.servlet.ServletRequest;

public class PictureQuestion extends AbstractQuestion {
	
	/*
	 * Request Parameter Names:
	 */
	private static final String REQUEST_PROMPT = "question_prompt";
	private static final String REQUEST_ANSWER = "question_answer";
	private static final String REQUEST_URL = "question_url";
	
	// constants
	
	private final String DEFAULT_PROMPT = "What is this a picture of?";
	private final String HUMAN_READABLE = "Picture";
	public static final String TYPE = "picture";
	
	/*
	 * Get Question info
	 */
	
	/**
	 * Returns the picture's url
	 * @return String
	 */
	public String getURL() {
		return getContent1();
	}
	
	/*
	 * Constructors:
	 * AbstractModel is same as super
	 * No prompt - uses default prompt
	 */
	
	/**
	 * Creates Image Question from AbstractModel
	 */
	public PictureQuestion(AbstractModel am) {
		super(am);
	}
	
	/**
	 * Creates Image Question from:
	 * QuizID, question, prompt
	 */
	public PictureQuestion(int quizID, String type, String prompt) {
		super(quizID, type, prompt);
	}
	
	/**
	 * Creates a PictureQuestion from
	 * a ServletRequest using the parameter values
	 */
	public PictureQuestion(int quizID, String type, ServletRequest request) {
		super(quizID, type, request.getParameter(REQUEST_PROMPT));
		setContent0(request.getParameter(REQUEST_ANSWER));
		setContent1(request.getParameter(REQUEST_URL));
	}

	/**
	 * Returns the type of the question
	 * in human-readable text
	 */
	@Override
	public String getHumanText() {
		return HUMAN_READABLE;
	}
	
	/**
	 * Returns the default prompt
	 * for this question
	 */
	@Override
	public String getDefaultPrompt() {
		return DEFAULT_PROMPT;
	}

}
