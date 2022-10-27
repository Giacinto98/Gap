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


<form name="form" method="GET" action = "AcquistoControl" > 
<fieldset id="demo" class="fieldReg">
     <legend><h2>Dati carta di pagamento</h2></legend>
     <div style="margin-top:-10px;" align = "center">
     	<%
			String errore = (String) request.getAttribute("errore");
			if(errore != null)
		{%>
				<div align="center" Style="color:red"><%=errore %></div>
		 <%
		  }
		 %>
			<p><b>Numero carta</b></p>
     		<p><input id="numeroCarta" type="text" name="numeroCarta" style="width:300px; height:30px;" placeholder="Inserire numero carta" required> </p>
       		<p><b>Mese scadenza</b></p>
     		<p><input id="meseScadenza" type="text" style="width:300px; height:30px;" name="meseScadenza" placeholder="Inserire data scadenza della carta" required> <p>
     		<p><b>Anno scadenza</b></p>
     		<p><input id="annoScadenza" type="text" style="width:300px; height:30px;" name="annoScadenza" placeholder="Inserire data scadenza della carta" required> <p>
     		<p><b>CVV</b></p>
     		<p><input id="cvv" type="text" style="width:300px; height:30px;" name="cvv" placeholder="Inserire codice a tre cifre carta" required> <p>
     		<input type="submit" value="Completa Acquisto">
		</div>
		</fieldset>
		</form>
     
<jsp:include page="common/futher.jsp"/>



</body>
</html>
