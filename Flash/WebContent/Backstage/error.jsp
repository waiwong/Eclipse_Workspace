<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>Flasher JiāngHú Manage System</title>
</head>
<body bgcolor="#ffffff" text="#000000" link="#0000ff" vlink="#0000ff" alink="#6699cc">

<%
	String msg = request.getParameter("msg") ;
%>
	
<div align="center"> 错誤信息:<br>
   <span><%=msg%></span> 
   <br><br>
   <div class="error">
     <input type='button' value='返回重试'  onclick='window.history.go(-1);'>　　
     <input type='button' value='系统首页'  onclick="window.location.href='../index.jsp'"> 
   </div>
</div>

         




</body>
</html>
