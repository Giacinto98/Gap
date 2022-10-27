<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.* , bean.*, java.lang.*" %>
    
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

<table id="demo" align="center" class= "tabella" style="width:100%;">
<tr>
<th>

<fieldset><legend><h3>Info Profilo</h3></legend>

	<fieldset>
     	<legend><h3>I tuoi ordini</h3></legend>
     	<h5>Visualizza gli ordini che hai effettuato</h5>
     	<%String url = response.encodeURL("VisualizzaOrdiniControl");%>
     	<button onClick="location.href='<%=url%>'">Visualizza</button>
	</fieldset>
</th>
<th>
	<fieldset>
     	<legend><h3>Impostazioni</h3></legend>
     	<h5>Modifica password dell'account</h5>
     	<button onclick="location.href='cambioPassword.jsp'">Modifica Password</button>
     	<h5>Visualizza le tue informazioni pesonali</h5>
     	<button onClick="cercaUtente('<%=utente.getCf()%>','<%=utente.getNome()%>','<%=utente.getCognome()%>','<%=utente.getEmail()%>','<%=utente.getIndirizzo()%>','<%=utente.getTelefono()%>')">Visualizza</button>
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

function cercaUtente(cf, nome, cognome, email, indirizzo, telefono)
{
console.log("Ciao ",cf, nome, cognome, email, indirizzo, telefono);
document.getElementById("demo").innerHTML = "<h3>Codice Fiscale: " + cf + "<br>Nome: " 
+ nome + "<br>Cognome: " + cognome + "<br>Email: " + email + "<br>Password: Non visibile per questioni di sicurezza<br>Indirizzo: " 
+ indirizzo + "<br>Telefono: " + telefono + "</h3><a href= 'profilo.jsp' >Indietro</a>";		
}

</script>

</body>
</html>