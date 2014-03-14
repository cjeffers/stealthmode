<%@ page language="java" contentType="text/html; charset=UTF-8" 
 pageEncoding="UTF-8"%>
 <%@ page import="models.User, models.Message, models.Quiz, java.util.*" %> 
 
<!DOCTYPE html>
<html>
<head>
<%@ include file="links.jsp" %>
<meta charset="UTF-8" />
<title>Administrator Page</title>
</head>
<body>
<%@ include file="jquery.jsp" %>
<%
List<User> users = User.findAll();
List<Quiz> quizzes = Quiz.findAll();
int usercount = users.size();
int quizcount = quizzes.size();
%>

<div>
<h4> Create announcements </h4> <button class="expand">+</button>
<div id="createannouncement">
<form action="CreateAnnouncement" method="post">
Announcement Text: <input type="text" name="username"><br>
<input type="submit" id="newannouncement" value="Post">
</form></div></div>

<div>
<h4> Remove User Accounts </h4> <button class="expand">+</button>
<div id="userremove"><ul>
<% for (User person:users){ %>
<li><%=person.getUserName() %> <a href= "#" class ="deleteuser" id="<%= person.getID() %>">Delete User</a></li>
<%} %>
</ul></div></div>

<div>
<h4> Remove Quizzes </h4> <button class="expand">+</button>
<div id="quizremove">
	<ul>
		<% for (Quiz quiz:quizzes){ %>
		<li><%=quiz.getName() %> 
			<a href= "#" class="deletequiz" id="<%= quiz.getID() %>">Delete Quiz</a>
		</li>
<%} %></ul></div></div>

<div>
<h4> Clear all history information </h4> <button class="expand">+</button>
<div id="historyremove"><ul><% for (Quiz quiz:quizzes){ %>

<li><%=quiz.getName() %> <a href= "#" class="deletehistory" id="<%= quiz.getID() %>">Delete Quiz History</a></li>
<%} %></ul></div>
</div>

<div>
<h4> See Site Statistics </h4> <button class="expand">+</button>
<div id="stats">There are <%= usercount  %> users. There are <%=quizcount %> quizzes.</div></div>
<script src="scripts/administrator.js">
</script>




</body>
</html>