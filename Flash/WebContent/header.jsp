<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = " java.util.*,
                    jsp.sqlServer.bean.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Header</title>
</head>

<link rel="stylesheet" href="css/index.css" type="text/css"/>
<jsp:include page="login.jsp" flush="true"/>

<body>
    
   <div id="logo"> </div>
   <div id="container">
      <div id="wrapper">

         <ul id="menu">
             <li class="left">&nbsp;</li> 
              
      <%          
         ArrayList<String> index1 = new ArrayList<String> (),
                           index2 = new ArrayList<String> ();
         int all_m = 0, all_i = 0, all_w = 0,
             all_f = 0, total1 = 0, total2 = 0;
                 
         Works works = new Works();
         Vector worksVector = new Vector();
         worksVector = works.getLevel();
         if(worksVector!=null) all_w = worksVector.size();
         for(int i=0; i<all_w; i++) {
        	 Works tempWorks = (Works)worksVector.get(i);
        	 index1.add( tempWorks.getLevel1() );
        	 total1++;
         }
         
         Flasher flasher = new Flasher();
         Vector flasherVector = new Vector();
         flasherVector = flasher.getFlasherType();
         if(flasherVector!=null) all_f = flasherVector.size();
         for(int i=0; i<all_f; i++) {
        	 Flasher tempFlasher = (Flasher)flasherVector.get(i);
        	 index2.add( tempFlasher.getKind() );
        	 total2++;
         }
                  
         Menu menu = new Menu();
         Vector menuVector = new Vector();
         menuVector = menu.getAllMenu();
         if(menuVector!=null) all_m = menuVector.size();
         for(int i=0; i<all_m; i++) {
        	 Menu tempMenu = (Menu)menuVector.get(i);
         
      %>   
            <li> <a href= <%=tempMenu.getURL() %> >
                    <%=tempMenu.getMenu() %>  </a> 
                 
                 <%  if (tempMenu.getID() == 5 ) { %>   
                 <img src="css/images/arrow.jpg"/>                 
                       <ul>
                         <% for (int j=0; j<total1; j++) { %>
                          <li> <a href=<%=tempMenu.getURL() %><%=index1.get(j) %> >
                                       <%=index1.get(j) %></a> </li>
                                       
                         <% } %>
                       </ul>
                 <% } %> 
                 
                 <%  if(tempMenu.getID() == 7 ) { %>   
                 <img src="css/images/arrow.jpg"/>                 
                       <ul>
                         <% for (int j=0; j<total2; j++) { %>
                          <li> <a href=<%=tempMenu.getURL() %><%=index2.get(j) %> >
                                       <%=index2.get(j) %></a> </li>
                                       
                         <% } %>
                       </ul>
                 <% } %> 
            </li>
      <% } %> 
      
              <li class="right">&nbsp;</li>
          </ul>

    <!-- Header -->   
    <div id="header">
      <div class="intro">
      
       <%          
         Index index = new Index();
         Vector indexVector = new Vector();
         indexVector = index.getAllIndex();
         if(indexVector!=null) all_i = indexVector.size();
         for(int i=0; i<all_i; i++) {
        	 Index tempIndex = (Index)indexVector.get(i); 
        	 if ( tempIndex.getType().equals("banner") ) {
       %>
       
            <h1> <%=tempIndex.getTitle() %> </h1>
            <p> <%=tempIndex.getContent() %> </p>
         <% 
             }
           }
         %> 
      
      </div>
    </div>    
  </div>
</div>

</body>
</html>