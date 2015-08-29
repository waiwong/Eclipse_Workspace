<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flasher JiāngHú Manage System</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />

</head>
<body id="home">

<div id="main">
	<jsp:include page="header/header.jsp" flush="true"/>

	<div id="middle">
		<div id="left-column">
			<h3>Welcome</h3>
			<ul class="nav">
			    <li>欢迎登入闪客江湖后台管理系统</li>
				<li>Welcome to Flasher JiāngHú Manage System </li>
			</ul>
			<a href="../index.jsp" style="cursor:pointer"><h3>返回前台首页</h3></a>
		</div>
		
		<div id="center-column">
			<div class="top-bar">
				<h1>主页面</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;主页面</div>
			</div><br />
			
			<div class="select-bar"></div>
		  
			<div class="table">
    
             <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="600" height="400" id="FlashID">
                <param name="movie" value="../swf/Other/welcome.swf" />
                <param name="quality" value="high" />
                <param name="wmode" value="transparent" />
                <param name="swfversion" value="9.0.45.0" />
 
                <!--[if !IE]>-->
                <object type="application/x-shockwave-flash" data="../swf/Other/welcome.swf" width="600" height="400">
                   <param name="quality" value="high" />
                   <param name="wmode" value="transparent" />
                   <param name="swfversion" value="9.0.45.0" /> 
                </object>
                <!--<![endif]-->

              </object>

		   </div>			  				
        </div>

      </div>
    <div id="footer"></div>
</div>
</body>
</html>
