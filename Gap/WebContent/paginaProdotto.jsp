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
<div class="immagine">
	
	
	<%
		ProdottoBean prodotto = new ProdottoBean(); 
		prodotto = (ProdottoBean) request.getAttribute("prodotto");
		Collection<?> materiali = (Collection<?>) request.getAttribute("materiali");	
	%>
	
	<img class="immagineFoto" src="Elementi/<%=prodotto.getNome()%>.jpg" alt="<%=prodotto.getNome()%>">

<fieldset class="bordino">
	<div class="immagineDesc">
		<h1><%=prodotto.getNome()%></h1>
		<b>Dimensioni: </b><b><em>h.</em></b><%=prodotto.getAltezza()%>
		<b><em>L.</em></b><%=prodotto.getLarghezza()%>
		<b><em>P.</em></b><%=prodotto.getProfondita()%>
		<br>
		<b>Tipologia: <%=prodotto.getTipologia()%></b>
		<%if(prodotto.getSconto() > 0)
	  	  { %>
			<h3 style="color:red">Prezzo </h3> <h2><del><%=prodotto.getPrezzo()%></del>  $ </h2>
	  		<%float sconto = prodotto.getPrezzo() - ((prodotto.getPrezzo() * prodotto.getSconto())/100); %>
			<h3 style="color:red">Prezzo Scontato </h3> <h2><%=sconto%> $ <br> </h2>
	   <% } 
		
		else 
			{ %>
	   			<h3 style="color:red">Prezzo </h3> <h2><%=prodotto.getPrezzo()%> $ </h2>
		<%  } 
		//controlliamo se non ci sono prodotti da mostrare
				if(materiali != null && materiali.size() > 0) //controllo se ci sono prodotti all'interno dell'array
				{
			%> <div> <% 
					Iterator<?> it = materiali.iterator(); //iteriamo i prodotti
					while(it.hasNext()) //fin quando ho prodotti
					{
					MaterialeBean materiale = (MaterialeBean) it.next(); //metto nel bean riferito alla tabella dei prodotti il prodotto i-esimo	
			%> 
					<img class="bordo"  title="<%=materiale.getTipologiaMateriale()%>_<%=materiale.getColore()%>"  onclick="selezionaColore ('<%=materiale.getTipologiaMateriale()%>_<%=materiale.getColore()%>')" 
					 height=30 width=30 src="Materiali/<%=materiale.getTipologiaMateriale()%>_<%=materiale.getColore()%>.jpg " alt="Card image cap">
				  <%} 
				}%>
				</div>
		  		<p id="demo"></p>
		
		
		
		<button type="submit" name="bottone" onclick="aggiungiAlCarrello('<%=prodotto.getNome()%>')" value="<%=prodotto.getNome()%>"> Aggiungi al carrello</button>
	</div>	
</fieldset>
	
</div>


<footer style="background:grey; color:white;margin:10px 0px 0px 0px">
	<p align="center">GRAZIE DELLA VISIONE PER SUPPORTARE IL SITO POTETE DONARE AL SEGUENTE IBAN:(iserire qui ibad di Emanuele)</p>
</footer>


<script type="text/javascript">
var colore = null;

	function selezionaColore (c)
	{
		colore = c;
	}
	
	function aggiungiAlCarrello(nome)
	{
		
		if(colore==null)
			{
			document.getElementById("demo").innerHTML = "Scegliere una tipologia di materiale per il prodotto";
			return;
			}
		else
			document.getElementById("demo").innerHTML = "";

			//alert(JSON.stringify(oggetto));
			
			var url = 'CarrelloControl?nome=' + encodeURIComponent(nome) + "&colore=" + encodeURIComponent(colore);
			
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = //alla risposta della servlet
				function() //aumenta di 1 unità il carrello
				{
					if(xhr.readyState == 4 && xhr.status == 200)
					{
						var response = JSON.parse(xhr.responseText); //stringa che contiene la risposta da parte del server
						document.getElementById("carrello").innerHTML = response.number;
					}
				}
			xhr.open("GET",url,true);
			xhr.send(null);
	}
</script>


</body>
</html>