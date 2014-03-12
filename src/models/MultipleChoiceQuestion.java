package models;

import javax.servlet.ServletRequest;

public class MultipleChoiceQuestion extends AbstractQuestion {

	/*
	 * Request Parameter Names:
	 */
	private static final String REQUEST_PROMPT = "question_prompt";
	private static final String REQUEST_ANSWER = "question_answer";     // values: "1", "2", "3", "4"
	private static final String REQUEST_QUESTION = "question_question";
	private static final String REQUEST_1 = "question_option_1";
	private static final String REQUEST_2 = "question_option_2";
	private static final String REQUEST_3 = "question_option_3";
	private static final String REQUEST_4 = "question_option_4";




	// constants

	private final String DEFAULT_PROMPT = "Choose the correct option";
	public static final String HUMAN_READABLE = "Multiple Choice";
	public static final String TYPE = "multiple_choice";

	/*
	 * Get question info
	 */

	/**
	 * Returns the first option
	 * @return String
	 */
	public String getOption1() {
		return getContent1();
	}

	/**
	 * Returns the second option
	 * @return String
	 */
	public String getOption2() {
		return getContent2();
	}

	/**
	 * Returns the third option
	 * @return String
	 */
	public String getOption3() {
		return getContent3();
	}

	/**
	 * Returns the fourth option
	 * @return String
	 */
	public String getOption4() {
		return getContent4();
	}

	/**
	 * Returns the question's question
	 * @return question
	 */
	public String getQuestion() {
		return getContent5();
	}


	/*
	 * Constructors:
	 * AbstractModel is same as super
	 * No prompt - uses default prompt
	 */

	/**
	 * Creates MultipleChoice Question from AbstractModel
	 */
	public MultipleChoiceQuestion(AbstractModel am) {
		super(am);
	}

	/**
	 * Creates MultipleChoice Question from:
	 * QuizID, question, prompt
	 */
	public MultipleChoiceQuestion(int quizID, String type, String prompt) {
		super(quizID, type, prompt);
	}

	/**
	 * Creates a MultipleChoiceQuestion from
	 * a ServletRequest using the parameter values
	 */
	public MultipleChoiceQuestion(int quizID, String type, ServletRequest request) {
		super(quizID, type, request.getParameter(REQUEST_PROMPT));
		setContent0(request.getParameter(REQUEST_ANSWER));
		setContent1(request.getParameter(REQUEST_1));
		setContent2(request.getParameter(REQUEST_2));
		setContent3(request.getParameter(REQUEST_3));
		setContent4(request.getParameter(REQUEST_4));
		setContent5(request.getParameter(REQUEST_QUESTION));
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

	// Override getAnswer and checkAnswer

	/**
	 * Get answer checks its answer column to determine
	 * what the corresponding text is.  Returns both in
	 * one string.  Format -
	 * #: text
	 * @return answer
	 */
	@Override
	public String getAnswer() {
		int correct_option = Integer.parseInt(getContent0());
		String correct_answer = null;
		switch(correct_option) {
			case 1: correct_answer = getContent1();
			break;
			case 2: correct_answer = getContent2();
			break;
			case 3: correct_answer = getContent3();
			break;
			case 4: correct_answer = getContent4();
		}
		return (Integer.toString(correct_option) + ": " + correct_answer);
	}
}
