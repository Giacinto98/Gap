<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<meta name="author" content= "Adinolfi Giacinto, Emanuele Giammarino, Vincenzo Palcone">
	 
	<title>CambioPassword</title>
	
	<link href="css/index.css" rel="stylesheet" type="text/css">
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

<p align="center" id="ciao" style="color:red">
<%String error = (String)request.getAttribute("errore"); //mi prendo anche la stringa dove tengo scritta una eventuale eccezione
if(error != null){ //se non sono riuscito a prednere i prodotti e non ho l'errore, c'è qualche problema
%>
<div align="center" Style="color:red"><%=error %></div>
<% 
} %>
</p>

<fieldset id="demo" align="center">
    <legend><h2 align="center">Inserire nuova password</h2></legend>
	<form name="form"  method="post"> 
		<input type="password" placeholder="Password Corrente" name="passwordCorrente" required><br>
		<input type="password" placeholder="Password" name="password" Style="margin-top: 7px;" required><br>
		<input type="button" style="width:100px;
									border-radius: 5px;
   									border: 3px solid #0088b3;
   									background-color:#0088b3;
    								padding:3px;
    								height: 30px;
   									width:75px;
    								margin-top: 10px;
   									color: white;" value="Invia" name="Password" onClick="ControlloFinale()"> 
	</form>
</fieldset>

<jsp:include page="common/futher.jsp"/>

<script>
function ControlloFinale()
{
	var pass = document.form.password.value;
	var count = 0;
	var stringa = "<%=response.encodeURL("CambiaPasswordControl")%>";
		
	var passExpression = /^(?=.*[a-z])(?=.*\d)(?=.*[@\.#?!$ %^&*-])(?=.*[A-Z])[a-zA-Z\d@\.#?!$ %^&*-]{6,}$/;
    if(!(passExpression.test(pass)))
    {
    	count++;
		document.getElementById("ciao").innerHTML = "Inserire formato password corretto";
   	}
    else
    {
    	document.getElementById("ciao").innerHTML = "";
    }
    
	if(count == 0)
	{
		document.form.action = stringa;
		document.form.submit();
	}
}
</script>


</body>
</html>