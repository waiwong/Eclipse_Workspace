<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.bean.Works,
                 java.util.Vector,
                 java.util.*" %>
                 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flash Resources</title>
</head>

<link rel="stylesheet" href="css/index.css" type="text/css"/>
<link rel="stylesheet" href="css/flash.css" type="text/css"/>

<jsp:include page="header.jsp" flush="true"/>


<body>
<div id="container">
  <div id="wrapper">
    <div id="content">
       <div class="block">
         <div class="bot">  

<%
  String str = request.getParameter("type"),
         parm = new String(str.getBytes("ISO-8859-1"), "UTF-8");
   int pagerows = 10;//每页显示的信息数目
   if ( (String)session.getAttribute("pagerows")!=null ) //查看session中是否有设定页显示数
     pagerows = Integer.parseInt((String)session.getAttribute("pagerows")); //如果有则取session中的值

   String prows = request.getParameter("pagerows"); //请求用户设定的页显示数
   if( prows != null&&!prows.trim().equals("")&&!prows.trim().equals("0") ) { //如果用户有设定（更改）
     pagerows=Integer.parseInt(prows);//将页显示数改为用户设定值 
     session.setAttribute("pagerows",prows);//改变session中的页显示数
   }
%>


   <div class="set_left">
     <ul class="set_left_tit">
	   <li class="set_left_tit_l">
	     <p><%=parm %>&nbsp;作品列表</p>
	   </li>
	   <li class="set_left_tit_r">
	   
   	<form name=pageset action="resource.jsp?type=<%=parm %>" method=post class="page" >
		<font size=-1>每页显示</font>
        <input type=text size=1 maxlength=3 name=pagerows 
               value=<%=pagerows%> onkeyup="value=value.replace(/[^\d]/g,'')" 
               style="height:20px">
        <font size=-1>&nbsp;条信息</font>&nbsp;&nbsp;
        <input type=submit value="Set" style="width:50px;height:25px" title="Set">
    </form>

	   </li>
	 </ul>
   </div>
   

<%
   Works works = new Works();
   Vector worksVector = new Vector();
   ArrayList<String> title = new ArrayList<String>(),
                     level1 = new ArrayList<String>(),
                     level2 = new ArrayList<String>(),
                     level3 = new ArrayList<String>(),
                     author = new ArrayList<String>(),
                     photo =  new ArrayList<String>(),
                     path = new ArrayList<String>();
   worksVector = works.getAllWorks();
   
   int pageid = 0,
       allrows = 0,
       pagecount = 0,
       total = 0;
   
   String s = request.getParameter("pageid");
   if( s==null ) {
     pageid = 1;
   } else {
       pageid = Integer.parseInt(s);
     }
   if(worksVector!=null) allrows = worksVector.size();

     for (int i=0; i<allrows; i++) {
    	 Works tempWorks = (Works)worksVector.get(i);
    	 if (tempWorks.getLevel1().equals(parm)) {
    		 title.add( tempWorks.getTitle() );
    		 level1.add( tempWorks.getLevel1() );
    		 level2.add( tempWorks.getLevel2() );
    		 level3.add( tempWorks.getLevel3() );
    		 author.add( tempWorks.getAuthor() );
    		 path.add( tempWorks.getPath() );
    		 photo.add( tempWorks.getPhoto() );
    		 total++;
    	 }
     }
     
   allrows = total;
   pagecount = (allrows+pagerows-1)/pagerows;
   if(pagecount==0) pageid = 0;
   if(pageid>pagecount) pageid = pagecount;
   if(pagecount>0&&pageid<=0) pageid = 1;	

   if( pageid<pagecount&&pageid>0 ){

	  int start = pagerows*pageid-pagerows,
	      end = pagerows*pageid;
      for(int i=start; i<end; i++) {


%>

      
              <div class="row-articles articles">
                <div class="cl">&nbsp;</div>
                  <div class="article">
                   <div class="cl">&nbsp;</div>
                      <div class="image">
                         <img src=<%=photo.get(i) %> width=200 height=140 />
                      </div>
                       
                        <div class="cnt">
                          <h4>作品名称 : <%=title.get(i) %></h4>
                          <p>作者 : <%=author.get(i) %> ( <%=level1.get(i) %> )</p>
                          <p>作品類型 : <%=level2.get(i) %> 
                                                                                           〖<%=level3.get(i) %>〗                                                           
                          </p>
                          <p><a href=view_resource.jsp?path=<%=path.get(i) %> >
                                  <img src="css/images/view.png" />作品演示
                             </a></p>
                         </div>
                         
                      <div class="cl">&nbsp;</div>
                    </div>
                </div>     


<%
    	  }
      }

 else if( pageid==pagecount&&pageid>0 ) {
	int start = pagerows*pageid-pagerows,
	    row = pagerows*pageid,
	    end = 0;
    if ( row>total ) end = total;
    else { end = row; }
    for(int i=start; i<end; i++) {

%>
     
              <div class="row-articles articles">
                <div class="cl">&nbsp;</div>
                  <div class="article">
                   <div class="cl">&nbsp;</div>
                      <div class="image">
                         <img src=<%=photo.get(i) %> width=200 height=140 />
                      </div>
                       
                        <div class="cnt">
                          <h4>作品名称 : <%=title.get(i) %></h4>
                          <p>作者 : <%=author.get(i) %> ( <%=level1.get(i) %> )</p>
                          <p>作品類型 : <%=level2.get(i) %> 
                                                                                           〖<%=level3.get(i) %>〗                                                           
                          </p>
                          <p><a href=view_resource.jsp?path=<%=path.get(i) %> >
                                  <img src="css/images/view.png" />作品演示
                             </a></p>
                         </div>
                         
                      <div class="cl">&nbsp;</div>
                    </div>
                </div>    
              
<% 
	   }
    }

%>	

      </div>
   </div>
         <div class="set-bottom"><font size="-1">
                               每页显示<font color=blue> <b><%=pagerows%></b> </font>条信息，总共
            <font color=blue> <b><%=allrows%></b> </font>条信息<br>
                              当前页：<font color=blue><b><%=pageid%></b> </font>/<font color=blue> <b><%=pagecount%></b> </font>
           <% if (pageid>1){ %>
              <a href="resource.jsp?pageid=<%=pageid-1%>&type=<%=parm%>">上一页</a> 
           <% }
           
             if(pageid<pagecount) { %>
              <a href="resource.jsp?pageid=<%=pageid+1%>&type=<%=parm%>">下一页</a> 
           <% } %>
           
              &nbsp;转到第<SELECT onChange="javascript:window.location='resource.jsp?type=<%=parm %>&pageid='+this.options[this.selectedIndex].value;" size=1 name=page>
	       <%
	          int p = 1;
		      while( p<=pagecount ) {
			   if( p==pageid )
			      out.println("<option value="+p+" selected>"+p);
		       else out.println("<option value="+p+">"+p);
		       p = p+1;
		    } %>
		  </select>页</font>
         </div>	             
             
         </div>
         
         
        <div id="sidebar"> 
         <br>
          <jsp:include page="search.jsp" flush="true"/>
        </div>
        
      <jsp:include page="footer.jsp" flush="true"/>
</div>
</div>
</body>
</html>
