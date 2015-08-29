<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.bean.Flasher,
                 java.util.Vector" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flasher</title>
</head>

<link rel="stylesheet" href="css/index.css" type="text/css" />
<jsp:include page="header.jsp" flush="true"/>


<body>

<div id="container">
  <div id="wrapper">
    <div id="content">

    
      <%
      int total = 0 ;
      String swf = null,
             str = request.getParameter("type"),
             parm = new String(str.getBytes("ISO-8859-1"), "UTF-8");
      Flasher flasher = new Flasher();
      Vector flasherVector = new Vector();
      flasherVector = flasher.getAllFlasher();
      if(flasherVector!=null) total = flasherVector.size();
      out.print("<h1>"+parm+"列表</h1>");
      for(int i=0; i<total; i++) {
          Flasher tempFlasher = (Flasher)flasherVector.get(i);
          if(tempFlasher.getKind().equals(parm)) {
      %>


         <div class="group">
                 
          <% if(parm.equals("闪客工作室")) { %>
            <a href = "flasher_detail.jsp?id=<%=tempFlasher.getID() %>" >
              <img src=<%=tempFlasher.getPhoto() %> /><br>
              <%=tempFlasher.getTruename() %></a>
              
          <% } else { %>
           <a href = "flasher_detail.jsp?id=<%=tempFlasher.getID() %>" >
              <img src=<%=tempFlasher.getPhoto() %> /><br/>
              <%=tempFlasher.getNickname() %>
           </a>
         <% } %>
         
       </div>
       
    <%
       }
     } 
   %>
      <a class="backToTop" href="#">&nbsp;</a>        
    </div>
        
       
    <div id="sidebar">   
   <% 
     if(parm.equals("男闪客")) { 
       swf = "men.swf";
     }
     else if (parm.equals("女闪客")) {
	   swf = "women.swf";
     }
     else {
	   swf = "team.swf";
     }
   %>


       <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="250" height="330" id="FlashID">
         <param name="movie" value=<%=swf %> />
         <param name="quality" value="high" />
         <param name="wmode" value="transparent" />
         <param name="swfversion" value="9.0.45.0" />
 
       <!--[if !IE]>-->
       <object type="application/x-shockwave-flash" data=<%=swf%> width="250" height="330">
         <param name="quality" value="high" />
         <param name="wmode" value="transparent" />
         <param name="swfversion" value="9.0.45.0" /> 
       </object>
       <!--<![endif]-->
       </object>
    </div>
                        
    <jsp:include page="footer.jsp" flush="true"/>
  </div>
</div>

</body>
</html>