<%@ page language="java" contentType="text/html; charset=UTF-8" 
 pageEncoding="UTF-8"%>
 <%@ page import="web.ShoppingCart, java.util.ArrayList" %> 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Shopping Cart</title>
</head>
<body>
<h1>Shopping Cart</h1>


<form method="post"  action="UpdateItemServlet">
<ul>
<%
ShoppingCart items = (ShoppingCart) getServletContext().getAttribute("cart");
ArrayList<Integer> quantities = items.quantity;
double total = 0;
for(int i = 0; i < quantities.size(); i++){
	out.print("<li>");
	out.print("<input type='input' name='itemQuant_" + items.getID(i) + "' value='" + quantities.get(i) + "'></input>");
	out.print(items.entryInfo(i));
	out.println("</li>");
}


%>
</ul>
<br>
Total: $ <%= String.format("%.2f", items.getTotal()) %>
<input type='submit' value='Update Cart'></input>


</form>
<a href="index.jsp">Continue Shopping</a>
</body>
</html>