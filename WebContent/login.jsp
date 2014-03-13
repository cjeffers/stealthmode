
<%@ page language="java" contentType="text/html; charset=UTF-8" 
 pageEncoding="UTF-8"%>
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Welcome</title>
</head>
<body>

<% if(request.getParameter("username") != null){ %>
<%= request.getParameter("username")%>
<%}else{ %>
<% } %>
<h3></h3>

<p> Please Log in </p>

<form action="LoginServlet" method="post">
User Name: <input type="text" name="username"><br>
Password: <input type="password" name="password"><br>
<input type="submit" value="Login">
</form>
<br>
<a href="createaccount.html">Create New Account</a>
</body>
</html>