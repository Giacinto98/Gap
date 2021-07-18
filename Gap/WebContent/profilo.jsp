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
	<link href="css/popUp.css" rel="stylesheet" type="text/css" media='all'>
	
	<script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.3/jquery.min.js'></script>
	<script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js'></script>
	<script type='text/javascript' src='http://demo.persaper.it/js/demo.js'></script>
	
	
	
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

<table id="demo" align="center" class= "tabella">
<tr>
<th>
<fieldset><legend><h3>Info Profilo</h3></legend>

	<fieldset>
     	<legend><h3>I tuoi ordini</h3></legend>
     	<h5>Visualizza gli ordini che hai effettuato</h5>
     	<button onClick="location.href='VisualizzaOrdiniControl'">Visualizza</button>
	</fieldset>
</th>
<th>
	<fieldset>
     	<legend><h3>Impostazioni</h3></legend>
     	<h5>Modificia password dell'account</h5>
     	<button onclick="location.href='cambioPassword.jsp'">Modifica Password</button>
     	<h5>Visualizza le tue informazioni pesonali</h5>
     	<button onClick="cercaUtente('<%=utente.getEmail()%>')">Visualizza</button>
	</fieldset>
	
</fieldset>
</th>
</tr>
</table>

<div class="demo-container">

	

</div>

<%} %>

<jsp:include page="common/futher.jsp"/>



<script>

function cercaUtente(email)
{
	var url = 'RecuperoDatiUtenteControl?email=' + encodeURIComponent(email);
  	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = 
	function() 
	{
		if(xhr.readyState == 4 && xhr.status == 200)
		{
			var response = JSON.parse(xhr.responseText);
			document.getElementById("demo").innerHTML = "<h3>Codice Fiscale: " + response.cf + "<br>Nome: " + response.nome + "<br>Cognome: " + response.cognome + "<br>Email: " + response.email + "<br>Password: Non visibile per questioni di sicurezza<br>Indirizzo: " + response.indirizzo + "<br>Telefono: " + response.telefono + "</h3><a href= 'profilo.jsp' >Indietro</a>";		
		}
	}
	xhr.open("GET",url,true);
	xhr.send(null);	
}


</script>

</body>
</html>