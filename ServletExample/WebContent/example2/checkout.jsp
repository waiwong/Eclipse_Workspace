<%-- Checkout Page  --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" import="java.util.*, servlets.example2.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>E-BookShop Checkout</title>
</head>
<body>
	<h2>Yet Another E-Bookshop - Checkout</h2>
	<hr />
	<br />

	<p>
		<strong>You have ordered the following book(s):</strong>
	</p>
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<th>Title</th>
			<th>Author</th>
			<th>Price</th>
			<th>Quantity</th>
		</tr>
		<%
			// Scriptlet 1: Display the items in shopping cart
			List<CartItem> theCart = (List<CartItem>) session
					.getAttribute("cart");
			for (CartItem item : theCart) {
		%>
		<tr>
			<td><%=item.getTitle()%></td>
			<td><%=item.getAuthor()%></td>
			<td align="right">$<%=item.getPrice()%></td>
			<td align="right"><%=item.getQtyOrdered()%></td>
		</tr>
		<%
			} // for
			session.invalidate();
		%>
		<tr>
			<th align="right" colspan="2">Total</th>
			<th align="right">$<%=request.getAttribute("totalPrice")%></th>
			<th align="right"><%=request.getAttribute("totalQtyOrdered")%></th>
		</tr>
	</table>
	<br />

	<a href="shopping">Click here to buy more books!</a>
</body>
</html>