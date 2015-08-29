<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Bar</title>

<script language = "JavaScript">

function CheckForm() {
	if (document.myform.searchType.value=="none") {
		alert("请选择搜索的标准！");
		document.myform.searchType.focus();
		return false;
	}
	if (document.myform.keyword.value==""||document.myform.keyword.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		alert("请填写必要的关键字！");
		document.myform.keyword.focus();
		return false;
	}	 
}

function clearText(field){
	if (field.defaultValue == field.value) field.value = '';
	else if (field.value == '') field.value = field.defaultValue;
	}
	window.addEvent('load', function(){
	new ByZoomer();	
	});
	
</script>

</head>
<body>

	<div class="select-bar">
	  <form action=search.jsp method=post name=myform onSubmit="return CheckForm();">
         <input type=hidden name=search value="form1">
         <select name=searchType>
           <option value="xxx">请选择搜索标准</option>
           <option value="第一代闪客">第一代闪客</option>
           <option value="第二代闪客">第二代闪客</option>
           <option value="新生代闪客">新生代闪客</option>
           <option value="国外闪客">国外闪客</option>
         </select>&nbsp;
         <input type=text size=30 name=keyword value="请输入作者名称"
                onfocus="clearText(this)" onblur="clearText(this)">&nbsp;
         <input type=submit value="Sumbit">
       </form>
	</div>

</body>
</html>