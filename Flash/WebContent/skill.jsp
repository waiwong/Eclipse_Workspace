<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.bean.Skill,
                 java.util.Vector" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flash Skill</title>
</head>

<link rel="stylesheet" href="css/index.css" type="text/css"/>
<jsp:include page="header.jsp" flush="true"/>


<body>


<div id="container">
  <div id="wrapper">
    <div id="content">
    
      <%
        int total = 0 , h = 0;
        Skill skill = new Skill();
        Vector skillVector = new Vector();
        skillVector = skill.getAllSkill();
        if(skillVector!=null) total = skillVector.size();
        for(int i=0; i<total; i++) {
    	  Skill tempSkill = (Skill)skillVector.get(i);
          if(tempSkill.getType().equals("summary")) {
      %>
    
       
      <h1> <%= tempSkill.getLabel() %> </h1>
      <p> <%= tempSkill.getSummary().replaceAll("\n","<br><br>" ) %> </p>
      
      
      <% if(tempSkill.getID()==1)  h = 300;
         else h = 500;
      %>
      
        <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="600" height=<%=h %> id="FlashID">
          <param name="movie" value=<%=tempSkill.getPath() %> />
          <param name="quality" value="high" />
          <param name="wmode" value="opaque" />
          <param name="swfversion" value="9.0.45.0" />
 
          <!--[if !IE]>-->
          <object type="application/x-shockwave-flash" data=<%=tempSkill.getPath()%> width="600" height=<%=h %>>
            <param name="quality" value="high" />
            <param name="wmode" value="opaque" />
            <param name="swfversion" value="9.0.45.0" /> 
          </object>
          <!--<![endif]-->
        </object>
        
      <a class="backToTop" href="#">&nbsp;</a> 
      
      <%
         }
      }
      %> 
    </div>
      
      
    <div id="sidebar">
       <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="260" height="550" id="FlashID">
         <param name="movie" value="skill.swf" />
         <param name="quality" value="high" />
         <param name="wmode" value="transparent" />
         <param name="swfversion" value="9.0.45.0" />
 
       <!--[if !IE]>-->
       <object type="application/x-shockwave-flash" data="skill.swf" width="260" height="550">
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