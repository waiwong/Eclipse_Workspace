<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.bean.Feature,
                 java.util.Vector,
                 java.util.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flash Feature</title>

<link rel="stylesheet" href="css/feature.css" type="text/css" />
<jsp:include page="header.jsp" flush="true"/>

</head>

<body>

   <%
      int total = 0 ;
      ArrayList<String> title = new ArrayList<String> (),
                        type = new ArrayList<String> (),
                        summary = new ArrayList<String> (),
                        photo = new ArrayList<String> ();
      Feature feature = new Feature();
      Vector featureVector = new Vector();
      featureVector = feature.getAllFeature();
      if(featureVector!=null) total = featureVector.size();
      
      for(int i=0; i<total;i++) {
    	  Feature tempFeature = (Feature)featureVector.get(i);
          title.add(tempFeature.getTitle());
          type.add(tempFeature.getType());
          summary.add(tempFeature.getSummary());
          photo.add(tempFeature.getPhoto());
      }
   %>

<div id="container">
  <div id="wrapper">
    <div id="text">
       

      <% 
         for(int i=0; i<total; i++) { 
           if (type.get(i).equals("Text")) { 
      %>
           
      <h1> <%=title.get(i) %> </h1>
      <img class="alignleft" src=<%=photo.get(i) %> ></img><br>
      <p> <%=summary.get(i).replaceAll("\n","<br><br>" ) %> </p>
      <a class="backToTop" href="#">&nbsp;</a> 
      <% }
           } 
      %>

           
      <h1>The Detail is about Flash Features</h1>
      <div id="container">
        <div id="container-b">
          <div class="shell">
            <div class="projects">
              <ul>
        
                      <li>          
                        <div class="project-info">
                        <br>
                            <h3>Adobe Flash 四个主要特征</h3>
                        <br>
                         <% for (int i=0; i<total; i++) {
                        	 if (type.get(i).equals("RollBar")) { %>
                           <h4> <%=title.get(i) %> </h4><br></br>
                         <% }
                        	 } 
                         %>         
                        </div>
            
                        <div class="project-swf">
                          <embed src="feature.swf"
                                 width=600 height=300 type=application/x-shockwave-flash  
                                 quality="high"/>              
                        </div>            
                      </li>

              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>    
   <jsp:include page="footer.jsp" flush="true"/>
  </div>
</div>
</body>
</html>