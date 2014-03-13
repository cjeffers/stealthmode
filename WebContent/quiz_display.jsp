<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.Quiz, java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% Quiz quiz = (Quiz) request.getAttribute("quiz"); %>
<% int numQuestions = (Integer) request.getAttribute("num_questions"); %>
<html>
    <head>
        <%@ include file="links.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= quiz.getName() %></title>
    </head>
    <body>
        <div class="container">
            <% List<Quiz> quizzes = (List<Quiz>) request.getAttribute("quizzes"); %>
            <h1><%= quiz.getName() %></h1>
            <p><%= quiz.getDescription() %></p>
            <p>Number of questions: <%= numQuestions %></p>
            <% request.setAttribute("id", quiz.getID()); %>
            <form action="/stealthmode/quiz/take" method="get">
                <input type="submit" value="Take Quiz" />
                <input type="hidden" name="quiz_id" value="<%= quiz.getID() %>" />
            </form>
            <a href="/stealthmode/quizzes">Browse quizzes</a>
        </div>
    </body>
</html>
