
<%@ page language="java" contentType="text/html; charset=UTF-8" 
 pageEncoding="UTF-8"%>
 <%@ page import="models.User, models.Result, models.Quiz, models.Message, java.util.* " %> 
 <!DOCTYPE html>
<html>
<head>
<%@ include file="links.jsp" %>
<link rel="stylesheet" type="text/css" href="/stealthmode/css/profile.css" />

<meta charset="UTF-8" />
<title>Profile</title>
</head>
<body>
<%@ include file="header.jsp" %>
<%@ include file="jquery.jsp" %>

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

<div class="container">
<center><h1><%=me.getUserName() %></h1>
 <img class="profile" src="<%= me.getPicURL() %>" /></center>
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
<table>

<tr>
<td>
Quiz
</td>
<td>
Score
</td>
</tr>
<% for (Result res: lastfive){ 
	
	if(n==5) break;
	n+=1;%>
<tr>
	<td><%=n%>. <a href="/stealthmode/quiz?id=<%=res.getQuizID()%>"><%=res.getQuiz().getName()%></a></td><td><%=res.getScore() %> </td>
</tr>
<%}%>

</table>
</td>
<td>
<ol>
<% int m=0; 
for (Quiz quiz: recentQuizzes){ 
	if(m==5) break;
	m+=1;%>
<li><a href="/stealthmode/quiz?id=<%=quiz.getID()%>"><%=quiz.getName()%></a> </li>	
<%}%>
</ol>
</td>
</tr>
<tr>
<td>
<div id="friendsRecent"></div>
<div >
Friends List <button class="expand">+</button>
<div id="friendsList">
<%for(User friend:friends){%>
<a href=""><%=friend.getUserName() %></a>
<br>
<%}%>
</div>
</div>
</td>
<td>
<div>
Quizzes Created<button class="expand">+</button>
<div id="quizzesMade"><%for(Quiz quiz:quizzesMade){%>
<a href=""><%=quiz.getName() %></a>
<%}%></div>
</div>
</td>
</tr>
<tr>
<td colspan="2" class="left">
Inbox <button class="expand">+</button>
<div id="inbox">
<table>
<tr><td>Messages</td></tr>
<% for (Message message:inboxmessage){ %>
<tr><td>From:</td><td><%=message.getSender() %></td></tr>
<tr><td>Message:</td><td><%=message.getText() %></td></tr>
<%} %>

<tr><td>Challenges</td></tr>
<% for (Message message:inboxchallenge){ 
User j = User.findByUsername(message.getSender());%>

<tr><td>From:</td><td><a href="/stealthmode/quiz?id=<%=j.getID()%>"><%=message.getSender() %></a></td></tr>
<tr><td>Message:</td><td><%=message.getText() %></td></tr>
<tr><td>Take this Quiz:</td><td><a href="/stealthmode/quiz?id=<%=message.getQuiz()%>">Take the Quiz!</a></td></tr>
<%} %>

</table>
</div>
</td>
</tr>
</table>
<div id="achievements">

</div>



<div id="newQuiz"><a href="create_quiz.jsp" > Create A New Quiz</a></div>
<% if (me.isAdministrator()){ %>
<a href="administrator.jsp">Access Administrator Privileges</a>
<%} %>
</div>
<script src="scripts/profile.js">
</script>
</body>
</html>