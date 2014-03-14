
<%@ page language="java" contentType="text/html; charset=UTF-8" 
 pageEncoding="UTF-8"%>
 <%@ page import="models.User, models.Result, models.Quiz, java.util.* " %> 
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Profile</title>
</head>
<body>
<%User me = (User)request.getSession().getAttribute("user");
List<Quiz> quizzesMade = Quiz.findByCreator(me.getID());
List<Result> lastfive = me.getRecentResults();
List<User> friends = me.getFriends();
List<String> achievements = me.seeAwardsWon();
int n=0;%>
<%=me.getUserName() %>
<br>
<div id="recentquizzes">
<table>
<tr>
<td>
Last 5 Quizzes Taken:
</td>
<td>
<ol>
<% for (Result res: lastfive){ 
	if(n==5) break;
	n+=1;%>
<li><%=res.getQuiz().getName()%> </li>	
<%}%>
</ol>
</td>
</tr>
<tr>
<td>
Last 5 Quizzes Created:
</td>
<td>
List Here
</td>
</tr>
</table>
</div>
<div id="achievements">
<%for(String earned: achievements){%>
<%=earned %>
<%}%></div>
<div id="friendsRecent"></div>
<div id="friendsList">
<%for(User friend:friends){%>
<a href=""><%=friend.getUserName() %></a>
<%}%>
</div>
<div id="inbox"></div>
<div id="quizzesMade"><%for(Quiz quiz:quizzesMade){%>
<a href=""><%=quiz.getName() %></a>
<%}%></div>
<div id="newQuiz"><a href="create_quiz.jsp" > Create A New Quiz</a></div>
Snoop's recent quiz taking and creating shenanigans, and a link to the full list. Nothing here, since he wasn't logged in for his last awesome quiz-take.
A list of achievements. Snoop retook the "Tupac Quiz" after logging in, so he has the "I am the greatest" achievement, with the quiz name and the date achieved.
A list of friends' recent create/take/achievement activity. Snoop needs to make friends!
A list of friends. Sadly empty.
A list of messages. Also sadly empty.
A link to a list of all users. How to make friends...
A link to browse quizzes

</body>
</html>