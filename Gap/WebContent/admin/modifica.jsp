<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.* , bean.*, java.lang.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	
	<link href="./css/indexAdmin.css" rel="stylesheet" type="text/css">
	<link href="./css/admin.css" rel="stylesheet" type="text/css">
	<link href="./css/prodotto.css" rel="stylesheet" type="text/css">
	<link href="./css/generale.css" rel="stylesheet" type="text/css">
	<link href="./css/responsive.css" rel="stylesheet" type="text/css">
	
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
	
<header>	

<a href="javascript:void(0)" class="btn-menu" onclick=toggle()>&#9776;</a>
	
	<div>
		<p align="center">
		<a href="./logout.jsp"><img src = "./Elementi/logout.png" width="50" height="50"></a>
		<p align="center">
	</div>
	
	
</header>

<div id="modificaAmministratore" >
<div id="left">

	<form  method="post" action="<%=response.encodeURL("./fileupload")%>" name="echo" enctype="multipart/form-data">
		<fieldset Style="background-color:white; border-radius:10px; border: none; margin-top: 0px"> 
		<legend><h3 Style="color: #0088b3">Aggiungi Prodotto</h3></legend>
		<%
	String error = (String)request.getAttribute("errore"); //mi prendo anche la stringa dove tengo scritta una eventuale eccezione
	if(error != null){ //se non sono riuscito a prednere i prodotti e non ho l'errore, c'è qualche problema
	%>
	<div align="center" Style="color:red"><%=error %></div>
	<% 
	}
%> 
		<table >
		<tr>
		<th>Nome</th> 
		<th><input type = "text" name="nome" required></th> 
		</tr>
		
		<tr>
		<th>Altezza</th> 
		<th><input type = "text" name="altezza" required></th> 
		</tr>
		
		<tr>
		<th>Profondita'</th> 
		<th><input type = "text" name="profondita" required></th> 
		</tr>
		
		<tr>
		<th>Larghezza</th> 
		<th><input type = "text" name="larghezza" required></th> 
		</tr>

		<tr>
		<th><label for="tipologia">Tipologia</label></th> 
		<th>
			<select name="tipologia" form="formModifica">
			<option value="manuale">Manuale</option>
			<option value="elettrica">Elettrica</option>
			<option value="pouf">Pouf</option>
			<option value="moderna">Moderna</option>
			<option value="classica">Classica</option>
			</select>
		</th> 
		</tr>
			
		
		<tr>
		<th>Quantita'</th> 
		<th><input type = "text" name="quantita" required></th> 
		</tr>
		
		<tr>
		<th>Prezzo</th> 
		<th> <input type = "text" name="prezzo" required></th> 
		</tr>
		
		<tr>
		<th>Sconto</th> 
		<th><input type = "text" name="sconto" required></th> 
		</tr>
		
		<tr>
		<th><legend>Materiali</legend></th> 
		<th><fieldset> 
		Abelia (monocromo)<input type="checkbox" name = "abelia_monocromo" value="2"> <br>
		Bergonia (marrone)<input type="checkbox" name = "bergonia_marrone" value="3"> <br>
		Lilum (blu)<input type="checkbox" name = "lilum_blu" value="4"> <br>
		Liroe (bianco)<input type="checkbox" name = "liroe_bianco" value="5"> <br>
		Pelle (beige)<input type="checkbox" name = "pelle_beige" value="9"> <br>
		Pelle (nero)<input type="checkbox" name = "pelle_nero" value="6"> <br>
		Pelle (ombra)<input type="checkbox" name = "pelle_ombra" value="7"> <br>
		Pelle (rosso)<input type="checkbox" name = "pelle_rosso" value="1"> <br>
		Santolina (bordeaux)<input type="checkbox" name = "santolina_bordeaux" value="8"> <br>
		Solidago (monocromo)<input type="checkbox" name = "solidago_monocromo" value="10"> <br>
		</fieldset> </th> 
		</tr>
		
		<tr>
		<th>Carica immagine</th> 
		<th> <input type="file" name="file" accept="image/*" size="50" multiple /></th> 
		</tr>
		
		<tr>
		<th><input type="submit" value="Aggiungi Prodotto"></th> 
		<th> <input type="reset" value="Reset"></th> 
		</tr>
		
		</table>
		</fieldset>
	</form>
</div>



<div id="right" Style="margin-right:15px">
		<form Style="margin-bottom:50px; margin-top:0px;" method="get" action="<%=response.encodeURL("./ModificaControl")%>">
		<fieldset Style="background-color:white; border-radius:10px; border: none;  margin-top: 0px"> 
		<legend><h3 Style="color: #0088b3">Modifica Prodotto</h3></legend>
	<%
	String error2 = (String)request.getAttribute("errore2"); 
	if(error2 != null){ 
	%>
	<div align="center" Style="color:red"><%=error2 %></div>
	<% }%> 
			<table>
			<tr>
				<th>Codice</th> 
				<th><input type = "text" name="codice" required></th> 
			</tr>
			
			<tr>
				<th>Prezzo</th> 
				<th><input type = "text" name="prezzo" required> </th> 
			</tr>
			
			<tr>
				<th>Sconto</th> 
				<th><input type = "text" name="sconto" required></th> 
			</tr>
			
			<tr>
				<th>Quantita</th> 
				<th><input type = "text" name="quantita" required></th> 
			</tr>
			
			<tr>
				<th><input type="submit" value="Modifica Prodotto"></th> 
				<th><input type="reset" value="Reset"></th> 
			</tr>
			</table>
	</fieldset> 
	</form>
</div>

<div id="center">
	<form Style="margin-bottom:50px; margin-top:-30px;" method="get" action="<%=response.encodeURL("./RemoveControl")%>">
		<fieldset Style="background-color:white; border-radius:10px; border: none;  margin-top: 25px"> 
		<legend><h3 Style="color: #0088b3">Rimuovi Prodotto</h3></legend>
		<%
		String error1 = (String)request.getAttribute("errore1"); 
		if(error1 != null){ 
		%>
		<div align="center" Style="color:red"><%=error1 %></div>
		<%}%> 
		<table>
		<tr>
		<th>Codice</th> 
		<th><input type = "text" name="codice" required></th> 
		</tr>
		
		<tr>
		<th><input type="submit" value="Rimuovi Prodotto"></th> 
		</tr>

		</table>
		</fieldset> 
	</form>
	
</div>

</div>

<table class="tabellaProdottiPerModifica" >
<tr>
	<th>Codice</th> 
	<th>Nome</th> 
	<th>Altezza</th> 
	<th>Profondita</th>
	<th>Larghezza</th>
	
	<th>Quantita</th>   
	<th>Prezzo</th> 
	<th>Sconto</th> 
</tr>


<%
	Collection<?> prodotti = (Collection<?>) request.getAttribute("prodotti");
	if(prodotti == null && error == null){ //se non sono riuscito a prednere i prodotti e non ho l'errore, c'è qualche problema
		response.sendRedirect(response.encodeRedirectURL("./ProdottoControl")); //chiamiamo noi la servlet
		return;	
	}
	if(prodotti != null && prodotti.size() > 0) //controllo se ci sono prodotti all'interno dell'array
	{
		Iterator<?> it = prodotti.iterator(); //iteriamo i prodotti
		while(it.hasNext()) //fin quando ho prodotti
		{
			ProdottoBean bean = (ProdottoBean) it.next();//metto nel bean riferito alla tabella dei prodotti il prodotto i-esimo	
			if(bean.getQuantita() > 0)
			{
%>
		<tr >
		<th ><%=bean.getCodice()%></th> 
		<th><%=bean.getNome()%></th> 
		<th><%=bean.getAltezza()%></th> 
		<th><%=bean.getProfondita()%></th> 
		<th><%=bean.getLarghezza()%></th> 
		
		<th><%=bean.getQuantita()%></th> 
		<th><%=bean.getPrezzo()%></th> 
		<th><%=bean.getSconto()%></th> 
		</tr>
<%			}
		}
	}
%>
</table>







<script>
function toggle() 
{
	if(document.getElementById("navbar").style.display=="none"){
		document.getElementById("navbar").style.display="block";
	}else{
		document.getElementById("navbar").style.display="none";
		}
}
</script>

	
</body>
</html>