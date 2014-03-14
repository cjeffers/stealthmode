<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.*, java.util.*" %>
<% User user = (User) request.getSession().getAttribute("user"); %>

<a href="/stealthmode">Home</a>
<a href="/stealthmode/quizzes">Quizzes</a>
<a href="/stealthmode/users">Users</a>
<div class="login_widget">
    <% if (user == null) { %>
    <a href="/stealthmode/user/login">Login</a> or <a href="/stealthmode/user/create">create a user</a>.
    <% } else { %>
    Welcome, <a href="/stealthmode/user?id=<%= user.getID() %>"><%= user.getUserName() %></a>. <a href="/stealthmode/user/logout">Log out</a>
    <% } %>
</div>
