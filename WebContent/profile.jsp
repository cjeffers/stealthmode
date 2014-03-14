
<%@ page language="java" contentType="text/html; charset=UTF-8" 
 pageEncoding="UTF-8"%>
 <%@ page import="models.User, models.Result, models.Quiz, models.Message, java.util.* " %> 
 <!DOCTYPE html>
<html>
<head>
<%@ include file="links.jsp" %>
<meta charset="UTF-8" />
<title>Profile</title>
</head>
<body>
<%User me = (User)request.getSession().getAttribute("user");
List<Quiz> quizzesMade = Quiz.findByCreator(me.getID());
List<Quiz> recentQuizzes = me.recentlyCreatedQuizzes();
List<Result> lastfive = me.getRecentResults();
List<User> friends = me.getFriends();
//List<String> achievements = me.seeAwardsWon();
List<Message> inboxmessage = me.seeMessages();
List<Message> inboxchallenge = me.seeChallenges();
List<Message> inboxrequests = me.seeRequests();
int n=0;%>
<%=me.getUserName() %>
<br>
<div id="container">
<div id="recentquizzes">
<table class="recentHolder">
<tr>
<td class="center">
Last 5 Quizzes Taken:
</td>
<td class="center">
Last 5 Quizzes Created:
</td>
</tr>
<tr>
<td>
<ol>
<% for (Result res: lastfive){ 
	if(n==5) break;
	n+=1;%>
<li><%=res.getQuiz().getName()%><%=res.getScore() %> </li>	
<%}%>
</ol>
</td>
<td>
<ol>
<% int m=0; 
for (Quiz quiz: recentQuizzes){ 
	if(m==5) break;
	m+=1;%>
<li><%=quiz.getName()%> </li>	
<%}%>
</ol>
</td>
</tr>
</table>
</div>
<div id="achievements">

</div>
<div id="friendsRecent"></div>
<div id="friendsList">
<%for(User friend:friends){%>
<a href=""><%=friend.getUserName() %></a>
<%}%>
</div>
<div id="inbox">
<% if(inboxmessage != null){
for(Message a:inboxmessage){ %>
<table>
<tr>
<td>
From: <%=a.getSender()%> </td>
</tr>
<tr>
<td>
Text: <%=a.getText() %> <br></td>
</tr>
</table>
<%}} %>
<br>
<%if(inboxchallenge != null){ 
for(Message a:inboxchallenge){ %>
<table>
<tr>
<td>
From: <%=a.getSender()%> </td>
</tr>
<tr>
<td>
QuizID: <%=a.getQuiz() %> <br></td>
</tr>
<tr>
<td>
Text: <%=a.getText() %> <br></td>
</tr>
</table>
<%}} %>
<br>
<% if(inboxrequests != null){
for(Message a:inboxrequests){ %>
<table>
<tr><td>From: <%=a.getSender()%> <br></td></tr>

<tr><td>Text: <%=a.getText() %></td></tr> </table><br>
<%}} %></div>
<div id="quizzesMade"><%for(Quiz quiz:quizzesMade){%>
<a href=""><%=quiz.getName() %></a>
<%}%></div>
<div id="newQuiz"><a href="create_quiz.jsp" > Create A New Quiz</a></div>
</div>
</body>
</html>