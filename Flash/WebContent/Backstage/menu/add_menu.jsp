<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu Manage System</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />

<script language="javascript">
   function checkValidateForm() {
	   if(ValidateForm.name.value==""||ValidateForm.name.value.replace(/(^\s*)|(\s*$)/g, "")=="")
       {
		   alert("菜單名称不能为空！");
		   ValidateForm.name.focus();
		   return false;
		   }
	   return true;
	   }
</script>

</head>
<body id="menu">

<div id="main">
	<jsp:include page="../header/header_link.jsp" flush="true"/>

	<div id="middle">
		<div id="left-column">
			<h3>菜单管理</h3>
			<ul class="nav">
				<li><a href="menu.jsp">菜单概览</a></li>
				<li class="last"><a href="add_menu.jsp">添加菜单</a></li>
			</ul>
		</div>
		<div id="center-column">
			<div class="top-bar">
				<h1>添加菜单</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 添加菜单</div>
			</div><br />
			
			<div class="select-bar"></div>
		  
			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				
				<form name="ValidateForm" onSubmit="return checkValidateForm();" 
				      method=post action="MENUINFO">
                  <input type=hidden name=cmd value="addMenu"> 
                  
				  <table class="listing form" cellpadding="0" cellspacing="0">                    
					<tr>
					  <th class="full" colspan="2">添加菜单信息</th>
					</tr>
                     
					<tr>
						<td class="first" width="172"><strong>【菜单名称】</strong></td>
						<td class="last"><input type="text" name="name" size="30"
						    maxlength="50"> </td>
				   </tr>
				   <tr class="bg">
						<td class="first"><strong>【超连接】</strong></td>
						<td class="last"><input type="text" name="url" maxlength="50" 
						    size="30"></td>
					</tr>
				</table>
				
				<div class="select">
				   <input type="reset" value="Reset" style="height:22px">
				   <input type="submit" value="Add" style="height:22px">
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
