<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.* , java.lang.*" %>

<%
	//response.encodeURL("Index.jsp"); //URL rewriting
	//dobbiamo verificare che passiamo per la servlete che recupera le informazioni sulla tabella store per poi stamparla
	Collection<?> prodotti = (Collection<?>) request.getAttribute("prodotti"); //leggo dalla request l'attributo products che ha la lista degli oggetti 
	String error = (String)request.getAttribute("error"); //mi prendo anche la stringa dove tengo scritta una eventuale eccezione
	
	if(prodotti == null && error == null){ //se non sono riuscito a prednere i prodotti e non ho l'errore, c'è qualche problema
		response.sendRedirect(response.encodeRedirectURL("./ProdottoControl")); //chiamiamo noi la servlet
		return;
	}
%>    
    
<!DOCTYPE html>
<html lang = "en">
<head>
	<meta charset="ISO-8859-1">
	<meta name="author" content= "Adinolfi Giacinto, Emanuele Giammarino, Vincenzo Palcone">
	 
	<title>Store</title>
	
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

<body id="body">

<jsp:include page="common/header.jsp"/>

<section id=StampaProdotti style="margin: 3px 3px 3px 3px;">

<table class="tabellaProdotti">
<tr>
	    <%
	    String url = response.encodeURL("CercaProdottoControl");
		//controlliamo se non ci sono prodotti da mostrare
		if(prodotti != null && prodotti.size() > 0) //controllo se ci sono prodotti all'interno dell'array
		{
			Iterator<?> it = prodotti.iterator(); //iteriamo i prodotti
			while(it.hasNext()) //fin quando ho prodotti
			{
			ProdottoBean bean = (ProdottoBean) it.next(); //metto nel bean riferito alla tabella dei prodotti il prodotto i-esimo	
		%>	

	<td> 

		
		<div id="carta" >
  		<img class="card-img-top" height=200 width=100% src="Elementi/<%=bean.getNome()%>.jpg " alt="Card image cap">
  			<div class="card-body">
    	<h3 class="card-title"><%=bean.getNome()%> <%if(bean.getSconto()>0) {%> <b style="color:red;"> &nbsp;&nbsp; SCONTO <%=bean.getSconto()%> &percnt; </b> <%} %></h3>
    	<p class="card-text">Apri per maggiori iformazioni </p>
    	<form action="<%=url%>" method="get"> <button type="submit" name="bottone" value="<%=bean.getNome()%>">Apri Prodotto</button> </form>
		</div>
		</div>

  	</td> 
		<% }
		} %>
	
</tr>		    
</table>
	
</div>
</section>
</table>	


<jsp:include page="common/futher.jsp"/>


</body>
</html>