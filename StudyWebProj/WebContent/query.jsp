<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Query</title>
</head>
<body>
	<h1>Another E-Bookstore</h1>
	<h3>Choose Author(s):</h3>
	<form method="get">
		<input type="checkbox" name="author" value="Tan Ah Teck">Tan <input
			type="checkbox" name="author" value="Mohammad Ali"
		>Ali <input type="checkbox" name="author" value="Kumar">Kumar
		<input type="checkbox" name="author" value="Kevin Jones">Kevin
		<input type="submit" value="Query">
	</form>
	<%
	String choosedAuthor="";
    String[] authors = request.getParameterValues("author");
    if (authors != null) {
      for (int i = 0; i < authors.length; ++i) {
    	  choosedAuthor += ", " + authors[i];  
      } 
    }
  %>
	<p>
		You have choose author
		<%= choosedAuthor %></p>
	<%
    //String[] authors = request.getParameterValues("author");
    if (authors != null) {
  %>
	<%@ page import="java.sql.DriverManager"%>
	<%@ page import="java.sql.ResultSet"%>
	<%@ page import="com.mysql.jdbc.Connection"%>
	<%@ page import="com.mysql.jdbc.PreparedStatement"%>
	<%@ page import="com.mysql.jdbc.Statement"%>
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
	
      Statement stmt =(Statement)conn.createStatement();
 
      String sqlStr = "SELECT * FROM books WHERE author IN (";
      sqlStr += "'" + authors[0] + "'";  // First author
      for (int i = 1; i < authors.length; ++i) {
         sqlStr += ", '" + authors[i] + "'";  // Subsequent authors need a leading commas
      }
      sqlStr += ") AND qty > 0 ORDER BY author ASC, title ASC";
 
      // for debugging
      System.out.println("Query statement is " + sqlStr);
      ResultSet rset = stmt.executeQuery(sqlStr);
  %>
	<hr>
	<form method="get" action="order.jsp">
		<table border=1 cellpadding=5>
			<tr>
				<th>Order</th>
				<th>Author</th>
				<th>Title</th>
				<th>Price</th>
				<th>Qty</th>
			</tr>
			<%
      while (rset.next()) {
        int id = rset.getInt("id");
  %>
			<tr>
				<td><input type="checkbox" name="id" value="<%= id %>"></td>
				<td><%= rset.getString("author") %></td>
				<td><%= rset.getString("title") %></td>
				<td>$<%= rset.getInt("price") %></td>
				<td><%= rset.getInt("qty") %></td>
			</tr>
			<%
      }
  %>
		</table>
		<br> <input type="submit" value="Order"> <input
			type="reset" value="Clear"
		>
	</form>
	<a href="<%= request.getRequestURI() %>"><h3>Back</h3></a>
	<%
      rset.close();
      stmt.close();
      conn.close();
    }
  %>
</body>
</html>