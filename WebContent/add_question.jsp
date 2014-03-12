<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.QuestionFactory" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add question</title>
    </head>
    <body>
        <h1>Add a <%= QuestionFactory.getHumanType(request.getParameter("type")) %> Question</h1>
        <form action="/stealthmode/quiz/add_question" method="post">
            <% String type_jsp = request.getAttribute("type") + "_question_create.jsp"; %>
            <jsp:include page="<%= type_jsp %>" flush="true" />
            <p>
                Question type:
                <select name="next_question_type">
                    <option value="basic">Basic</option>
                    <option value="multiple_choice">Multiple choice</option>
                    <option value="fill_in_blank">Fill in the blank</option>
                    <option value="picture">Picture</option>
                </select>
            </p>
            <input type="hidden" name="question_type" value='<%= request.getAttribute("type") %>'>
            <input type="submit" name="add_question" value="Add a question" />
            <input type="submit" name="cancel" value="Cancel" />
            <input type="submit" name="finish" value="Save and finish" />
        </form>
    </body>
</html>
