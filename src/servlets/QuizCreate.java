package servlets;

import java.util.*;
import models.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuizCreate
 */
@WebServlet("/quiz/create")
public class QuizCreate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean loggedIn = (request.getSession().getAttribute("user") != null);
        // display the forms to create the question
        RequestDispatcher dispatcher;
        if (loggedIn) {
            dispatcher = request.getRequestDispatcher("/create_quiz.jsp");
        } else {
            dispatcher = request.getRequestDispatcher("/not_logged_in.jsp");
        }
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get all the parameters
        String quizTitle = request.getParameter("quiz_title");
        String quizDescription = request.getParameter("quiz_description");
        String quizTimed = request.getParameter("quiz_timed");
        String quizMultiPages = request.getParameter("quiz_multi_pages");
        User me = (User) request.getSession().getAttribute("user");

        // for the next question (if applicable)
        String questionType = request.getParameter("question_type");

        // possible submit buttons
        String cancel = request.getParameter("cancel");
        String add = request.getParameter("add");

        if (me != null) {
            if (add != null) {  // we are adding a question

                boolean isTimed = (quizTimed == null ? false : true);
                boolean isMultiPage = (quizMultiPages == null ? false : true);

                // make and save the quiz
                Quiz newQuiz = new Quiz(quizTitle, quizDescription, isTimed,
                        isMultiPage, System.currentTimeMillis(), me.getID());
                int quizID = newQuiz.save();

                // show the form for the first question
                request.getSession().setAttribute("quiz_id", quizID);
                response.sendRedirect("/stealthmode/quiz/add_question?type=" + questionType);

            } else if (cancel != null) {  // we're cancelling quiz creation
                RequestDispatcher dispatcher = request.getRequestDispatcher("/cancel_quiz_creation.html");
                dispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/not_logged_in.jsp");
            dispatcher.forward(request, response);
        }
    }
}
