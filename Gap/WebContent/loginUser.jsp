<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.* , bean.*, java.lang.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	 <meta name="viewport" content="width=device-width, initial-scale=1.0">
	

<link href="css/index.css" rel="stylesheet" type="text/css">
<link href="css/generale.css" rel="stylesheet" type="text/css">
<link href="css/responsive.css" rel="stylesheet" type="text/css">


<style>
	div 
	{
		width:100%;
		margin=0;
		padding=0;
 	}
 	
 	input[type=button]
	{
	padding:3px; 
	height:28px;
	border-radius: 5px;
    border: 3px solid #0088b3;
    background-color:#0088b3;
    color: white;
	box-shadow: 0px 15px 10px -10px rgba(0,0,0,0.4);
    transition: all 0.5s;
	}

	input[type=button]:hover
	{
	box-shadow: 0px 45px 20px -30px rgba(0,0,0,0.2);
    transform: translateY(-5px);
	}
 	
	input[type=text]:focus
	{
		background-color:#7fffd4;
	}
	
	input[type=password]
	{
		height:25px;
		border-radius: 5px;
	}
	
	input[type=password]:focus
	{
		background-color:#7fffd4;
	}
</style>

</head>
<body>

<jsp:include page="common/header.jsp"/>


<form name="form" method="POST"> 
<fieldset id="demo" class="fieldReg">
     <legend><h2>Log in</h2></legend>
     <div style="margin-top:-10px;" align = "center">
     	<p><a href="<%=response.encodeURL("Index.jsp")%>"><img src = "Elementi/logo.png" width="100" height="100" align="center"></a></p>
     	<%
			String errore = (String) request.getAttribute("errore");
			if(errore != null)
			{ %>
				<div align="center" Style="color:red"><%=errore %></div>
		  <%}%>
     	<p><b>Email</b></p>
     	<p><input id="username" type="text" name="username" style="width:300px; height:30px;" placeholder="Inserire email" required> </p>
       	<p><b>Password</b></p>
     	<p><input id="password" type="password" style="width:300px; height:30px;" name="password" placeholder="Inserire password" required> <p>
     	<input type="button" onClick="invia()" value="Login"/>
     </div> 
</fieldset>
</form> 

<jsp:include page="common/futher.jsp"/>
<script>

function invia()
{
	document.form.action = "<%=response.encodeURL("LoginControl")%>";
	document.form.submit();
}
</script>


</body>
</html>
