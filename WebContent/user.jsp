<%@ page language="java" contentType="text/html; charset=UTF-8" 
 pageEncoding="UTF-8"%>
 <%@ page import="models.User" %> 
<% User user = (User) request.getAttribute("user"); %>
 
<!DOCTYPE html>
<html>
	<head>
        <%@ include file="links.jsp" %>
		<meta charset="UTF-8" />
		<title><%= user.getUserName() %></title>
	</head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container">
            <h1><%= user.getUserName() %></h1>
            <ul>
                <li>Full name: <%= user.getFullname() %></li>
            </ul>
            <img class="profile" src="<%= user.getPicURL() %>" />
            <a href="/stealthmode/users">See users</a>
        </div>
    </body>
</html>
