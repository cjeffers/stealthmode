<%@ page language="java" contentType="text/html; charset=UTF-8" 
 pageEncoding="UTF-8"%>
 <%@ page import="models.User, models.Message, java.util.*" %> 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Inboxt</title>
</head>
<body>
<h1>Messages Recieved</h1>




<%
User me = (User)request.getSession().getAttribute("user");
List<Message> inboxmessage = me.seeMessages();
List<Message> inboxchallenge = me.seeChallenges();
List<Message> inboxrequests = me.seeRequests();
%>

<% if(inboxmessage != null){
for(Message a:inboxmessage){ %>
From: <%=a.getSender()%> <br>
Text: <%=a.getText() %> <br>
<%}} %>
<br>
<%if(inboxchallenge != null){ 
for(Message a:inboxchallenge){ %>
From: <%=a.getSender()%> <br>
Text: <%=a.getText() %> <br>
QuizID: <%=a.getQuiz() %> <br>
<%}} %>
<br>
<% if(inboxrequests != null){
for(Message a:inboxrequests){ %>
From: <%=a.getSender()%> <br>
Text: <%=a.getText() %> <br>
<%}} %>

</body>
</html>