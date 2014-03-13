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
 * Servlet implementation class QuizTake
 */
@WebServlet("/quiz/take")
public class QuizTake extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizTake() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("quiz_id"));
        Quiz quiz = Quiz.findByID(id);
        List<Question> questions;

        if (quiz.hasMultiplePages()) {  // mutli-page
            // TODO get index from form
            int index = Integer.parseInt(request.getParameter("next_index"));
            int quizID = Integer.parseInt(request.getParameter("quiz_id"));
            if (index == 0) {  // initialize question array
                questions = quiz.getQuestions();
            } else {
                questions = (List<Question>) request.getSession().getAttribute("questions");
            }
            Question question = questions.get(index);
            int nextIndex = index + 1;
            // TODO stick question and next index on request
            // TODO forward to multi-page JSP
        } else {  // single-page
            questions = quiz.getQuestions();

            RequestDispatcher dispatcher;

            request.setAttribute("quiz", quiz);
            request.setAttribute("questions", questions);
            dispatcher = request.getRequestDispatcher("/quiz_take_single.jsp");

            dispatcher.forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("quiz_id"));
        Quiz quiz = Quiz.findByID(id);
        List<Question> questions = quiz.getQuestions();

        if (quiz.hasMultiplePages()) {  // mutli-page
            // TODO get index from form
            // TODO stuff
        } else {
            Integer score = quiz.checkScore(request);
            System.out.println("Score:" + score);

            request.getSession().setAttribute("score", score);
            request.getSession().setAttribute("quiz_id", id);

            response.sendRedirect("/stealthmode/quiz/result");
        }
	}

}
