<%@ page language="java" contentType="text/html; charset=UTF-8" 
 pageEncoding="UTF-8"%>
 <%@ page import="models.User, models.Message, models.Quiz, java.util.*" %> 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Administrator Page</title>
</head>
<body>

<%
List<User> users = User.findAll();
List<Quiz> quizzes = Quiz.findAll();
int usercount = users.size();
int quizcount = quizzes.size();
%>

<h4> Create announcements </h4>
<div>
<form action="CreateAnnouncement" method="post">
Announcement Text: <input type="text" name="username"><br>
<input type="submit" value="Post">
</form></div>

<h4> Remove User Accounts </h4>
<div><ul>
<% for (User person:users){ %>
<li><%=person.getUserName() %> <a href= "#" class ="deleteuser" id="<%= person.getID() %>">Delete User</a></li>
<%} %>
</ul></div>

<h4> Remove Quizzes </h4>
<div><ul><% for (Quiz quiz:quizzes){ %>

<li><%=quiz.getName() %> <a href= "#" class="deletequiz" id="<%= quiz.getID() %>">Delete Quiz</a></li>
<%} %></ul></div>

<h4> Clear all history information </h4>
<div><ul><% for (Quiz quiz:quizzes){ %>

<li><%=quiz.getName() %> <a href= "#" class="deletehistory" id="<%= quiz.getID() %>">Delete Quiz History</a></li>
<%} %></ul></div>

<h4> See Site Statistics </h4>
<div>There are <%= usercount  %> users. There are <%=quizcount %> quizzes.</div>





</body>
</html>