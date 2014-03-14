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
        int score;
        int quizID;
        int numQuestions;
        long duration;
        boolean isSaved;
        Quiz quiz;

        String resultIDStr = request.getParameter("id");
        if (resultIDStr != null) {
            isSaved = true;

            int resultID = Integer.parseInt(resultIDStr);
            Result result = Result.findByID(resultID);

            int userID = result.getUserID();
            quizID = result.getQuizID();
            quiz = Quiz.findByID(quizID);

            request.setAttribute("username", User.findByID(userID).getUserName());
            request.setAttribute("result", result);
        } else {
            isSaved = false;

            quizID = (Integer) request.getSession().getAttribute("quiz_id");
            quiz = Quiz.findByID(quizID);

            score = (Integer) request.getSession().getAttribute("score");
            numQuestions = quiz.getQuestions().size();
            duration = (Long) request.getSession().getAttribute("duration");

            request.setAttribute("score", score);
            request.setAttribute("num_questions", numQuestions);
            request.setAttribute("duration", duration);
        }
        request.setAttribute("isSaved", isSaved);


        request.setAttribute("quiz", quiz);

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
