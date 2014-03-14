<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <%@ include file="links.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create quiz</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container">
            <h1>New Quiz</h1>
            <form action="/stealthmode/quiz/create" method="post">
                <p>Quiz title: <input type="text" name="quiz_title" /></p>
                <p>Description: <textarea name="quiz_description"></textarea></p>
                <p>Check if timed: <input type="checkbox" name="quiz_timed" /></p>
                <p>Check if displayed on multiple pages: <input type="checkbox" name="quiz_multi_pages" /></p>
                <p>
                    Question type:
                    <select name="question_type">
                        <option value="basic">Basic</option>
                        <option value="multiple_choice">Multiple choice</option>
                        <option value="fill_in_blank">Fill in the blank</option>
                        <option value="picture">Picture</option>
                    </select>
                </p>
                <input type="submit" name="add" value="Add a question" />
                <input type="submit" name="cancel" value="Cancel" />
            </form>
        </div>
    </body>
</html>
