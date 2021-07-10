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
     	<p><a href="Index.jsp"><img src = "Elementi/logo.png" width="100" height="100" align="center"></a></p>
     	<p><label for="username">Login</label></p>
     	<p><input id="username" type="text" name="username" placeholder="enter email" required> </p>
       	<p><label for="password">Password</label></p>
     	<p><input id="password" type="password" name="password" placeholder="enter password" required> <p>
     	<input style="border-radius: 5px; border: 3px solid #0088b3; background-color:#0088b3; color: white;" type="submit" value="Login"/>
     	<input style="border-radius: 5px; border: 3px solid #0088b3; background-color:#0088b3; color: white;" type="reset" value="Reset"/>
     </div> 
</fieldset>
</form> 
</body>
</html>
