<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Message Bar</title>
</head>

<body>
   <div id="right-column">
	   <strong class="h">Message</strong>
	   <%	
	      String message = (String)session.getAttribute("message");
          if (message != null) {
       %>
       
	     <div class="box"><b><%= message %></b></div>

       <%	
          session.removeAttribute("message");
       } else { 
       %>
       
       <div class="box"><b>正在等待管理员操作...</b></div>
       
       <%} %>
   </div>
</body>
</html>