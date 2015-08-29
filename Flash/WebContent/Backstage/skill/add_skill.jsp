<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Skill Manage System</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />

<script language="javascript">

function checkValidateForm() {
	   if(ValidateForm.label.value==""||ValidateForm.label.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("技巧主题不能为空！");
		   ValidateForm.label.focus();
		   return false;
		   }
	   if(ValidateForm.sublabel.value==""||ValidateForm.sublabel.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("技巧名称不能为空！");
		   ValidateForm.sublabel.focus();
		   return false;
		   }
	   if(ValidateForm.no.value==""||ValidateForm.no.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("索引鍵不能为空！");
		   ValidateForm.no.focus();
		   return false;
		   }
	   if(ValidateForm.type.value==""||ValidateForm.type.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("技巧的类型不能为空！");
		   ValidateForm.type.focus();
		   return false;
		   }
	   if(ValidateForm.summary.value==""||ValidateForm.summary.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("教程內容不能为空！");
		   ValidateForm.summary.focus();
		   return false;
		   }
	   if(ValidateForm.code.value==""||ValidateForm.code.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("教程中代碼不能为空！");
		   ValidateForm.code.focus();
		   return false;
		   }
	   if(!regex.test(ValidateForm.path.value)) {
		   alert("请选择图片类型或*.swf类型的文档！\n(*.jpg/*.jpeg/*.png/*.bmp/*.gif/*.swf)");
		   return false;
		   } 
	   }

</script>

</head>
<body id="skill">

<div id="main">
	<jsp:include page="../header/header_link.jsp" flush="true"/>
	<div id="middle">
	
		<div id="left-column">
			<h3>Flash 实用技巧管理</h3>
			<ul class="nav">
				<li><a href="skill.jsp">实用技巧概览</a></li>
				<li class="last"><a href="add_skill.jsp">添加实用技巧</a></li>
			</ul>
		</div>
		
		<div id="center-column">
			<div class="top-bar">
				<h1>添加实用技巧</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 添加实用技巧</div>
			</div><br />
			
			<div class="select-bar"></div>
		  
			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				
				<form name="ValidateForm" onSubmit="return checkValidateForm();" 
				      method=post action="SKILLINFO">
                  <input type=hidden name=cmd value="addSkill"> 
                  
				  <table class="listing form" cellpadding="0" cellspacing="0">  
				  	<tr>
					  <th class="full" colspan="2">添加实用技巧信息</th>
					</tr>

					<tr class="bg">
						<td class="first" width="185"><strong>【索 引 鍵】</strong></td>
						<td class="last"><input type="text" name="no" size="30" > </td>
				   </tr>				                    
					<tr>
						<td class="first"><strong>【实用技巧主题】</strong></td>
						<td class="last"><input type="text" name="label" size="30" > </td>
				   </tr>
				   <tr class="bg">
						<td class="first"><strong>【技巧名称】</strong></td>
						<td class="last"><input type="text" name="sublabel" maxlength="50" 
						    size="30"></td>
				   </tr>
				   <tr>
						<td class="first"><strong>【类 型】</strong></td>
						<td class="last"><input type="text" name="type" maxlength="50" 
						    size="30"></td>
				   </tr>
				   <tr class="bg">
						<td class="first"><strong>【教程內容】</strong></td>
						<td class="last"><textarea name="summary" cols=60 rows=5>
						    </textarea></td>
				   </tr>
				   <tr>
						<td class="first"><strong>【教程中代碼】</strong></td>
						<td class="last"><textarea name="code" cols=60 rows=5>
						    </textarea></td>
				   </tr>
				   <tr class="bg">
						<td class="first"><strong>【图片/SWF路徑】</strong></td>
						<td class="last"><input type="file" name="path" maxlength="50" 
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
