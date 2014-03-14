package servlets;

import models.*;

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
        request.getSession().setAttribute("quiz_id", quiz.getID());
        request.getSession().setAttribute("next_index", 0);
        request.setAttribute("quiz", quiz);
        request.setAttribute("num_questions", numQuestions);

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
