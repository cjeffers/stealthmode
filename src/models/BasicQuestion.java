package models;

import javax.servlet.ServletRequest;

public class BasicQuestion extends AbstractQuestion {
	
	/*
	 * Request Parameter Names:
	 */
	private static final String REQUEST_PROMPT = "question_prompt";
	private static final String REQUEST_ANSWER = "question_answer";
	private static final String REQUEST_QUESTION = "question_question";
	
	// constants
	
	private final String DEFAULT_PROMPT = "Answer the question.";
	private final String HUMAN_READABLE = "Basic";
	public static final String TYPE = "basic";
	
	
	/*
	 * Get question info
	 */
	
	/**
	 * Returns the question's question
	 * @return question
	 */
	public String getQuestion() {
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
	public BasicQuestion(AbstractModel am) {
		super(am);
	}
	
	/**
	 * Creates Image Question from:
	 * QuizID, question, prompt
	 */
	public BasicQuestion(int quizID, String type, String prompt) {
		super(quizID, type, prompt);
	}

	/**
	 * Creates a BasicQuestion from
	 * a ServletRequest using the parameter values
	 */
	public BasicQuestion(int quizID, String type, ServletRequest request) {
		super(quizID, type, request.getParameter(REQUEST_PROMPT));
		setContent0(request.getParameter(REQUEST_ANSWER));
		setContent1(request.getParameter(REQUEST_QUESTION));
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
