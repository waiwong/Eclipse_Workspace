<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.bean.Chart2,
                 java.util.Vector" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chart2 Manage System</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />

<script language="javascript">

var regex = /^\d+\.\d+$/;
function checkValidateForm() {
	   if(ValidateForm.region.value==""||ValidateForm.region.value.replace(/(^\s*)|(\s*$)/g, "")=="")
    {
		   alert("调查地区名称不能为空！");
		   ValidateForm.region.focus();
		   return false;
		   }
	   
	   if(!regex.test(ValidateForm.fp10.value)) {
		   alert("请输入一个0-9的浮点型数字!\n如:5.0");	 
		   return false;
		   }

       if(!regex.test(ValidateForm.fp9.value)) {
	       alert("请输入一个0-9的浮点型数字!\n如:5.0");	 
	       return false;
	   }

      if(!regex.test(ValidateForm.fp8.value)) {
	       alert("请输入一个0-9的浮点型数字!\n如:5.0");	 
	       return false;
	   }
}

</script>

</head>
<body id="index">

<%
   String cId = request.getParameter("cid");
   Chart2 chart2 = new Chart2();
   chart2.setID(Integer.parseInt(cId));
   chart2 = chart2.getChart2ByChart2ID();
%>

<div id="main">
	<jsp:include page="../header/header_link.jsp" flush="true"/>
	<div id="middle">
	   <jsp:include page="sidebar.jsp" flush="true"/>
		
		<div id="center-column">
			<div class="top-bar">
				<h1>修改柱狀图信息</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 修改柱狀图信息</div>
			</div><br />
			
			<div class="select-bar"> </div>
		  
			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				
				<form name="ValidateForm" onSubmit="return checkValidateForm();" 
				      method=post action="CHART2INFO?cid=<%=cId%>">
                  <input type=hidden name=cmd value="editChart2Info"> 
                  
				  <table class="listing form" cellpadding="0" cellspacing="0">                    
					<tr>
					  <th class="full" colspan="2"><%=chart2.getRegion()%>地区的调查信息</th>
					</tr>
                     
					<tr>
						<td class="first"><strong>【调查地区】</strong></td>
						<td class="last"><input type="text" name="region" size="30" 
						    value="<%=chart2.getRegion()%>"> </td>
				   </tr>
				   
				   <tr bgcolor=#F3F3F3>
                      <td class="line" colspan="2"> 
                         Flash Player 某一版本在該地区中所占百分比 </td>
                   </tr> 
				   
				   <tr class="bg">
						<td class="first"><strong>【Flash Player 10】</strong></td>
						<td class="last"><input type="text" name="fp10" maxlength="50" 
						    size="30" value="<%=chart2.getFP10()%>"></td>
					</tr>
					
					<tr>
						<td class="first"><strong>【Flash Player 9】</strong></td>
						<td class="last"><input type="text" name="fp9" maxlength="50" 
						    size="30" value="<%=chart2.getFP9()%>"></td>
					</tr>
					
					<tr class="bg">
						<td class="first"><strong>【Flash Player 8】</strong></td>
						<td class="last"><input type="text" name="fp8" maxlength="50" 
						    size="30" value="<%=chart2.getFP8()%>"></td>
					</tr>
				</table>
				
				<div class="select">
				   <input type="reset" value="Reset" style="height:22px">
				   <input type="submit" value="Modify" style="height:22px">
				</div>
			  </form>
		   </div>			  				
        </div>

      <jsp:include page="../messagebar.jsp" flush="true"/>
     </div>
   <div id="footer"></div>
</div>
</body>
</html>
