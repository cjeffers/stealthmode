<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.*, java.util.*" %>
<% MultipleChoiceQuestion q = new MultipleChoiceQuestion(Question.findByID(Integer.parseInt(request.getParameter("question_id")))); %>
<% String answer_name = "question_" + q.getID() + "_answer"; %>
<p><%= q.getPrompt() %></p>
<p><%= q.getQuestion() %></p>
<input type="radio" name="<%= answer_name %>" value="1" /><%= q.getOption1() %><br />
<input type="radio" name="<%= answer_name %>" value="2" /><%= q.getOption2() %><br />
<input type="radio" name="<%= answer_name %>" value="3" /><%= q.getOption3() %><br />
<input type="radio" name="<%= answer_name %>" value="4" /><%= q.getOption4() %><br />
