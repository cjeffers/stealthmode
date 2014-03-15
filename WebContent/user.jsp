<%@ page language="java" contentType="text/html; charset=UTF-8" 
 pageEncoding="UTF-8"%>
 <%@ page import="models.User, models.Quiz, models.Result" %> 
<% User user = (User) User.findByID(Integer.parseInt(request.getParameter("id"))); 
List<Quiz> allQuizzes = Quiz.findAll();
List<Result> results = user.getRecentResults();
List<String> achievements = new ArrayList<String>();//user.seeAwardsWon();
List<User> friends = user.getFriends();
%>
 
<!DOCTYPE html>
<html>
	<head>
        <%@ include file="links.jsp" %>
		<meta charset="UTF-8" />
		<title><%= user.getUserName() %></title>
	</head>
    <body>
        <%@ include file="header.jsp" %>
        <%@ include file="jquery.jsp" %>
        <div class="container">


            <h1><%= user.getUserName() %></h1>
            <br>
            Full name: <%= user.getFullname() %>
            <br>
            <img class="profile" src="<%= user.getPicURL() %>" />
            <br>
            <table>
            <tr>
            <td rowspan="2">
            Send Friend Request:
            <br>
            <textarea id='friendRequestText' ></textarea>
            <br>
            <button id="sendFriendRequest">Send Friend Request</button>
            <br>
            Send Message:<br>
            <textarea id='messageText' ></textarea>
            <input type="hidden" value="<%=user.getUserName()%>" id="sendTo"></input>
            
            <br>
            <button id="sendMessage">Send Message</button>
            <br>
            Send Challenge:
            <br>
            Quiz:<select id="quizSelector">
  			<% for (Quiz quiz: allQuizzes){ %>
  			<option value="<%=quiz.getID()%>"><%=quiz.getName() %></option>
  			<%} %>
			</select>
			<br>
			<textarea id='challengeText' ></textarea>
			<br>
			<button id="sendChallenge">Send Challenge</button>
            <br>
            </td>
            <td>Recent Quiz History<br>
            <ol><% int n = 0;
            for(Result result: results){
            	if(n>=5) break;%>
            	<li><%=result.getQuiz().getName()%>:<%=result.getScore() %></li>
            <% }%>
            
            </ol></td></tr>
            <tr><td>Achievements
           </td></tr>
            <tr ><td colspan="2">Friends
            <%for(User friend: friends){%>
            	<a href="/stealthmode/user?id=<%=friend.getID()%>"><%=friend.getUserName() %></a>
            <% }%>
            </td></tr>
</table>
            
            
            <a href="/stealthmode/users">See users</a>
        </div>
        <script src="scripts/otheruser.js"></script>
    </body>
</html>
