package servlets;

import java.util.*;
import models.Quiz;
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/create_quiz.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String quizTitle = request.getParameter("quiz_title");
        String quizDescription = request.getParameter("quiz_description");
        String quizTimed = request.getParameter("quiz_timed");
        String quizMultiPages = request.getParameter("quiz_multi_pages");
        String questionType = request.getParameter("question_type");
        String cancel = request.getParameter("cancel");
        String add = request.getParameter("add");

        if (add != null) {
            boolean isTimed = (quizTimed == null ? false : true);
            boolean isMultiPage = (quizMultiPages == null ? false : true);
            Quiz newQuiz = new Quiz(quizTitle, quizDescription, isTimed,
                                    isMultiPage, System.currentTimeMillis());
            int quizID = newQuiz.save();
            System.out.println("created quiz:" + quizID);

            request.setAttribute("quiz_id", quizID);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/add_question.jsp?type=" + questionType);
            dispatcher.forward(request, response);

        } else if (cancel != null) {
            // TODO forward somewhere else
        }
    }
}
