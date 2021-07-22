<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.* , java.lang.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">	
	<meta name="author" content= "Adinolfi Giacinto">
	<meta charset="ISO-8859-1">

<title>Prodotti di un ordine</title>
	
	<link href="css/index.css" rel="stylesheet" type="text/css">
	<link href="css/prodotto.css" rel="stylesheet" type="text/css">
	<link href="css/generale.css" rel="stylesheet" type="text/css">
	<link href="css/responsive.css" rel="stylesheet" type="text/css">
	
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
	<% 
	UtenteBean utente = new UtenteBean();
	HttpSession sessione = request.getSession(false);
   if (sessione != null)
   {
		utente = (UtenteBean) sessione.getAttribute("utente");
   }
   String codice= (String) request.getParameter("codice");
%> 
<jsp:include page="common/header.jsp"/>
<h2 align="center">Inserisci qui la tua recensione</h2>

<table align="center" Style="width:500px">
<form id="pippo" action="<%=response.encodeURL("RecensioneControl")%>" method="get">
	<input type="hidden" name="codice" value="<%=codice%>">
	<input type="hidden" name="email" value="<%=utente.getEmail()%>">
	<tr><td><label><%=utente.getNome()%> <%=utente.getCognome()%>:</label></td></tr>
	<tr><td><textarea form="pippo" name="recensione" rows="15" cols="85" placeholder="Scrivi qui la tua recensione(max 250 caratteri)"></textarea></td></tr>
	<tr><td><input type="submit" value="Invia recensione"></td></tr>
</form>
</table>


<jsp:include page="common/futher.jsp"/>

</body>
</html>