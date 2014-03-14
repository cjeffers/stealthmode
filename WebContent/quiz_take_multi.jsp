<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="models.Quiz, models.Question, java.util.*" %>
<% boolean isFinal = (Boolean) request.getAttribute("isFinal"); %>
<html>
    <head>
        <%@ include file="links.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Take Quiz</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <% Question question = (Question) request.getAttribute("question"); %>
        <% String include_str = question.getType() + "_question_display.jsp"; %>
        <div class="container">
            <form action="/stealthmode/quiz/take" method="post">
                <jsp:include page="<%= include_str %>" flush="true">
                    <jsp:param name="question_id" value="<%= question.getID() %>" />
                </jsp:include>
                <% if (isFinal) { %>
                <input type="submit" value="Finish quiz" />
                <% } else { %>
                <input type="submit" value="Next Question" />
                <% } %>
            </form>
        </div>
    </body>
</html>
