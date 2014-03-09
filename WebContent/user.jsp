<%@ page language="java" contentType="text/html; charset=UTF-8" 
 pageEncoding="UTF-8"%>
 <%@ page import="models.User" %> 
<% User user = (User) request.getAttribute("user"); %>
 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title><%= user.getUserName() %></title>
	</head>
	<body>
		<h1><%= user.getUserName() %></h1>
		<ul>
			<li>Full name: <%= user.getFullname() %></li>
		</ul>
		<a href="/stealthmode/users">See users</a>
	</body>
</html>