<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <%@ include file="links.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container">
            <% if (request.getSession().getAttribute("msg") != null) { %>
            <div class="alert">
                <%= request.getSession().getAttribute("msg") %>
                <% request.getSession().removeAttribute("msg"); %>
            </div>
            <% } %>
            <form action="/stealthmode/user/create" method="POST">
                <table>
                    <tr><td class="label">Username:</td><td class="input"><input type="text" name="username" /></td></tr>
                    <tr><td class="label">Password:</td><td class="input"><input type="password" name="password" /></td></tr>
                    <tr><td class="label">Confirm password:</td><td class="input"><input type="password" name="password_confirm" /></td></tr>
                    <tr><td class="label">Full name:</td><td class="input"><input type="text" name="fullname" /></td></tr>
                    <tr><td class="label">Profile picture URL:</td><td class="input"><input type="text" name="pic_url" /></td></tr>
                </table>
                <input type="submit" value="Create User" />
            </form>
        </div>
    </body>
</html>
