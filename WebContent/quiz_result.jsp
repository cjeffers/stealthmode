<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.Quiz, java.util.List, java.util.*, java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
int score;
int numQuestions;
long duration;
long takenAt;
String titleStart;
String title;
Calendar dateTaken = Calendar.getInstance();
Quiz quiz = (Quiz) request.getAttribute("quiz");
String quizURL = "/stealthmode/quiz?id=" + quiz.getID();

boolean isSaved = (Boolean) request.getAttribute("isSaved");
if (isSaved) {
    Result result = (Result) request.getAttribute("result");
    score = result.getScore();
    numQuestions = result.getNumQuestions();
    duration = result.getDuration();
    takenAt = result.getTakenAt();
    String username = (String) request.getAttribute("username");

    titleStart = username + ": results for ";
    title = titleStart + quiz.getName();
} else {
    score = (Integer) request.getAttribute("score");
    numQuestions = (Integer) request.getAttribute("num_questions");
    duration = (Long) request.getAttribute("duration");
    takenAt = System.currentTimeMillis();

    titleStart = "Results for ";
    title = "Results for " + quiz.getName();
}

double percentage = (((double) score) / numQuestions) * 100.0;
dateTaken.setTimeInMillis(takenAt);
SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMMM d yyyy, h:mm a");
long totalSeconds = duration / 1000;
long minutes = totalSeconds / 60;
long seconds = totalSeconds % 60;
long millis = duration % 1000;
%>

<html>
    <head>
        <%@ include file="links.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title><%= title %></title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container">
            <h1><%= titleStart %><a href="<%= quizURL %>"><%= quiz.getName() %></a></h1>
            <% if (!isSaved) { %><p>Congratulations on finishing the quiz! Here are your results:</p><% } %>
        </%%>
            <table>
                <tr><td class="label">Number correct:</td><td class="input"><%= score %></td></tr>
                <tr><td class="label">Number of questions:</td><td class="input"><%= numQuestions %></td></tr>
                <tr><td class="label">Percentage correct:</td><td class="input"><%= String.format("%.1f", percentage) %>%</td></tr>
                <tr><td class="label">Amount of time taken:</td><td class="input"><%= minutes + "m, " + seconds + "." + millis + "s" %></td></tr>
                <tr><td class="label">Date taken:</td><td class="input"><%= sdf.format(dateTaken.getTime()) %></td></tr>
            </table>
            <a href="/stealthmode/quizzes">Browse quizzes</a>
            <a href="<%= quizURL %>">View quiz</a>
        </div>
    </body>
</html>
