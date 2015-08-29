<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>

  	<link rel="stylesheet" href="css/login.css" type="text/css" media="screen" />
  	<link rel="stylesheet" href="css/fx.slide.css" type="text/css" media="screen" />
	<script src="js/jquery-1.3.2.min.js" type="text/javascript"></script>
	
	<script language="javascript" >
		$(function(){
		   $("#toggleLogin").toggle(function(){
		        $("#login").parent("div").animate({ height : 105 } , 520 );
				$("#login").animate({marginTop : 0 } , 500 );
				$(this).blur();
		   },function(){
			    $("#login").parent("div").animate({ height : 0 } , 500 );
				$("#login").animate({marginTop : -105 } , 520 ); 
				$(this).blur();
		   });
		   $("#closeLogin").click(function(){
		        $("#login").parent("div").animate({ height : 0 } , 500 );
				$("#login").animate({marginTop : -105 } , 520 ); 
		   });
		})
	</script>

</head>

<body>
	<!-- Login -->
	<div style="margin: 0px; overflow: hidden; position: relative; height: 0px;">
		<div id="login" style="margin: -105px 0px 0px; height: auto;">
			<div class="loginContent">
			
			
				<form name="LoginForm" onsubmit="return checkLoginForm()"  method="post" action="check_login.jsp">
					<label for="log"><b>Username: </b></label>
					<input class="field" type="text" name=userName id="log" value="" size="23" />
					<label for="pwd"><b>Password:</b></label>
					<input class="field" type="password" name=userPW id="pwd" size="23" />
					<input type="submit" name="submit" value="" class="button_login" />
					<input type="hidden" name="redirect_to" value=""/>
				</form>
				
				
				<div class="left">
					<label for="rememberme">
					<input name="rememberme" id="rememberme" class="rememberme" type="checkbox" checked="checked" value="forever" /> Remember me</label>
				</div>
				
			</div>
			<div class="loginClose"><a href="#" id="closeLogin">Close Panel</a></div>
		</div> 
	</div> 
	<!-- /login -->

    <div id="container">
		<div id="top">
		<!-- login -->
			<ul class="login">
		    	<li class="left">&nbsp;</li>
		        <li>Hello Guest!</li>
				<li>|</li>
				<li><a id="toggleLogin" href="#">Log In</a></li>
			</ul> <!-- / login -->
		</div> <!-- / top -->
    </div>
</body>
</html>