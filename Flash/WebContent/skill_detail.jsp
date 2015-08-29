<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.bean.Skill,
                 java.util.Vector" %>
                 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Skill Detail</title>
</head>

<link rel="stylesheet" href="css/index.css" type="text/css"/>
<jsp:include page="header.jsp" flush="true"/>

<script type="text/javascript">

function openShutManager(oSourceObj,oTargetObj,shutAble,oOpenTip,oShutTip){
var sourceObj = typeof oSourceObj == "string" ? document.getElementById(oSourceObj) : oSourceObj;
var targetObj = typeof oTargetObj == "string" ? document.getElementById(oTargetObj) : oTargetObj;
var openTip = oOpenTip || "";
var shutTip = oShutTip || "";
if(targetObj.style.display!="none"){
   if(shutAble) return;
   targetObj.style.display="none";
   if(openTip  &&  shutTip){
    sourceObj.innerHTML = shutTip;
   }
} else {
   targetObj.style.display="block";
   if(openTip  &&  shutTip){
    sourceObj.innerHTML = openTip;
   }
}
}
</script>

<body>


<div id="container">
  <div id="wrapper">
    <div id="content">
    
    
      <%
         int total = 0;
         String str = request.getParameter("sid");
         int id = Integer.parseInt(str);       
         Skill skill = new Skill();
         Vector skillVector = new Vector();
         skillVector = skill.getAllSkill();
         if(skillVector!=null) total = skillVector.size();
         for(int i=0; i<total; i++) {
     	   Skill tempSkill = (Skill)skillVector.get(i);
           if( tempSkill.getID()==id ) {
      %>
 
      
      <h1> <%= tempSkill.getsubLabel() %> </h1>
      <p>  <%= tempSkill.getSummary().replaceAll("\n","<br>" ) %> </p>
      <br />
          
      <p>代碼运行结果:</p>
    <%
       String regex = "^\56*?\56(swf)$";
       if ( tempSkill.getPath().matches(regex) ) { %>
         <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="450" height="400" id="FlashID">
           <param name="movie" value=<%=tempSkill.getPath() %> />
           <param name="quality" value="high" />
           <param name="wmode" value="opaque" />
           <param name="swfversion" value="9.0.45.0" />

         <!--[if !IE]>-->
         <object type="application/x-shockwave-flash" data=<%=tempSkill.getPath()%> width="450" height="400">
           <param name="quality" value="high" />
           <param name="wmode" value="opaque" />
           <param name="swfversion" value="9.0.45.0" /> 
         </object>
         <!--<![endif]-->

         </object>
         
     <%  
       } else {
    	  %>
    	  <img src="<%=tempSkill.getPath() %>"/>
    <% }  %>
    
      <br><br>
      <p>  <button onclick="openShutManager(this,'box',false,'关闭代碼','展开代碼')">展开代碼</button></p>
           <p id="box" style="display:none">
                   <%= tempSkill.getCode().replaceAll("\n","<br>" ) %>
           </p>

        
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