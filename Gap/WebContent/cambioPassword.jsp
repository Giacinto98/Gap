<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<meta name="author" content= "Adinolfi Giacinto, Emanuele Giammarino, Vincenzo Palcone">
	 
	<title>CambioPassword</title>
	
	<link href="css/index.css" rel="stylesheet" type="text/css">
	<link href="css/generale.css" rel="stylesheet" type="text/css">
	<link href="css/responsive.css" rel="stylesheet" type="text/css">
	
	
<style>
	div {
	width:100%;
	margin:0;
	padding:0;
	}
</style>	
</head>
<body>

<jsp:include page="common/header.jsp"/>


<fieldset id="demo" align="center">
    <legend><h2 align="center">Inserire nuova password</h2></legend>
	<form action ="<%=response.encodeURL("CambiaPasswordControl")%>" method="post"> 
		<input type="password" placeholder="Password" name="password" required>
		<input type="submit" name="Password">
	</form>
	
</fieldset>

<jsp:include page="common/futher.jsp"/>




</body>
</html>