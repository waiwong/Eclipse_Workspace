<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.bean.Works,
                 java.util.Vector,
                 java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Resource</title>

<link rel="stylesheet" href="css/index.css" type="text/css"/>
<jsp:include page="header.jsp" flush="true"/>


</head>

<body>
    <%         
       String str = request.getParameter("path"),
              parm = new String(str.getBytes("ISO-8859-1"), "UTF-8");
        int total = 0;
        ArrayList<String> title = new ArrayList<String> (),
                          author = new ArrayList<String> (),
                          pic = new ArrayList<String> (),
                          path = new ArrayList<String> ();
        String WorksName = "";
        Works works = new Works();
        Vector worksVector = new Vector();
        worksVector = works.getAllWorks();
        
        if(worksVector!=null) total = worksVector.size();
        for(int i=0; i<total; i++) {
          Works tempWorks = (Works)worksVector.get(i);
          title.add(tempWorks.getTitle());
          author.add(tempWorks.getAuthor());
          pic.add(tempWorks.getPhoto());
          path.add(tempWorks.getPath());
          if (tempWorks.getPath().equals(parm)) {
        	  WorksName = tempWorks.getTitle();
          }
        }  
     %>
 
 
<div id="container">
  <div id="wrapper">
    <div id="content">    

     <h2> 作品名称:&nbsp;<%out.print(WorksName); %> </h2>
     
     <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="640" height="600" id="FlashID">
        <param name="movie" value="<%=parm %>" />
        <param name="quality" value="high" />
        <param name="wmode" value="opaque" />
        <param name="swfversion" value="9.0.45.0" />
 
      <!--[if !IE]>-->
      <object type="application/x-shockwave-flash" data="<%=parm %>" width="640" height="600">
        <param name="quality" value="high" />
        <param name="wmode" value="opaque" />
        <param name="swfversion" value="9.0.45.0" /> 
      </object>
     <!--<![endif]-->
    </object>
    

    </div>   
    
     	
    <div id="sidebar"> 
      <h2><img src="css/images/label1.png"/>&nbsp;相关作品</h2>
        <ul id="flash">
        
        <% 
           for (int i=0; i<5; i++) { 
             int num = (int)(Math.random()*total);
        %>
        
        <li> <a href=view_resource.jsp?path=<%=path.get(num) %> > 
         <img src=<%=pic.get(num) %> alt="" width="54" height="50" /> </a>
          <h4>作品名称:<%=title.get(num) %></h4>
          <p>作者:<%=author.get(num) %></p>
        </li>
        
        <%} %>
      </ul>
      
      <br>
      <div class="comeback" onclick='window.history.go(-1);'>
         <img src="css/images/comeback.gif" width=98 height=35  />
      </div>
       
    </div>  
    
    
      <jsp:include page="footer.jsp" flush="true"/>
  </div>
</div>
</body>
</html>

