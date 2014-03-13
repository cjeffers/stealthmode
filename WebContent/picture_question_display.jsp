<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.*, java.util.*" %>
<% PictureQuestion q = new PictureQuestion(Question.findByID(Integer.parseInt(request.getParameter("question_id")))); %>
<% String answer_name = "question_" + q.getID() + "_answer"; %>
<p><%= q.getPrompt() %></p>
<p><img src="<%= q.getURL() %>"></p>
<input type="text" name="<%= answer_name %>" />
