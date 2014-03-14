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
 * Servlet implementation class UserCreate
 */
@WebServlet("/user/create")
public class UserCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user_create.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("password_confirm");
        String fullname = request.getParameter("fullname");
        String picURL = request.getParameter("pic_url");

        if (!password.equals(passwordConfirm)) {  // make sure passwords are equal
            request.getSession().setAttribute("msg", "Passwords don't match. Try again.");
            response.sendRedirect("/stealthmode/user/create");
        } else if (User.nameInUse(username)) {  // make sure username is valid
            request.getSession().setAttribute("msg", "Username is already in use. Pick a different one.");
            response.sendRedirect("/stealthmode/user/create");
        } else {  // create the user and sign them in
            fullname = (fullname == null ? "" : fullname);
            picURL = (picURL == null ? "" : picURL);

            User newUser = new User(username, password, fullname, false);
            newUser.setPicURL(picURL);
            newUser.save();
            request.getSession().setAttribute("user", newUser);
            response.sendRedirect("/stealthmode/user?id=" + newUser.getID());
        }
    }

}
