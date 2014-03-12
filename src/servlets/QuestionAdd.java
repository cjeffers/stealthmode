package servlets;

import models.*;

import java.util.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuestionAdd
 */
@WebServlet("/quiz/add_question")
public class QuestionAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // make sure the JSP can find the question type
        request.setAttribute("type", request.getParameter("type"));

        // display the form for the question
        RequestDispatcher dispatcher = request.getRequestDispatcher("/add_question.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get the state we need
        String questionType = request.getParameter("question_type");
        String nextQuestionType = request.getParameter("next_question_type");
        String add = request.getParameter("add_question");

        // submit buttons
        String cancel = request.getParameter("cancel");
        String finish = request.getParameter("finish");

        // this is on the session because of redirects
        int quizID = (Integer) request.getSession().getAttribute("quiz_id");

        if (cancel != null) {  // then we're cancelling, so we delete the quiz and show the appropriate page

            Quiz q = Quiz.findByID(quizID);
            q.delete();  // gotta delete, since we saved the quiz already!
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cancel_quiz_creation.html");
            dispatcher.forward(request, response);

        } else {  // either we're adding another question or finishing

            // make and save the current question
            Question newQuestion = QuestionFactory.makeQuestion(quizID, questionType, request);
            newQuestion.save();

            if (add != null) {  // we're adding another question, display the appropriate page
                response.sendRedirect("/stealthmode/quiz/add_question?type=" + nextQuestionType);

            } else if (finish != null) {  // all done with this quiz, go to the finish page
                RequestDispatcher dispatcher = request.getRequestDispatcher("/finish_quiz_creation.html");
                dispatcher.forward(request, response);
            }
        }
    }
}
