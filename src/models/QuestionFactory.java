package models;

import javax.servlet.ServletRequest;

/**
 * Question factory handles question creation, so that
 * servlets are more logic-free.  It has one method,
 * which takes a ServletRequest containing question info,
 * the quizID, and the question type. Based on the question
 * type, it creates the appropriate question.
 */
public class QuestionFactory {

	/**
	 * Creates a question from a ServletRequest
	 * @param quizID
	 * @param question type String
	 * @param ServletRequest containing question info
	 */
	public static Question makeQuestion(int quizID, String type, ServletRequest request) {
		Question q = null;
		if (type.equals(BasicQuestion.TYPE)) {
			q = new BasicQuestion(quizID, type, request);

		} else if (type.equals(PictureQuestion.TYPE)) {
			q = new PictureQuestion(quizID, type, request);

		} else if (type.equals(MultipleChoiceQuestion.TYPE)) {
			q = new MultipleChoiceQuestion(quizID, type, request);

		} else if (type.equals(FillInBlankQuestion.TYPE)) {
			q = new FillInBlankQuestion(quizID, type, request);
		}
		return q;
	}

    public static String getHumanType(String type) {
        String h = null;
        if (type.equals(BasicQuestion.TYPE)) {
            h = BasicQuestion.HUMAN_READABLE;
        } else if (type.equals(PictureQuestion.TYPE)) {
            h = PictureQuestion.HUMAN_READABLE;
        } else if (type.equals(MultipleChoiceQuestion.TYPE)) {
            h = MultipleChoiceQuestion.HUMAN_READABLE;
        } else if (type.equals(FillInBlankQuestion.TYPE)) {
            h = FillInBlankQuestion.HUMAN_READABLE;
        }
        return h;
    }
}
