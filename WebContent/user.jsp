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
			<li>Full name: <%= user.getFullName() %></li>
			<li><img src=<%= user.getPicURL() %>/></li>
			<li>Friends:
				<ul><% for (User f : user.getFriends()) { %>
					<li><a href="/user/<%= f.getID() %>"><%= f.getFullName() %></a></li>
					<% } %>
				</ul>
			</li>
		</ul>
	</body>
</html>