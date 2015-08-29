<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP List Users Records</title>
</head>
<body>
	<sql:setDataSource var="myDS" driver="org.sqlite.JDBC"
		url="jdbc:sqlite:C:/WW_Work_Dir/tst4Java.db" />

	<sql:query var="listUsers" dataSource="${myDS}">
		SELECT * FROM users;
	</sql:query>

	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<H2>List of users</H2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Profession</th>
			</tr>
			<c:forEach var="user" items="${listUsers.rows}">
				<tr>
					<td><c:out value="${user.id}" /></td>
					<td><c:out value="${user.name}" /></td>
					<td><c:out value="${user.email}" /></td>
					<td><c:out value="${user.profession}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>