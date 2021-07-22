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
<jsp:include page="common/header.jsp"/>

<%
	UtenteBean utente = new UtenteBean();
	HttpSession sessione = request.getSession(false);
   if (sessione != null)
   {
		utente = (UtenteBean) sessione.getAttribute("utente");
   }	
	Collection<?> prodotti = (Collection<?>) request.getAttribute("prodotti"); //leggo dalla request l'attributo products che ha la lista degli oggetti 
	Collection<?> recensioni = (Collection<?>) request.getAttribute("recensioni"); //leggo dalla request l'attributo products che ha la lista degli oggetti 
%>

<table class="tabellaProdotti">
<tr>

<%
	if(prodotti != null)
	{
		Iterator<?> it = prodotti.iterator(); //iteriamo i prodotti
		Iterator<?> itRec = recensioni.iterator();
		while(it.hasNext() && itRec.hasNext()) //fin quando ho prodotti
		{
		ProdottoBean bean = (ProdottoBean) it.next();
		RecensioneBean recensione = (RecensioneBean) itRec.next();
%>
	<td> 
		<div id="carta" >
  		<img class="card-img-top" height=200 width=100% src="Elementi/<%=bean.getNome()%>.jpg " alt="Card image cap">
  			<div class="card-body">
    	<h3 align="center" class="card-title"><%=bean.getNome()%><br>
    	<%if(recensione.getTesto().equals(""))
    	{%>
    	<%=recensione.getTesto()%>
    	<%String url = response.encodeURL("scriviRecensione.jsp?codice="+bean.getCodice());%>
    	<button  type="submit" onClick="location.href='<%=url%>'" name="bottone" value="<%=bean.getNome()%>">Recensisci</button></h3>
		<%} %>
		</div>
		</div>
  	</td> 

<%		}
	}
%>
</tr>		    
</table>

<jsp:include page="common/futher.jsp"/>
</body>
</html>