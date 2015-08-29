<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  page  import="java.sql.*"  %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Link Database</title>
</head>

<body>

<%! 
  boolean verbose = true;  //判斷是否冗長
   
  public void log(String s)  {  
    if (verbose)  System.out.println("==>do register log:" + s);  
  }  
   
  public  Connection getConnection()   {  
    try {  
    	  /* Class.forName("org.gjt.mm.mysql.Driver").newInstance(); 
           return DriverManager.getConnection("jdbc:mysql://localhost:3306/info","root","kunwa59"); */
        //String   driverName   =   "com.microsoft.sqlserver.jdbc.SQLServerDriver";     //加载JDBC驱动
        String   dbURL   =   "jdbc:sqlserver://localhost:1433;DatabaseName=info";
        return DriverManager.getConnection(dbURL,"sa","12345678");
        }   catch   (  Exception  e  )   {  
               log("create con  exception:" + e.toString());  
               return   null;  
            }  
  }  
   
  public  ResultSet executeQuery(Connection con, String   sql)   {  
    PreparedStatement ps = null;  
    try {  
          ps  =  con.prepareStatement(sql);  
          return  ps.executeQuery();  
        }  catch  ( Exception e  )   {  
            log("execute  query  error  e:   "  +   e.toString   ());  
            return   null;  
           }  
   }  
%> 

</body>
</html>