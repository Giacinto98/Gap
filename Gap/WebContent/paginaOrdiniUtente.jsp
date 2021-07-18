<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.* , java.lang.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">	
	<meta name="author" content= "Adinolfi Giacinto">
	<meta charset="ISO-8859-1">

<title>Ordini Utente</title>
	
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
<jsp:include page="common/header.jsp"/>

<%
	response.encodeURL("paginaOrdiniUtente.jsp");
	UtenteBean utente = new UtenteBean();
	HttpSession sessione = request.getSession(false);
   if (sessione != null)
   {
		utente = (UtenteBean) sessione.getAttribute("utente");
   }	
	 //URL rewriting
	//dobbiamo verificare che passiamo per la servlete che recupera le informazioni sulla tabella store per poi stamparla
	Collection<?> ordini = (Collection<?>) request.getAttribute("ordini"); //leggo dalla request l'attributo products che ha la lista degli oggetti 
	if(ordini != null)
	{
		Iterator<?> it = ordini.iterator(); //iteriamo i prodotti
		while(it.hasNext()) //fin quando ho prodotti
		{
		OrdineBean ordine = (OrdineBean) it.next();
%>

	<table align="center" 
	Style="background-color: white;
	width:18rem;
	border-style:solid; 
	border-color:white;
	border-width:3px;
	border-radius: 10px;
	margin-bottom: 10px;
	width:40%;
	font-size: 24px ">
		<tr><td>Ordine#:</td><td><%=ordine.getNumeroOrdine()%></td></tr>
		<tr><td>Ordine effettuato il:</td><td> <%=ordine.getDataOrdine()%></td></tr>
		<tr><td>Inviato a:</td><td> <%=utente.getNome()%> <%=utente.getCognome()%></td></tr>
		<tr><td>Numero di elementi:</td><td> <%=ordine.getNumeroProdotti()%> </td></tr>
		<tr><td>Prodotti:</td><td><button onclick="location.href='ComposizioneControl?idOrdine=<%=ordine.getNumeroOrdine()%>'">Visualizza Prodotti</button></td></tr>
		<tr><td>Prezzo totale:</td><td> <%=ordine.getPrezzoTotale()%>
	</table>	
	



<%		}
	}	
else {%>
<h1 align="center"> Non ci sono ordini effettuati</h1>

<%} %>

<jsp:include page="common/futher.jsp"/>
</body>
</html>