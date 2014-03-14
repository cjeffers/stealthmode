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
        int quizID = (Integer) request.getSession().getAttribute("quiz_id");
        int index = (Integer) request.getSession().getAttribute("next_index");
        Quiz quiz = Quiz.findByID(quizID);
        List<Question> questions = quiz.getQuestions();

        if (quiz.hasMultiplePages()) {  // mutli-page
            // get stuff from form
            if (index == 0) {
                request.getSession().setAttribute("score", 0);
                request.getSession().setAttribute("start_time", System.currentTimeMillis());
            }
            Question question = questions.get(index);

            // stick question and next index on request
            request.setAttribute("question", question);
            request.getSession().setAttribute("index", index);

            // TODO forward to multi-page JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/quiz_take_multi.jsp");
            dispatcher.forward(request, response);
        } else {  // single-page

            RequestDispatcher dispatcher;

            request.getSession().setAttribute("start_time", System.currentTimeMillis());
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
        User user = (User) request.getSession().getAttribute("user");
        boolean loggedIn = (user != null);
        int id = (Integer) request.getSession().getAttribute("quiz_id");
        Quiz quiz = Quiz.findByID(id);
        List<Question> questions = quiz.getQuestions();
        long finish = System.currentTimeMillis();
        long start = (Long) request.getSession().getAttribute("start_time");
        Result res;
        String redirectURL = "/stealthmode/quiz/result";

        if (quiz.hasMultiplePages()) {  // mutli-page
            // get index of question, question, and current score
            int index = (Integer) request.getSession().getAttribute("index");
            Question question = questions.get(index);
            int score = (Integer) request.getSession().getAttribute("score");

            // set score + 1 if correct
            if (question.checkAnswer(request)) score += 1;

            request.getSession().setAttribute("score", score);

            int nextIndex = index + 1;

            if (nextIndex == questions.size()) {  // the quiz is over!
                if (loggedIn) {
                    res = new Result(quiz.getID(), user.getID(), score, questions.size(), finish, finish - start);
                    redirectURL = redirectURL + "?id=" + res.save();
                } else {
                    request.getSession().setAttribute("quiz_id", id);
                    request.getSession().setAttribute("duration", finish - start);
                }
            } else {  // send the next question
                request.getSession().setAttribute("next_index", nextIndex);
                redirectURL = "/stealthmode/quiz/take";
            }

        } else {  // single page

            Integer score = quiz.checkScore(request);

            if (loggedIn) {
                res = new Result(quiz.getID(), user.getID(), score, questions.size(), finish, finish - start);
                redirectURL = redirectURL + "?id=" + res.save();
            } else {
                request.getSession().setAttribute("score", score);
                request.getSession().setAttribute("quiz_id", id);
                request.getSession().setAttribute("duration", finish - start);
            }
            response.sendRedirect(redirectURL);
        }
	}

}
