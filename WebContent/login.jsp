<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="links.jsp" %>
        <meta charset="UTF-8" />
        <title>Welcome</title>
    </head>
    <body>
        <div class="container">
            <% if (request.getSession().getAttribute("msg") != null) { %>
            <div class="alert">
                <%= request.getSession().getAttribute("msg") %>
                <% request.getSession().removeAttribute("msg"); %>
            </div>
            <% } %>
            <p> Please Log in </p>
            <form action="/stealthmode/user/login" method="post">
                User Name: <input type="text" name="username"><br>
                Password: <input type="password" name="password"><br>
                <input type="submit" value="Login">
            </form>
            <br>
            <a href="/user/create">Create New Account</a>
        </div>
    </body>
</html>
