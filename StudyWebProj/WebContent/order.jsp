<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Book</title>
</head>
<body>
	<h1>Another E-Bookstore</h1>
	<h2>Thank you for ordering...</h2>
	<%
    String[] ids = request.getParameterValues("id");
    if (ids != null) {
  %>
	<%@ page import="java.sql.*"%>
	<%
    String CLASSFORNAME = "com.mysql.jdbc.Driver";
	String SERVANDDB = "jdbc:mysql://localhost:3306/test";
	String USER = "root";
	String PWD = "1234";
	
  Connection conn=null;
	try{
		Class.forName("com.mysql.jdbc.Driver");
		conn = (Connection) DriverManager
				.getConnection(SERVANDDB, USER, PWD);
		
		}catch(ClassNotFoundException cnfex){
		cnfex.printStackTrace();
		}
      Statement stmt = conn.createStatement();
      String sqlStr;
      int recordUpdated;
      ResultSet rset;
  %>
	<table border=1 cellpadding=3 cellspacing=0>
		<tr>
			<th>Author</th>
			<th>Title</th>
			<th>Price</th>
			<th>Qty In Stock</th>
		</tr>
		<%
      for (int i = 0; i < ids.length; ++i) {
        // Subtract the QtyAvailable by one
        sqlStr = "UPDATE books SET qty = qty - 1 WHERE id = " + ids[i];
        recordUpdated = stmt.executeUpdate(sqlStr);
        // carry out a query to confirm
        sqlStr = "SELECT * FROM books WHERE id =" + ids[i];
        rset = stmt.executeQuery(sqlStr);
        while (rset.next()) {
  %>
		<tr>
			<td><%= rset.getString("author") %></td>
			<td><%= rset.getString("title") %></td>
			<td>$<%= rset.getInt("price") %></td>
			<td><%= rset.getInt("qty") %></td>
		</tr>
		<%    }
        rset.close();
      }
      stmt.close();
      conn.close();
    }
  %>
	</table>
	<a href="query.jsp"><h3>BACK</h3></a>
</body>
</html>