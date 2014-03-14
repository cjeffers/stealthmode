<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.Quiz, java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <%@ include file="links.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quizzes</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container">
            <% List<Quiz> quizzes = (List<Quiz>) request.getAttribute("quizzes"); %>
            <h1>Quizzes</h1>
            <% for (Quiz q : quizzes) { %>
                <div class="quiz_title">
                    <% String quiz_href = "/stealthmode/quiz?id=" + q.getID(); %>
                    <% String creator_href = "/stealthmode/user?id=" + q.getCreatorID(); %>
                    <a href="<%= quiz_href %>"><%= q.getName() %></a> created by
                    <a href="<%= creator_href %>"><%= User.findByID(q.getCreatorID()).getFullname() %></a>
                </div>
            <% } %>
            <% if (request.getSession().getAttribute("user") != null)  { %>
            <a href="/stealthmode/quiz/create">Create a quiz</a>
            <% } %>
        </div>
    </body>
</html>
