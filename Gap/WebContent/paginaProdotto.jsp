<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.* , bean.*, java.lang.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">	
	<meta name="author" content= "Adinolfi Giacinto">
	<meta charset="ISO-8859-1">

<title>Prodotto</title>
	
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

<div class="immagine">
	<%
		String url = response.encodeURL("CarrelloControl");
		ProdottoBean prodotto = new ProdottoBean(); 
		prodotto = (ProdottoBean) request.getAttribute("prodotto");
		Collection<?> materiali = (Collection<?>) request.getAttribute("materiali");	
	%>
	
	<img class="immagineFoto" src="Elementi/<%=prodotto.getNome()%>.jpg" alt="<%=prodotto.getNome()%>" Style="height:500px; width:800px;"}>

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
			<h3 style="color:red">Prezzo </h3> <h2><del><%=prodotto.getPrezzo()%></del> &euro; </h2>
	  		<%float sconto = prodotto.getPrezzo() - ((prodotto.getPrezzo() * prodotto.getSconto())/100); %>
			<h3 style="color:red">Prezzo Scontato </h3> <h2><%=sconto%> &euro; <br> </h2>
	   <% } 
		
		else 
			{ %>
	   			<h3 style="color:red">Prezzo </h3> <h2><%=prodotto.getPrezzo()%> $ </h2>
		<%  } 
		//controlliamo se non ci sono prodotti da mostrare
				if(materiali != null && materiali.size() > 0) //controllo se ci sono prodotti all'interno dell'array
				{
			%> <div> <% 
					Iterator<?> it1 = materiali.iterator(); //iteriamo i prodotti
					String array = "";
					while(it1.hasNext()) //fin quando ho prodotti
					{
						MaterialeBean materiale = (MaterialeBean) it1.next(); //metto nel bean riferito alla tabella dei prodotti il prodotto i-esimo	
						array+=materiale.getId();
					}
					
					Iterator<?> it = materiali.iterator(); //iteriamo i prodotti
					 //metto nel bean riferito alla tabella dei prodotti il prodotto i-esimo	
					while(it.hasNext()) //fin quando ho prodotti
					{
						MaterialeBean materiale = (MaterialeBean) it.next();
				  %> 
					<img class="bordo" id="<%=materiale.getId()%>" title="<%=materiale.getTipologiaMateriale()%>_<%=materiale.getColore()%>"  onclick="selezionaColore('<%=materiale.getId()%>','<%=array%>')"
					 height=30 width=30 src="Materiali/<%=materiale.getTipologiaMateriale()%>_<%=materiale.getColore()%>.jpg " alt="Card image cap">
				  <%} 
				}%>
				</div>
		  		<p id="demo" Style="color:red"></p>
		  		<p id="aggiunta" Style="color:#0088b3"></p>
		
		<%	
		
		HttpSession sessione = request.getSession(false);
		   if (sessione != null)
		   {
			   UtenteBean utente = (UtenteBean) sessione.getAttribute("utente");
				if(utente != null)
				{
			%> 
					<button type="submit" name="bottone" onclick="aggiungiAlCarrello('<%=prodotto.getNome()%>','<%=utente.getNome()%>')"> Aggiungi al carrello</button>
			<%	}
				else
				{ %>
					<button type="submit" name="bottone" onclick="cane()"> Aggiungi al carrello</button>
			<% 	} 
		   }
		    %>
	</div>
</fieldset>
</div>

<p align=center style="margin-top:200px; color: #0088b3"><b>RECENSIONI</b></p>
<table class="tabellaRecensioni" >
<tr>
<%
	Collection<?> recensioni = (Collection<?>) request.getAttribute("recensioni");
if(recensioni != null && recensioni.size() > 0) //controllo se ci sono prodotti all'interno dell'array
{
	Iterator<?> it = recensioni.iterator(); //iteriamo i prodotti
	while(it.hasNext()) //fin quando ho prodotti
	{
	RecensioneBean bean = (RecensioneBean) it.next(); 
%>
<td> 
<div class="recensioneDiv" >
<fieldset class="fieldReg" style="margin-top:5px" >
<h3><span style="color:#0088b3"><%=bean.getEmail() %> <br></span> </h3>
<h4><%=bean.getTesto()%></h4>
</fieldset>
</div>
  	</td>
  	</tr> 
		<% }
		}else{ %>
		<h3>Non sono presenti recensioni per questo prodotto</h3>
		<%}%>
		
	
		    
</table>
<jsp:include page="common/futher.jsp"/>


<script type="text/javascript">
var id = null;

	function selezionaColore (identificativo,array)
	{
	id = identificativo;
	var i=0;
	
	while(i<array.length)
	{
		if(array.charAt(i)!="" && array.charAt(i) == identificativo)
			document.getElementById(identificativo).style.border = "3px solid #0088b3";
		else if(array.charAt(i)!=" ")
			document.getElementById(array.charAt(i)).style.border = "none";
		i++;
	}
}
	
	function aggiungiAlCarrello(nome,utente)
	{
		
		if(id==null)
			{
			document.getElementById("demo").innerHTML = "Scegliere una tipologia di materiale per il prodotto";
			return;
			}
		else
			{
			document.getElementById("aggiunta").innerHTML = "Prodotto inserito nel carrello";
			document.getElementById("demo").innerHTML = "";
			}
			    var url = '<%=url%>' + "?nome=" + encodeURIComponent(nome) + "&idMateriale=" + encodeURIComponent(id);
			    console.log(url);
				//var url = 'CarrelloControl?nome=' + encodeURIComponent(nome) + "&idMateriale=" + encodeURIComponent(id);
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
	
	function cane()
	{
			document.getElementById("demo").innerHTML = "Devi essere loggato per poter visualizzare/inserire prodotti all'interno del carrello";
			return;
	}
</script>


</body>
</html>