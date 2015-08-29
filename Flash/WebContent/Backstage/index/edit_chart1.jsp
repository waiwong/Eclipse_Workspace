<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.bean.Chart1,
                 java.util.Vector" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chart1 Manage System</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />

<script language="javascript">

   function checkValidateForm() {
	   var regex =  /\d+(\.\d+){0,1}\%/;
	   if(ValidateForm.name.value==""||ValidateForm.name.value.replace(/(^\s*)|(\s*$)/g, "")=="")
       {
		   alert("多媒体播放器名称不能为空！");
		   ValidateForm.name.focus();
		   return false;
		   }

       if(!regex.test(ValidateForm.percent.value)) {
	      alert("请输入有效的百分比!\n如:10.0%");	 
	      return false;
	   }
return true;
}
   
</script>

</head>
<body id="index">

<%
   String cId = request.getParameter("cid");
   Chart1 chart1 = new Chart1();
   chart1.setID(Integer.parseInt(cId));
   chart1 = chart1.getChart1ByChart1ID();
%>

<div id="main">
	<jsp:include page="../header/header_link.jsp" flush="true"/>
	<div id="middle">
	   <jsp:include page="sidebar.jsp" flush="true"/>
		
		<div id="center-column">
			<div class="top-bar">
				<h1>修改缐狀图信息</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 修改缐狀图信息</div>
			</div><br />
			
			<div class="select-bar"> </div>
		  
			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				
				<form name="ValidateForm" onSubmit="return checkValidateForm();" 
				      method=post action="CHART1INFO?cid=<%=cId%>">
                  <input type=hidden name=cmd value="editChart1Info"> 
                  
				  <table class="listing form" cellpadding="0" cellspacing="0">                    
					<tr>
					  <th class="full" colspan="2"><%=chart1.getName()%>的详细信息</th>
					</tr>
                     
					<tr>
						<td class="first"><strong>【多媒体播放器名称】</strong></td>
						<td class="last"><input type="text" name="name" size="30" 
						    value="<%=chart1.getName()%>"> </td>
				   </tr>
				   <tr class="bg">
						<td class="first"><strong>【在市場上所占百分比】</strong></td>
						<td class="last"><input type="text" name="percent" maxlength="50" 
						    size="30" value="<%=chart1.getPercent()%>"></td>
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
