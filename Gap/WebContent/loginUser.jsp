<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link href="css/generale.css" rel="stylesheet" type="text/css">

	<title>Login form</title>
</head>

<body>

<form action="LoginControl" method="get"> 
<fieldset>
     <legend><h2>Log in</h2></legend>
     <div align = "center">
     	<p><img src = "Elementi/logo.png" width="100" height="100" align="center"></p>
     	<p><label for="username">Login</label></p>
     	<p><input id="username" type="text" name="username" placeholder="enter email" required> </p>
       	<p><label for="password">Password</label></p>
     	<p><input id="password" type="password" name="password" placeholder="enter password" required> <p>
     	<input type="submit" value="Login"/>
     	<input type="reset" value="Reset"/>
     </div> 
</fieldset>
</form> 
</body>
</html>
