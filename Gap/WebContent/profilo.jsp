<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.* , java.lang.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">	
	<meta name="author" content= "Adinolfi Giacinto">
	<meta charset="ISO-8859-1">

<title>Prodotto</title>
	
	<link href="css/responsive.css" rel="stylesheet" type="text/css">
	<link href="css/index.css" rel="stylesheet" type="text/css">
	<link href="css/prodotto.css" rel="stylesheet" type="text/css">
	<link href="css/generale.css" rel="stylesheet" type="text/css">
	<link href="css/profilo.css" rel="stylesheet" type="text/css">
	
	<style>
		div 
		{
			width:100%;
			margin:0;
			padding:0;
		}
	</style>
</head>
<body>

<jsp:include page="common/header.jsp"/>

<%
HttpSession sessione = request.getSession(false);
	   if (sessione != null)
	   {
			UtenteBean utente = (UtenteBean) sessione.getAttribute("utente");
%>


<h1 align= "center">Benvenuto <%=utente.getNome()%></h1>

<table align="center" class= "tabella">
<tr>
<th>
<fieldset><legend><h3>Info Profilo</h3></legend>

	<fieldset>
     	<legend><h3>I tuoi ordini</h3></legend>
     	<h5>Visualizza gli ordini che hai effettuato</h5>
     	<button>Visualizza</button>
	</fieldset>
</th>
<th>
	<fieldset>
     	<legend><h3>Impostazioni</h3></legend>
     	<h5>Modificia password dell'account</h5>
     	<button>Modifica Password</button>
     	<h5>Visualizza le tue informazioni pesonali</h5>
     	<button>Visualizza</button><br>
	</fieldset>
	
</fieldset>
</th>
</tr>
</table>


<%} %>

<jsp:include page="common/futher.jsp"/>

</body>
</html>