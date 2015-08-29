<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.bean.Skill,
                 java.util.Vector" %>
                 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Skill Manage System</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />
</head>
<body id="skill">

<%

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
			<h3>Flash 实用技巧管理</h3>
			<ul class="nav">
				<li><a href="skill.jsp">实用技巧概览</a></li>
				<li class="last"><a href="add_skill.jsp">添加实用技巧</a></li>
			</ul>
		</div>
		
		<div id="center-column">
			<div class="top-bar">
				<form name=pageset action="skill.jsp" method=post class="page" >
				  <font size=-1>每页显示</font>
                  <input type=text size=1 maxlength=3 name=pagerows 
                         value=<%=pagerows%> onkeyup="value=value.replace(/[^\d]/g,'')" 
                         style="height:20px">
                  <font size=-1>&nbsp;条信息</font>&nbsp;&nbsp;
                  <input type=submit value="设定" style="width:50px;height:25px" title="设定">
               </form>
				
				<h1>实用技巧概览</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 实用技巧概览</div>
			</div><br />
			
			<div class="select-bar"></div>

			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				<table class="listing" cellpadding="0" cellspacing="0">
					<tr>
						<th class="first">ID</th>
						<th >Flash 实用技巧名称</th>
						<th >类型</th>
						<th >实用技巧教程</th>
						<th >Edit</th>
						<th class="last">Delete</th>
					</tr>

<%
   Skill skill = new Skill();
   Vector skillVector = new Vector();
   skillVector=skill.getAllSkill();
   
   int pageid,
       allrows = 0,
       pagecount = 0;
   
   String s = request.getParameter("pageid");
   if( s==null ) {
     pageid = 1;
   } else {
       pageid = Integer.parseInt(s);
     }
   if(skillVector!=null) allrows = skillVector.size();
    
   pagecount = (allrows+pagerows-1)/pagerows;
   if(pagecount==0) pageid = 0;
   if(pageid>pagecount) pageid = pagecount;
   if(pagecount>0&&pageid<=0) pageid = 1;	

   if(pageid<pagecount&&pageid>0){
      for(int i=0; i<pagerows;i++) {
        Skill tempSkill = (Skill)skillVector.get(pagerows*(pageid-1)+i);
        int num = pagerows*(pageid-1)+i+1;
        if ( num%2==0 ) {
%>

		 <tr>
	   <%  } else { %>
		 <tr class="bg">
	   <%  }  %>
			<td align="center"><%=num%></td>
			<td><a href="view_skill.jsp?sid=<%=tempSkill.getID()%>"><font size="-1">
			
			<%
			  if(tempSkill.getID()<4) {
			     out.print(tempSkill.getLabel());
			   } else { 
				   out.print(tempSkill.getsubLabel());
			  }
			%>
			</font></a></td>
			
			<td><%= tempSkill.getType()%></td>
			
			<%
              String text = tempSkill.getSummary();
              if (text.length()>50) { 
            %>
			<td><%= text.substring(0,50)+"..."%></td>
			<% } else { %>
			<td><%= text%></td>
			<% } %>
			
			<td><a href="edit_skill.jsp?sid=<%=tempSkill.getID()%>">
	            <img src="../css/images/edit.png" title="编辑此教程的属性"
	                 alt="编辑此教程的属性" border="0"></a>
	        </td>
			<td><a href="SKILLINFO?cmd=delSkill&sid=<%=tempSkill.getID()%>" onclick='if(window.confirm("操作不能恢复，您确定要删除此教程？")){return true;}else{return false;}'>
	            <img src="../css/images/rubbish.png" title="删除此教程"
	                 alt="删除此教程 " border="0"></a>
	        </td>
		 </tr>

<% 
      }
}
 else if( pageid==pagecount&&pageid>0 ) {
   for(int i=0; i<skillVector.size()-pagerows*(pagecount-1);i++) {
      Skill tempSkill = (Skill)skillVector.get(pagerows*(pageid-1)+i);
      int num = pagerows*(pageid-1)+i+1;
      if ( num%2==0 ) {
%>

		 <tr>
	   <%  } else { %>
		 <tr class="bg">
	   <%  }  %>
			<td align="center"><%=num%></td>
			<td><a href="view_skill.jsp?sid=<%=tempSkill.getID()%>"><font size="-1">
			
			<%
			  if(tempSkill.getID()<4) {
			     out.print(tempSkill.getLabel());
			   } else { 
				   out.print(tempSkill.getsubLabel());
			  }
			%>
			</font></a></td>
			
			<td><%= tempSkill.getType()%></td>
			
			<%
              String text = tempSkill.getSummary();
              if (text.length()>50) { 
            %>
			<td><%= text.substring(0,50)+"..."%></td>
			<% } else { %>
			<td><%= text%></td>
			<% } %>
			
			<td><a href="edit_skill.jsp?sid=<%=tempSkill.getID()%>">
	            <img src="../css/images/edit.png" title="编辑此教程的属性"
	                 alt="编辑此教程的属性" border="0"></a>
	        </td>
			<td><a href="SKILLINFO?cmd=delSkill&sid=<%=tempSkill.getID()%>" onclick='if(window.confirm("操作不能恢复，您确定要删除此教程？")){return true;}else{return false;}'>
	            <img src="../css/images/rubbish.png" title="删除此教程"
	                 alt="删除此教程 " border="0"></a>
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
           <% if (pageid>1){ %>
              <a href="skill.jsp?pageid=<%=pageid-1%>">上一页</a> 
           <% }
           
             if(pageid<pagecount) { %>
              <a href="skill.jsp?pageid=<%=pageid+1%>">下一页</a> 
           <% } %>
           
              &nbsp;转到第<SELECT onChange="javascript:window.location='skill.jsp?pageid='+this.options[this.selectedIndex].value;" size=1 name=page>
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
      </table>
     </div>
   </div>
   
      <jsp:include page="../messagebar.jsp" flush="true"/>
    </div>
  <div id="footer"></div>
</div>
</body>
</html>