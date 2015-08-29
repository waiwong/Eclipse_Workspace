<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.bean.Feature,
                 java.util.Vector" %>
                 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Feature Manage System</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />
</head>
<body id="feature">

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
	   <jsp:include page="sidebar.jsp" flush="true"/>
		
		<div id="center-column">
			<div class="top-bar">
				<form name=pageset action="feature.jsp" method=post class="page" >
				  <font size=-1>每页显示</font>
                  <input type=text size=1 maxlength=3 name=pagerows 
                         value=<%=pagerows%> onkeyup="value=value.replace(/[^\d]/g,'')" 
                         style="height:20px">
                  <font size=-1>&nbsp;条信息</font>&nbsp;&nbsp;
                  <input type=submit value="设定" style="width:50px;height:25px" title="设定">
               </form>
				
				<h1>技术特色概览</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 技术特色概览</div>
			</div><br />
			
			<div class="select-bar"></div>

			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				<table class="listing" cellpadding="0" cellspacing="0">
					<tr>
						<th class="first">ID</th>
						<th >主题名称</th>
						<th >类型</th>
						<th >摘要</th>
						<th >Edit</th>
						<th class="last">Delete</th>
					</tr>

<%
   Feature feature = new Feature();
   Vector featureVector = new Vector();
   featureVector=feature.getAllFeature();
   
   int pageid,
       allrows = 0,
       pagecount = 0;
   
   String s = request.getParameter("pageid");
   if( s==null ) {
     pageid = 1;
   } else {
       pageid = Integer.parseInt(s);
     }
   if(featureVector!=null) allrows = featureVector.size();
    
   pagecount = (allrows+pagerows-1)/pagerows;
   if(pagecount==0) pageid = 0;
   if(pageid>pagecount) pageid = pagecount;
   if(pagecount>0&&pageid<=0) pageid = 1;	

   if(pageid<pagecount&&pageid>0){
      for(int i=0; i<pagerows;i++) {
        Feature tempFeature = (Feature)featureVector.get(pagerows*(pageid-1)+i);
        int num = pagerows*(pageid-1)+i+1;
        if ( num%2==0 ) {
%>

		 <tr>
	   <%  } else { %>
		 <tr class="bg">
	   <%  }  %>
			<td align="center"><%=num%></td>
			<td><a href="view_feature.jsp?fid=<%=tempFeature.getID()%>"><font size="-1">
			    <%=tempFeature.getTitle()%></font></a>
			</td>
			<td><%= tempFeature.getType()%></td>
			
			<%
              String text = tempFeature.getSummary();
              if (text.length()>50) { 
            %>
			<td><%= text.substring(0,50)+"..."%></td>
			<% } else { %>
			<td><%= text%></td>
			<% } %>
			
			<td><a href="edit_feature.jsp?fid=<%=tempFeature.getID()%>">
	            <img src="../css/images/edit.png" title="编辑<%=tempFeature.getTitle()%>此项的属性"
	                 alt="编辑<%=tempFeature.getTitle()%>此项的属性" border="0"></a>
	        </td>
			<td><a href="FEATUREINFO?cmd=delFeature&fid=<%=tempFeature.getID()%>" onclick='if(window.confirm("操作不能恢复，您确定要删除此项？")){return true;}else{return false;}'>
	            <img src="../css/images/rubbish.png" title="删除<%=tempFeature.getTitle()%>此项 "
	                 alt="删除<%=tempFeature.getTitle()%>此项 " border="0"></a>
	        </td>
		 </tr>

<% 
      }
}
 else if( pageid==pagecount&&pageid>0 ) {
   for(int i=0; i<featureVector.size()-pagerows*(pagecount-1);i++) {
      Feature tempFeature = (Feature)featureVector.get(pagerows*(pageid-1)+i);
      int num = pagerows*(pageid-1)+i+1;
      if ( num%2==0 ) {
%>

		 <tr>
	   <%  } else { %>
		 <tr class="bg">
	   <%  }  %>
			<td align="center"><%=num%></td>
			<td><a href="view_feature.jsp?fid=<%=tempFeature.getID()%>"><font size="-1">
			    <%=tempFeature.getTitle()%></font></a>
			</td>
			<td><%= tempFeature.getType()%></td>
			
			<%
              String text = tempFeature.getSummary();
              if (text.length()>50) { 
            %>
			<td><%= text.substring(0,50)+"..."%></td>
			<% } else { %>
			<td><%= text%></td>
			<% } %>
			
			<td><a href="edit_feature.jsp?fid=<%=tempFeature.getID()%>">
	            <img src="../css/images/edit.png" title="编辑<%=tempFeature.getTitle()%>此项的属性"
	                 alt="编辑<%=tempFeature.getTitle()%>此项的属性" border="0"></a>
	        </td>
			<td><a href="FEATUREINFO?cmd=delFeature&fid=<%=tempFeature.getID()%>" onclick='if(window.confirm("操作不能恢复，您确定要删除此项？")){return true;}else{return false;}'>
	            <img src="../css/images/rubbish.png" title="删除<%=tempFeature.getTitle()%>此项 "
	                 alt="删除<%=tempFeature.getTitle()%>此项 " border="0"></a>
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
              <a href="feature.jsp?pageid=<%=pageid-1%>">上一页</a> 
           <% }
           
             if(pageid<pagecount) { %>
              <a href="feature.jsp?pageid=<%=pageid+1%>">下一页</a> 
           <% } %>
           
              &nbsp;转到第<SELECT onChange="javascript:window.location='feature.jsp?pageid='+this.options[this.selectedIndex].value;" size=1 name=page>
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