<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.* , bean.*, java.lang.*"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
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
	String error = (String)request.getAttribute("error");
	if(error != null)
	{ %>
		<div align="center" Style="color:red"><%=error %></div>
  <%}%>
	<%
	String url=response.encodeURL("AumentoProdottoCarrello");
	String url1=response.encodeURL("DiminuzioneProdottoCarrello");
	String url2=response.encodeURL("RimozioneDaCarrello");
	
	HttpSession sessione = request.getSession(false);
	if (sessione != null)
	{float prezzoTot=0;
		CarrelloBean carrello = (CarrelloBean) sessione.getAttribute ("carrello");
		if(carrello != null)
		{
			if(carrello.getQuantita() > 0)
			{
				//codice se carrello ha elementi
				ArrayList <ProdottoBean> prodotti = carrello.getProdotti();
				ArrayList<MaterialeBean> materiali = carrello.getMateriali();
				 %>
				 
				<div class="immagine" >
				<table class="tabellaProdCarrello">
				
					
			
				<%
				int i=0;
				for (; i<prodotti.size(); i++)
				{
				float sconto=0;
				 %>
<tr id = "<%=prodotti.get(i).getCodice()%>_<%=materiali.get(i).getId()%>sezioneProdotto">
<td>
<div style="float: right;clear: right; background-color: white; border-radius:10px;">

<div  style="text-align:center">	
<div>
<%
	if(prodotti.get(i).getSconto() > 0){
		sconto = prodotti.get(i).getPrezzo() - ((prodotti.get(i).getPrezzo() * prodotti.get(i).getSconto())/100);
		prezzoTot = prezzoTot +(sconto * prodotti.get(i).getQuantita());}
	else{
		prezzoTot = prezzoTot + (prodotti.get(i).getPrezzo() * prodotti.get(i).getQuantita());}
		%> 		
<img class="immagineCarrello" src="Elementi/<%=prodotti.get(i).getNome()%>.jpg" alt="<%=prodotti.get(i).getNome()%>">				 
 Nome:<h2><%=prodotti.get(i).getNome()%></h2>
 Materiale :
 <img class="bordo"  title="<%=materiali.get(i).getTipologiaMateriale()%>_<%=materiali.get(i).getColore()%>"  
height=30 width=30 src="Materiali/<%=materiali.get(i).getTipologiaMateriale()%>_<%=materiali.get(i).getColore()%>.jpg " 
alt="Card image cap"><%=materiali.get(i).getTipologiaMateriale()%>

<br> 
 <%
	if(prodotti.get(i).getSconto() > 0){
	%>		
 		Importo:<b><%=sconto%> &euro;</b><br>
<%	}
	else{
		%>
		Importo:<b><%=prodotti.get(i).getPrezzo()%> &euro;</b><br>
	<%}
%> 

</div>	

<div class="divQuantita">

<button type="button" onclick="funzioneMeno('<%=prodotti.get(i).getCodice()%>','<%=materiali.get(i).getId()%>')"> - </button> 
<span id = "<%=prodotti.get(i).getCodice()%>_<%=materiali.get(i).getId()%>"><%=prodotti.get(i).getQuantita()%></span>
<button type="button" onclick="funzionePiu('<%=prodotti.get(i).getCodice()%>','<%=materiali.get(i).getId()%>')"> + </button> 
<button type="button" onclick="rimuovi('<%=prodotti.get(i).getCodice()%>','<%=materiali.get(i).getId()%>')"> Rimuovi </button> 

</div>
</div>	

</div>
</td> 

</tr>

	<% 
				}
				%>				

		    
</table>


<div id="barra" class="immagineDesc2"  >
<fieldset class="fieldReg">
<h2 >TOTALE ORDINE</h2>
Numero di prodotti: <b id ="totale"><%=carrello.getQuantita()%></b><br>
<h3>Importo Totale</h3><b id="importo"> <%=prezzoTot%> &euro; </b>
<form method = "GET" action = "<%=response.encodeURL("./datiCartaAcquisto.jsp")%>"> 
<button type="submit" name="Acquista">Continua Acquisto</button>
</form>

</fieldset>
</div>
</div>			
				<%
			}
			else
			{
				%> 
				<h1 align="center"> Nessun elemento presente all'interno del carrello </h1>
				<%
			}
		}
	}
	
	%>

<script>
function funzionePiu(idProdotto, idMateriale)
{
	var url = '<%=url%>'+"?codiceProdotto=" + encodeURIComponent(idProdotto) + "&idMateriale=" + encodeURIComponent(idMateriale);
	//var url = 'AumentoProdottoCarrello?codiceProdotto=' + encodeURIComponent(idProdotto) + "&idMateriale=" + encodeURIComponent(idMateriale); 
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = //alla risposta della servlet
		function() //aumenta di 1 unità il carrello
		{
			if(xhr.readyState == 4 && xhr.status == 200)
			{
				var response = JSON.parse(xhr.responseText);
				var stringa = response.riferimento;
				document.getElementById(stringa).innerHTML = response.quantita;
				document.getElementById("totale").innerHTML = response.totale;
				document.getElementById("carrello").innerHTML = response.totale;
				document.getElementById("importo").innerHTML = response.prezzoTot + " &euro;";
			}
		}
	xhr.open("GET",url,true);
	xhr.send(null);
}

function funzioneMeno(idProdotto, idMateriale)
{
	var url ='<%=url1%>'+"?codiceProdotto=" + encodeURIComponent(idProdotto) + "&idMateriale=" + encodeURIComponent(idMateriale);
	//var url = 'DiminuzioneProdottoCarrello?codiceProdotto=' + encodeURIComponent(idProdotto) + "&idMateriale=" + encodeURIComponent(idMateriale); 
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = //alla risposta della servlet
		function() //aumenta di 1 unità il carrello
		{
			if(xhr.readyState == 4 && xhr.status == 200)
			{
				var response = JSON.parse(xhr.responseText);
				var stringa = response.riferimento;
					document.getElementById(stringa).innerHTML = response.quantita;
					document.getElementById("carrello").innerHTML = response.totale;
					document.getElementById("totale").innerHTML = response.totale;
					document.getElementById("importo").innerHTML = response.prezzoTot;
			}
		}
	xhr.open("GET",url,true);
	xhr.send(null);
}

function rimuovi(idProdotto, idMateriale)
{
	var url = '<%=url2%>'+"?codiceProdotto=" + encodeURIComponent(idProdotto) + "&idMateriale=" + encodeURIComponent(idMateriale);
	//var url = 'RimozioneDaCarrello?codiceProdotto=' + encodeURIComponent(idProdotto) + "&idMateriale=" + encodeURIComponent(idMateriale); 
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = //alla risposta della servlet
		function() //aumenta di 1 unità il carrello
		{
			if(xhr.readyState == 4 && xhr.status == 200)
			{
				var response = JSON.parse(xhr.responseText);
				var stringa = response.riferimento;
				document.getElementById(stringa).remove();
				document.getElementById("carrello").innerHTML = response.totale;
				document.getElementById("importo").innerHTML = response.prezzoTot;
				document.getElementById("totale").innerHTML = response.totale;
				var totaleElementi = response.totale;
				if(totaleElementi == 0)
					document.getElementById("barra").innerHTML ="<h1>Nessun elemento presente all'interno del carrello</h1>";
			}
		}
	xhr.open("GET",url,true);
	xhr.send(null);
}


</script>



<jsp:include page="common/futher.jsp"/>

</body>
</html>