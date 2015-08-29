<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.bean.Flasher,
                 java.util.Vector"%>                
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Flasher</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />

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

<body id="flasher">


<%
   request.setCharacterEncoding("UTF-8");
   String searchType = null;
   if((String)session.getAttribute("usearchType")!=null )//查看session中是否有设定searchType
	   searchType = (String)session.getAttribute("usearchType");//如果有则取session中的值
	   String stype = request.getParameter("searchType");

	   if(stype!=null) { //如果用户有设定（更改）
		   searchType = stype;//将searchType改为用户设定值 
		   session.setAttribute("usearchType",stype);//改变session中的searchType
		   }

	   String keyword = null;
	   if( (String)session.getAttribute("ukeyword")!=null )//查看session中是否有设定keyword
		   keyword = (String)session.getAttribute("ukeyword");//如果有则取session中的值
		   String kword=request.getParameter("keyword");
		   if( kword!=null ) { //如果用户有设定（更改）
			   keyword = kword;//将keyword改为用户设定值 
			   session.setAttribute("ukeyword",kword);//改变session中的keyword
			   }

		   Flasher flasher = new Flasher();
		   Vector flasherVector=new Vector();

		   int flag = 0;
//判断是否有搜索结果要显示
   String search=request.getParameter("search");
   if(search!=null) {
	   flag = 1;
	   if( search.equals("form1") ) {
		   flasherVector = flasher.searchFlasher(searchType,keyword);
		   }

	   if( search.equals("pages") ) {
		   flag = 1;
		   if(searchType!=null&&!searchType.equals("none")&&keyword!=null&&!keyword.trim().equals(""))
			   {
			     flasherVector = flasher.searchFlasher(searchType,keyword);
			   }	
		   }
	   }
   
   if( flag==1 ) {
	int pagerows = 10;//每页显示的信息数目
	if ( (String)session.getAttribute("pagerows")!=null ) //查看session中是否有设定页显示数
	     pagerows = Integer.parseInt((String)session.getAttribute("pagerows")); //如果有则取session中的值

	String prows = request.getParameter("pagerows"); //请求用户设定的页显示数
	if( prows != null&&!prows.trim().equals("")&&!prows.trim().equals("0") ) { //如果用户有设定（更改）
	    pagerows=Integer.parseInt(prows);//将页显示数改为用户设定值 
	    session.setAttribute("pagerows",prows);//改变session中的页显示数
	   }
%>

<div id="main">
	<jsp:include page="../header/header_link.jsp" flush="true"/>

	<div id="middle">
		<div id="left-column">
			<h3>闪客管理</h3>
			<ul class="nav">
				<li><a href="flasher.jsp">闪客概览</a></li>
				<li class="last"><a href="add_flasher.jsp">添加闪客信息</a></li>
			</ul>
		</div>
		
		
		<div id="center-column">
			<div class="top-bar">
				<form name=pageset action="search.jsp" method=post class="page" >
				  <font size=-1>每页显示</font>
                  <input type=text size=1 maxlength=3 name=pagerows 
                         value=<%=pagerows%> onkeyup="value=value.replace(/[^\d]/g,'')" 
                         style="height:20px">
                  <font size=-1>&nbsp;条信息</font>&nbsp;&nbsp;
                  <input type=submit value="设定" style="width:50px;height:25px" title="设定">
               </form>
				
				<h1>资源概览</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 查询闪客资讯</div>
			</div><br />
			
			
			<div class="select-bar">
			   <form action=search.jsp method=post name=myform onSubmit="return CheckForm();">
                 <input type=hidden name=search value="form1">
                   <select name=searchType>
                     <option value="none">请选择搜索标准</option>
                     <option value="男闪客">男闪客</option>
                     <option value="女闪客">女闪客</option>
                     <option value="闪客工作室">闪客工作室</option>
                   </select>&nbsp;
                   <input type=text size=30 name=keyword value="请输入作者名称"
                          onfocus="clearText(this)" onblur="clearText(this)">&nbsp;
                   <input type=submit value="Sumbit">
                </form>
		     </div>


			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				<table class="listing" cellpadding="0" cellspacing="0">
					<tr>
						<th class="first">ID</th>
						<th >闪客/工作室名称</th>
						<th >类別</th>
						<th >主要作品</th>
						<th >Edit</th>
						<th class="last">Delete</th>
					</tr>

<%
   if(flasherVector!=null) {
   int pageid,
       allrows = 0,
       pagecount = 0;
   String s = request.getParameter("pageid");
   
   if( s==null ) {
    pageid = 1; 
   } else  {
       pageid=Integer.parseInt(s);
     }
   
   if(flasherVector!=null) allrows=flasherVector.size();   
      pagecount=(allrows+pagerows-1)/pagerows;
      if(pagecount==0) pageid = 0;
      if(pageid>pagecount) pageid = pagecount;
      if(pagecount>0&&pageid<=0) pageid = 1;	

      if(pageid<pagecount&&pageid>0) {
        for(int i=0; i<pagerows;i++) {
           Flasher tempFlasher = (Flasher)flasherVector.get(pagerows*(pageid-1)+i);
           int num = pagerows*(pageid-1)+i+1;
           if ( num%2==0 ) {
%>

		 <tr>
	   <%  } else { %>
		 <tr class="bg">
	   <%  }  %>
			<td align="center"><%=num%></td>
			<td><a href="view_flasher.jsp?fid=<%=tempFlasher.getID()%>"><font size="-1">
			    <%=tempFlasher.getTruename()%></font></a>
			</td>
			<td><%= tempFlasher.getKind()%></td>
			<td><%= tempFlasher.getClassic()%></td>
			<td><a href="edit_flasher.jsp?fid=<%=tempFlasher.getID()%>">
	            <img src="../css/images/edit.png" title="编辑<%=tempFlasher.getTruename()%>的属性"
	                 alt="编辑<%=tempFlasher.getTruename()%>的属性" border="0"></a>
	        </td>
			<td><a href="FLASHERINFO?cmd=delFlasher&fid=<%=tempFlasher.getID()%>" onclick='if(window.confirm("操作不能恢复，您确定要删除此信息？")){return true;}else{return false;}'>
	            <img src="../css/images/rubbish.png" title="删除<%=tempFlasher.getTruename()%>这一栏目 "
	                 alt="删除<%=tempFlasher.getTruename()%>这一栏目 " border="0"></a>
	        </td>
		 </tr>


<% 
      }
}
 else if( pageid==pagecount&&pageid>0 ) {
   for(int i=0; i<flasherVector.size()-pagerows*(pagecount-1);i++) {
      Flasher tempFlasher = (Flasher)flasherVector.get(pagerows*(pageid-1)+i);
      int num = pagerows*(pageid-1)+i+1;
      if ( num%2==0 ) {
%>

		 <tr>
	   <%  } else { %>
		 <tr class="bg">
	   <%  }  %>
			<td align="center"><%=num%></td>
			<td><a href="view_flasher.jsp?fid=<%=tempFlasher.getID()%>"><font size="-1">
			    <%=tempFlasher.getTruename()%></font></a>
			</td>
			<td><%= tempFlasher.getKind()%></td>
			<td><%= tempFlasher.getClassic()%></td>
			<td><a href="edit_flasher.jsp?fid=<%=tempFlasher.getID()%>">
	            <img src="../css/images/edit.png" title="编辑<%=tempFlasher.getTruename()%>的属性"
	                 alt="编辑<%=tempFlasher.getTruename()%>的属性" border="0"></a>
	        </td>
			<td><a href="FLASHERINFO?cmd=delFlasher&fid=<%=tempFlasher.getID()%>" onclick='if(window.confirm("操作不能恢复，您确定要删除此信息？")){return true;}else{return false;}'>
	            <img src="../css/images/rubbish.png" title="删除<%=tempFlasher.getTruename()%>这一栏目 "
	                 alt="删除<%=tempFlasher.getTruename()%>这一栏目 " border="0"></a>
	        </td>
		 </tr>
<% 
    }
  }
%>	

         <td align="right" colspan="8"><font size="-1">
                               每页显示<font color=blue> <b><%=pagerows%></b> </font>条信息，总共
            <font color=blue> <b><%=allrows%></b> </font>条信息<br>
                              当前页：<font color=blue><b><%=pageid%></b> </font>/<font color=blue> <b><%=pagecount%></b> </font>
           <% if (pageid>1) { %>
              <a href="search.jsp?pageid=<%=pageid-1%>">上一页</a> 
           <% }
           
             if(pageid<pagecount) { %>
              <a href="search.jsp?pageid=<%=pageid+1%>">下一页</a> 
           <% } %>
           
              &nbsp;转到第<SELECT onChange="javascript:window.location='search.jsp?pageid='+this.options[this.selectedIndex].value;" size=1 name=page>
	       <%	
	          int p = 1;
		      while( p<=pagecount ) {
			   if( p==pageid )
			      out.println("<option value="+p+" selected>"+p);
		       else out.println("<option value="+p+">"+p);
		       p = p+1;
		    } %>
		  </select>页</font>
         </td>	
         <%} %>
      </table>
      <%} %>
     </div>
   </div>
   
       <jsp:include page="../messagebar.jsp" flush="true"/>
    </div>
  <div id="footer"></div>
</div>
</body>
</html>