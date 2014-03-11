package models;

import javax.servlet.ServletRequest;

public class FillInBlankQuestion extends AbstractQuestion {
	
	/*
	 * Request Parameter Names:
	 */
	private static final String REQUEST_PROMPT = "question_prompt";
	private static final String REQUEST_BEFORE = "question_before";
	private static final String REQUEST_BLANK = "question_blank";
	private static final String REQUEST_AFTER = "question_after";
	
	// constants
	
	private final String DEFAULT_PROMPT = "Fill in the blank.";
	private final String HUMAN_READABLE = "Fill In The Blank";
	
	
	/*
	 * Constructors:
	 * AbstractModel is same as super
	 * No prompt - uses default prompt
	 */
	
	/**
	 * Creates Image Question from AbstractModel
	 */
	public FillInBlankQuestion(AbstractModel am) {
		super(am);
	}
	
	/**
	 * Creates Image Question from:
	 * QuizID, question, prompt
	 */
	public FillInBlankQuestion(int quizID, String type, String prompt) {
		super(quizID, type, prompt);
	}

	/**
	 * Creates a FillInBlankQuestion from
	 * a ServletRequest using the parameter values
	 */
	public FillInBlankQuestion(int quizID, String type, ServletRequest request) {
		super(quizID, type, request.getParameter(REQUEST_PROMPT));
		setContent0(request.getParameter(REQUEST_BLANK));
		setContent1(request.getParameter(REQUEST_BEFORE));
		setContent2(request.getParameter(REQUEST_AFTER));
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
