<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.bean.Feature_info,
                 jsp.sqlServer.bean.Feature,
                 java.util.Vector" %>
                     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Feature Detail</title>

<script src="js/SpryCollapsiblePanel.js" type="text/javascript"></script>
<link href="css/SpryCollapsiblePanel.css" rel="stylesheet" type="text/css" />
<link href="css/index.css" rel="stylesheet" type="text/css" />
<jsp:include page="header.jsp" flush="true"/>

</head>


<body>

<div id="container">
  <div id="wrapper">
    <div id="content">
    
 
   <%
      String str =  request.getParameter("id");
      int id = Integer.parseInt(str),
          total = 0 ,num = 0;
      Feature_info featureInfo = new Feature_info();
      Vector featureInfoVector = new Vector();
      featureInfoVector = featureInfo.getAllFeature_info();
      if(featureInfoVector!=null) total = featureInfoVector.size();
      
      for(int i=0; i<total;i++) {
    	  Feature_info tempFeatureInfo = (Feature_info)featureInfoVector.get(i);
    	  if ( tempFeatureInfo.getNO()==id ) {
    		  
   %>    
   
    
    <div id="CollapsiblePanel<%=num %>" class="CollapsiblePanel">
      <div class="CollapsiblePanelTab" tabindex="0">
        <%=tempFeatureInfo.getTitle() %>
      </div>
      <div class="CollapsiblePanelContent">
       <%=tempFeatureInfo.getSummary().replaceAll("\n","<br><br>") %>
      </div>
   </div>
   
<%
   num++;
  }
} 
%>
</div>

    <div id="sidebar">
      <h2>Flash 主要的四个特色</h2>
       <ul id="flash">
         
   <%
      int count = 0 ;
      Feature feature = new Feature();
      Vector featureVector = new Vector();
      featureVector = feature.getAllFeature();
      if(featureVector!=null) total = featureVector.size();
      
      for(int i=0; i<total;i++) {
    	  Feature tempFeature = (Feature)featureVector.get(i);
    	    if (tempFeature.getType().equals("RollBar")) {
   %>


        <li> <a href=<%=tempFeature.getURL() %>> 
        <img src=<%=tempFeature.getPhoto() %> alt="" width="54" /> </a>
          <h4><%=tempFeature.getTitle() %></h4>
          <p><%=tempFeature.getSummary().substring(0,15)+"..." %></p>
        </li>
        
        <%}} %>
      </ul>
    </div>   
      
      <jsp:include page="footer.jsp" flush="true"/>
  </div>
</div>


<script type="text/javascript">
<!--

var CollapsiblePanel = "";
var lenght = <%=num%>;

for (var i=0; i<lenght; i++) {
	CollapsiblePanel = "CollapsiblePanel"+i.toString();
	if (i==0) {
		 CollapsiblePanel = new Spry.Widget.CollapsiblePanel(CollapsiblePanel,{contentIsOpen:true});
	} else {
    CollapsiblePanel = new Spry.Widget.CollapsiblePanel(CollapsiblePanel,{contentIsOpen:false});
}}
//-->
</script>

</body>
</html>
