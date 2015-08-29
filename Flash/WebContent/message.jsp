<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>閃客江湖系统</title>

</head>

<body>
<table height="120"><tr><td>&nbsp;</td></tr></table>
<table class=tablemsg valign=middle align=center height="168" cellpadding="0" cellspacing="0">
	<tr>
		<td align=right>
		
			<table valign=middle width="537" align=right border="0" cellpadding="0" cellspacing="0">
            <tr><td align="center" valign=middle>
             <b>提示信息</b> </td>
            </tr>           
            </table>
            
       </td>
	</tr>
	<tr>
		<td>
			<table class=tablemsg bgcolor="#E8E8E8" valign=middle width="537" height=100 align=right border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td align=center> <div align="center">
             <br><br>

              <%
                String messageid = request.getParameter("messageid") ;
                if((messageid != null) && (messageid.equals("007"))) {%>
                  <span class="message">退出成功!欢迎下次再来！</span> 
                  <br><br>
                  <input type='button' value='重新登录' style='width:80px;height:18px;background:#f6f6f9 ;border:solid 1px #333333;' onclick="window.location.href='userLogin.jsp'">　　<input type='button' value='系统首页' style='width:80px;height:18px;background:#f6f6f9 ;border:solid 1px #333333;' onclick="window.location.href='index.jsp'"> 

               <%
                  }
                    if((messageid != null) && (messageid.equals("199"))){%>
                        <span class="message">您已经退出!请重新<a href="userLogin.jsp">登录</a>。</span> 
              <%}%>             
              
              
              <%
                  String msg=request.getParameter("msg");
                  if(msg!=null)
              {
              %>
              
              <span><font color=#DC5130><%=msg%></font></span><br><br>
              <input type='button' value='返回重试' style='width:80px;height:18px;background:#f6f6f9 ;border:solid 1px #333333;' onclick='window.history.go(-1);'>
&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' value='系统首页' style='width:80px;height:18px;background:#f6f6f9 ;border:solid 1px #333333;' onclick="window.location.href='index.jsp'">
              <%}%>
            </div>
          </td>
        </tr>

          </table>
          </td>
        </tr>

          </table>
</body>
</html>