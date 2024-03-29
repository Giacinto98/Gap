<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.* , bean.*,  java.lang.*" %>

<%
	Collection<?> prodotti = (Collection<?>) request.getAttribute("prodotti"); //leggo dalla request l'attributo products che ha la lista degli oggetti 
	String error = (String)request.getAttribute("error"); //mi prendo anche la stringa dove tengo scritta una eventuale eccezione
	
	if(prodotti == null && error == null){ //se non sono riuscito a prednere i prodotti e non ho l'errore, c'� qualche problema
		response.sendRedirect(response.encodeRedirectURL("./ProdottoControl")); //chiamiamo noi la servlet
		return;
	}
%>    
    
<!DOCTYPE html>
<html lang = "en">
<head>
	<meta charset="ISO-8859-1">
	<meta name="author" content= "Adinolfi Giacinto, Emanuele Giammarino, Vincenzo Palcone">
	 
	<title>Pagina iniziale</title>
	
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

<section id=StampaProdotti style="margin: 3px 3px 3px 3px;">

<table class="tabellaProdotti">
<tr>
	    <%
		//controlliamo se non ci sono prodotti da mostrare
		if(prodotti != null && prodotti.size() > 0) //controllo se ci sono prodotti all'interno dell'array
		{
			Iterator<?> it = prodotti.iterator(); //iteriamo i prodotti
			while(it.hasNext()) //fin quando ho prodotti
			{
			ProdottoBean bean = (ProdottoBean) it.next();//metto nel bean riferito alla tabella dei prodotti il prodotto i-esimo	
			if(bean.getQuantita() > 0)
			{
			%>	

	<td> 

		
		<div id="carta" >
  		<img class="card-img-top" height=200 width=100% src="Elementi/<%=bean.getNome()%>.jpg " alt="Card image cap">
  			<div class="card-body">
    	<h3 class="card-title"><%=bean.getNome()%> <%if(bean.getSconto()>0) {%> <b style="color:red;"> &nbsp;&nbsp; SCONTO <%=bean.getSconto()%> &percnt; </b> <%} %></h3>
    	<p class="card-text"> <h3>Prezzo: <b style="color:green;"><%=bean.getPrezzo()%>&euro; </b></h3></p>
    	<form action="<%=response.encodeURL("CercaProdottoControl")%>" method="get"> <button type="submit" name="bottone" value="<%=bean.getNome()%>">Apri Prodotto</button> </form>
		</div>
		</div>

  	</td> 
		<%} 
		}
		} %>
	
</tr>		    
</table>
	
</div>
</section>
</table>	


<jsp:include page="common/futher.jsp"/>


</body>
</html>