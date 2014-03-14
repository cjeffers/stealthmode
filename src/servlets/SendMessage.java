package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;

/**
 * Servlet implementation class SendMessage
 */
@WebServlet("/SendMessage")
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User me = (User)request.getSession().getAttribute("user");
		Character type =  request.getParameter("type").charAt(0);
		if(type == 'm'){
			me.sendNote(request.getParameter("user"), request.getParameter("message"));
		}
		else if(type == 'f'){
			me.sendRequest(request.getParameter("user"), request.getParameter("message"));
		}
		else if(type == 'c'){
			me.sendChallenge(request.getParameter("user"), request.getParameter("message"), Integer.parseInt(request.getParameter("quizid")));
		}
	}

}
