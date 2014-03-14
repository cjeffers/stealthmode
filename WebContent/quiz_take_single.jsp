<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="models.Quiz, models.Question, java.util.*" %>
<html>
    <head>
        <%@ include file="links.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Take Quiz</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <% Quiz quiz = (Quiz) request.getAttribute("quiz"); %>
        <% List<Question> questions = (List<Question>) request.getAttribute("questions"); %>
        <div class="container">
            <form action="/stealthmode/quiz/take" method="post">
                <h1><%= quiz.getName() %></h1>
                <p><%= quiz.getDescription() %></p>
                <ul>
                <% for (Question question : questions) { %>
                    <% String include_str = question.getType() + "_question_display.jsp"; %>
                    <li>
                        <jsp:include page="<%= include_str %>" flush="true">
                            <jsp:param name="question_id" value="<%= question.getID() %>" />
                        </jsp:include>
                    </li>
                <% }%>
                </ul>
                <input type="hidden" name="quiz_id" value="<%= quiz.getID() %>" />
                <input type="submit" name="submit" value="Submit Answers" />
            </form>
        </div>
    </body>
</html>
