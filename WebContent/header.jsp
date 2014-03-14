<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.*, java.util.*" %>
<% User u = (User) request.getSession().getAttribute("user"); %>

<a href="/stealthmode/home">Home</a>
<a href="/stealthmode/quizzes">Quizzes</a>
<a href="/stealthmode/users">Users</a>
<div class="login_widget">
    <% if (u == null) { %>
    <a href="/stealthmode/user/login">Login</a> or <a href="/stealthmode/user/create">create a user</a>.
    <% } else { %>
    Welcome, <a href="/stealthmode/user?id=<%= u.getID() %>"><%= u.getUserName() %></a>. <a href="/stealthmode/user/logout">Log out</a>
    <% } %>
</div>
