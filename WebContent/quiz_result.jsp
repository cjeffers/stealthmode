<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.Quiz, java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% Quiz quiz = (Quiz) request.getAttribute("quiz"); %>
<% int score = (Integer) request.getAttribute("score"); %>
<% int numQuestions = (Integer) request.getAttribute("num_questions"); %>
<% double percentage = (((double) score) / numQuestions) * 100.0; %>
<% String quizURL = "/stealthmode/quiz?id=" + quiz.getID(); %>
<html>
    <head>
        <%@ include file="links.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results for <%= quiz.getName() %></title>
    </head>
    <body>
        <div class="container">
            <h1>Results for <a href="<%= quizURL %>"><%= quiz.getName() %></a></h1>
            <p>Congratulations on finishing the quiz! Here are your results:</p>
            <table>
                <tr><td class="label">Number correct:</td><td class="input"><%= score %></td></tr>
                <tr><td class="label">Number of questions:</td><td class="input"><%= numQuestions %></td></tr>
                <tr><td class="label">Percentage correct:</td><td class="input"><%= String.format("%.1f", percentage) %>%</td></tr>
            </table>
            <a href="/stealthmode/quizzes">Browse quizzes</a>
            <a href="<%= quizURL %>">View quiz</a>
        </div>
    </body>
</html>
