<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flasher JiāngHú Manage System</title>

<%@ include file = "link_db.jsp"  %>

</head>
<body>

      <%
         Connection conn; 
         ResultSet rs; 
         conn = getConnection(); 
         int flag = 0;
         request.setCharacterEncoding("utf-8");
         String str1 = request.getParameter("userName"),
                str2 = request.getParameter("userPW"),
                parmName = new String(str1.getBytes("ISO-8859-1"), "UTF-8"),
                parmPW = new String(str2.getBytes("ISO-8859-1"), "UTF-8"),
                sql = "select * from t_user Where userName ='"+parmName+"' and userPW ='"+parmPW+"'";
         rs = executeQuery(conn,sql);
         if ( rs.next() ) {
        	 response.setHeader("Refresh","2;URL=Backstage/homepage.jsp");
         }
         
         else  {
        	 String msg="Password or Username Error!";
			 request.getRequestDispatcher("Backstage/error.jsp?msg="+msg).forward(request, response);
         }

      %>
</body>
</html>