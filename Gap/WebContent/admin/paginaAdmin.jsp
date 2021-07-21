<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.* , java.lang.*"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link href="css/indexAdmin.css" rel="stylesheet" type="text/css">
	<link href="css/prodotto.css" rel="stylesheet" type="text/css">
	<link href="css/generale.css" rel="stylesheet" type="text/css">
	<link href="css/responsive.css" rel="stylesheet" type="text/css">
	<link href="admin.css" rel="stylesheet" type="text/css">
	
<style>
		div 
		{
			width:100%;
			margin:0;
			padding:0;
		}
</style>

</head>
<body >
<header>	

<a href="javascript:void(0)" class="btn-menu" onclick=toggle()>&#9776;</a>
	 
<div>
	<p align="center">
	<a href="./Index.jsp"><img src = "Elementi/logo.png" width="75" height="75"></a>
	<p align="center">
</div>
					
</header>

<nav id="navbar">
	<ul><h3>
	<li><a href="./admin/modifica.jsp" class="dropdown">Modifica</a></li>
</h3></ul>
</nav>

<h1 align="center">Monitora gli ordini degli utenti</h1>

<%
	response.encodeURL("paginaAdmin.jsp"); //URL rewriting
	Collection<?> ordini = (Collection<?>) request.getAttribute("ordini"); //leggo dalla request l'attributo products che ha la lista degli oggetti 
	
	if(ordini == null){ 
		response.sendRedirect(response.encodeRedirectURL("../AdminControl")); //chiamiamo noi la servlet
		return;//se non sono riuscito a prednere i prodotti e non ho l'errore, c'è qualche problema
}
else
	if(ordini != null && ordini.size() > 0) //controllo se ci sono prodotti all'interno dell'array
	{%>
	<table class="tabellaCiao">
	<tr>
		
	<%
		Iterator<?> it = ordini.iterator(); //iteriamo i prodotti
		while(it.hasNext()) //fin quando ho prodotti
		{
		OrdineBean bean = (OrdineBean) it.next(); //metto nel bean riferito alla tabella dei prodotti il prodotto i-esimo	
%>
<td> 
	<div style="width:20rem;height:5rem;">
Ordine n°:  <b><%=bean.getNumeroOrdine()%></b>&nbsp;
E-mail: <b><%=bean.getEmail()%></b><br>
N° prodotti: <b><%=bean.getNumeroProdotti()%></b>
Importo: <b><%=bean.getPrezzoTotale()%></b>
<br>

</div>

</td>
<% }
} %>

</tr>		    
</table>

<footer style="background:#0088b3; border-radius: 5px; color:white;margin:100px 0 0 0;height:100px;">
	
	<div style="float:left;margin-top:10px;">
		<a href="Index.jsp"><img src = "Elementi/logo.png" width="75" height="75"></a>

<span style="margin-left:40%;">
	SEGUICI:
	<img src = "Elementi/instagram.png" width="50" height="50">
	<img src = "Elementi/facebook.png" width="50" height="50">
	
</span>
</div>
</footer>

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