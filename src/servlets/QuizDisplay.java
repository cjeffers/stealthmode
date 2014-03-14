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
 * Servlet implementation class QuizDisplay
 */
@WebServlet("/quiz")
public class QuizDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizDisplay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Quiz quiz = Quiz.findByID(Integer.parseInt(request.getParameter("id")));
        int numQuestions = quiz.getQuestions().size();
        User creator = User.findByID(quiz.getCreatorID());
        User me = (User) request.getSession().getAttribute("user");

        if (me != null) {
            List<Result> myResults = Result.findByQuizAndUser(quiz.getID(), me.getID());
            Collections.sort(myResults, Quiz.SCORE_SORT);
            request.setAttribute("my_results", myResults);
        }

        request.getSession().setAttribute("quiz_id", quiz.getID());
        request.getSession().setAttribute("next_index", 0);
        request.setAttribute("quiz", quiz);
        request.setAttribute("num_questions", numQuestions);
        request.setAttribute("creator", creator);
        request.setAttribute("top_results", quiz.getScores());
        request.setAttribute("recent_results", quiz.getRecentResults());
        request.setAttribute("average_score", quiz.averageScore());
        request.setAttribute("average_time", quiz.averageTime());

        RequestDispatcher dispatcher = request.getRequestDispatcher("quiz_display.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
