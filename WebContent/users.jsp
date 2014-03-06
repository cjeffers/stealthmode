<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, models.User" %> 
<% List<User> users = (List<User>) request.getAttribute("users"); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Users</title>
	</head>
	<body>
		<h1>Users</h1>
		<ul>
			<% for (User u : users) { %>
			<li><a href="/stealthmode/user?id=<%= u.getID() %>"><%= u.getFullname() %></a></li>
			<% } %>
		</ul>
	</body>
</html>