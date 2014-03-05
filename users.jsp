<%@ page language="java" contentType="text/html; charset=UTF-8" 
 pageEncoding="UTF-8"%>
 <%@ page import="//APPROPRIATEIMPORTS HERE" %> 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Users</title>
</head>
<body>
<h1>Users</h1>



<ul>
<%
List<User> users = User.getAll();
for(int i = 0; i < users.size(); i++){
	out.print("<li>");
	out.print("<a href='user/'" + "'>" + users.get(i).getUserName() + "</a>");
	out.println("</li>");
	
}

%>
</ul>
<br>
</body>
</html>