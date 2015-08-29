<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.bean.Works,
                 java.util.Vector" %>
                 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Search Result</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="css/index.css" type="text/css"/>
<link rel="stylesheet" href="css/flash.css" type="text/css"/>
<jsp:include page="header.jsp" flush="true"/>
</head>

<body>


<div id="container">
  <div id="wrapper">
    <div id="content">
      <h1> 查询结果 </h1>

<%    
    int total = 0 ,count1 = 0, count2 =0;
    String level1 = new String(request.getParameter("level1").getBytes("ISO-8859-1"), "GB2312"),
           level2 = new String(request.getParameter("level2").getBytes("ISO-8859-1"), "GB2312"),
           level3 = new String(request.getParameter("level3").getBytes("ISO-8859-1"), "GB2312"),
           search1 = new String(request.getParameter("search1").getBytes("ISO-8859-1"), "GB2312"),
           search2 = new String(request.getParameter("search2").getBytes("ISO-8859-1"), "GB2312"),
           author = new String(request.getParameter("author").getBytes("ISO-8859-1"), "GB2312");
   
    level1 = level1.substring(3,level1.length());
    level2 = level2.substring(3,level2.length());
    level3 = level3.substring(3,level3.length());

   
    Works works = new Works();
    Vector worksVector = new Vector();
    worksVector = works.getAllWorks();
    if(worksVector!=null) total = worksVector.size();
    
    for(int i=0; i<total;i++) {
      Works tempWorks = (Works)worksVector.get(i);
      if( search1.equals("true")&&tempWorks.getLevel1().equals(level1) && tempWorks.getLevel2().equals(level2)&& 
    	  tempWorks.getLevel3().equals(level3)) {
          count1++;
          count2 = 1;
%>
   

       <div class="block">
         <div class="bot">  
         
              <div class="row-articles articles">
                <div class="cl">&nbsp;</div>
                  <div class="article">
                   <div class="cl">&nbsp;</div>
                      <div class="image">
                         <img src=<%=tempWorks.getPhoto() %> width=200 height=140 />
                      </div>
                       
                        <div class="cnt">
                          <h4>作品名称 : <%=tempWorks.getTitle() %></h4>
                          <p>作者 : <%=tempWorks.getAuthor() %>  ( <%=tempWorks.getLevel1()%> )</p>    
                          <p>作品類型 : <%=tempWorks.getLevel2() %> 
                                                                                           〖<%=tempWorks.getLevel3() %>〗                                                           
                          </p>
                          <p><a href=view_resource.jsp?path=<%=tempWorks.getPath() %> >
                                  <img src="css/images/view.png" />作品演示
                             </a></p>
                         </div>
                         
                      <div class="cl">&nbsp;</div>
                    </div>
                </div>  
               </div>
               </div>

  
    <% 
         } else if( search1.equals("false")&&tempWorks.getLevel1().equals(level1) && tempWorks.getLevel2().equals(level2)&& 
           	  tempWorks.getLevel3().equals(level3)&&tempWorks.getAuthor().equals(author)) {
        	  count1 = 1; 
        	  count2++;
     %>  	 
             <div class="block">
             <div class="bot">  
             
                  <div class="row-articles articles">
                    <div class="cl">&nbsp;</div>
                      <div class="article">
                       <div class="cl">&nbsp;</div>
                          <div class="image">
                             <img src=<%=tempWorks.getPhoto() %> width=200 height=140 />
                          </div>
                           
                            <div class="cnt">
                              <h4>作品名称 : <%=tempWorks.getTitle() %></h4>
                              <p>作者 : <%=tempWorks.getAuthor() %>  ( <%=tempWorks.getLevel1()%> )</p>    
                              <p>作品類型 : <%=tempWorks.getLevel2() %> 
                                                                                               〖<%=tempWorks.getLevel3() %>〗                                                           
                              </p>
                              <p><a href=view_resource.jsp?path=<%=tempWorks.getPath() %> >
                                      <img src="css/images/view.png" />作品演示
                                 </a></p>
                             </div>
                             
                          <div class="cl">&nbsp;</div>
                        </div>
                    </div>  
                   </div>
                   </div>
    <% 
         } 
      }
       if (count1==0||count2==0) {
    %> 
        <h4>找不到符合查询条件的作品信息!</h4>
     <% } %>    
        
        </div>  
           
        <div id="sidebar"> 
          <br>
          <jsp:include page="search.jsp" flush="true"/>       
        </div>
        
      <jsp:include page="footer.jsp" flush="true"/>
</div>
</div>
</body>
</html>
