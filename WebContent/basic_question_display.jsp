<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.*, java.util.*" %>
<% BasicQuestion q = new BasicQuestion(Question.findByID(Integer.parseInt(request.getParameter("question_id")))); %>
<% String answer_name = "question_" + q.getID() + "_answer"; %>
<p><%= q.getPrompt() %></p>
<p><%= q.getQuestion() %></p>
<textarea name="<%= answer_name %>"></textarea>
