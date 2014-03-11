package models;

import javax.servlet.ServletRequest;

public class ImageQuestion extends AbstractQuestion {

	public ImageQuestion(AbstractModel am) {
		super(am);
	}

	@Override
	public boolean checkAnswer(ServletRequest request) {
		String answer = request.getParameter("answer");
		return (answer.equals(getContent0()));
	}

	@Override
	public String getAnswer() {
		return getContent0();
	}

}
