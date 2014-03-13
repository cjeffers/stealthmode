<%@ page language="java" contentType="text/html; charset=UTF-8" 
 pageEncoding="UTF-8"%>
 <%@ page import="models.User, models.Message, java.util.*" %> 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Inboxt</title>
</head>
<body>
<h1>Messages Recieved</h1>




<%
User me = (User)request.getSession().getAttribute("user");
List<Message> inbox = me.seeMessages();

%>

<% for(Message a:inbox){ %>
From: <%=a.getSender()%> <br>
Text: <%=a.getText() %>
<%} %>
</body>
</html>