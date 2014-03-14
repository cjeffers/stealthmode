<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, models.User" %> 
<% List<User> users = (List<User>) request.getAttribute("users"); %>

<!DOCTYPE html>
<html>
	<head>
        <%@ include file="links.jsp" %>
		<meta charset="UTF-8" />
		<title>Users</title>
	</head>
	<body>
        <%@ include file="header.jsp" %>
        <div class="container">
            <h1>Users</h1>
                <% for (User usr : users) { %>
                <div>
                    <a href="/stealthmode/user?id=<%= usr.getID() %>"><%= usr.getFullname() %></a>
                </div>
                <% } %>
        </body>
    </div>
</html>
