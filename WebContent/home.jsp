<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.*, java.util.List, java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
List<Quiz> popularQuizzes = (List<Quiz>) request.getAttribute("popular_quizzes");
List<Quiz> recentQuizzes = (List<Quiz>) request.getAttribute("recent_quizzes");
%>
<html>
    <head>
        <%@ include file="links.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stealthmode Home</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container">
            <h1>Welcome to stealthmode</h1>
            <p>a.k.a. "Fo shizzle, my quizzle."</p>

            <%--Popular quizzes--%>
            <h2>Popular Quizzes</h2>
            <table id="popular_quizzes">
                <tr class="header">
                    <td class="name">Quiz</td>
                    <td class="times_taken"># times taken</td>
                </tr>
                <% for (Quiz pop : popularQuizzes) {
                    String popURL = "/stealthmode/quiz?id=" + pop.getID();
                    int timesTaken = Quiz.getPopularity(pop.getID());
                    %>
                <tr class="row">
                    <td class="name"><a href="<%= popURL %>"><%= pop.getName() %></td>
                    <td class="times_taken"><%= timesTaken %></td>
                </tr>
                <% } %>
            </table>

            <%--Recent quizzes--%>
            <h2>Recent Quizzes</h2>
            <table id="recent_quizzes">
                <tr class="header">
                    <td class="name">Quiz</td>
                    <td class="time_created">Time created</td>
                </tr>
                <% for (Quiz rec : recentQuizzes) {
                    String recURL = "/stealthmode/quiz?id=" + rec.getID();
                    long timeCreated = rec.getDateMade();
                    %>
                <tr class="row">
                    <td class="name"><a href="<%= recURL %>"><%= rec.getName() %></td>
                    <td class="times_taken"><%= sdf.format(timeCreated) %></td>
                </tr>
                <% } %>
            </table>
        </div>
    </body>
</html>
