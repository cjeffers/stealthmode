package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(User.validateLogin(request.getParameter("username"), request.getParameter("password"))){
			User newUser = User.findByUsername(request.getParameter("username"));
			request.getSession().setAttribute("user", newUser);
			response.sendRedirect("/stealthmode/quizzes");
		}
		else{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            request.setAttribute("msg", "Username or password was incorrect. Try again!");
		    dispatcher.forward(request, response);
		}





	}

}
