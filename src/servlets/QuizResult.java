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
 * Servlet implementation class QuizResult
 */
@WebServlet("/quiz/result")
public class QuizResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int score = (Integer) request.getSession().getAttribute("score");
        int quizID = (Integer) request.getSession().getAttribute("quiz_id");
        Quiz quiz = Quiz.findByID(quizID);
        int numQuestions = quiz.getQuestions().size();

        request.setAttribute("quiz", quiz);
        request.setAttribute("score", score);
        request.setAttribute("num_questions", numQuestions);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/quiz_result.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
