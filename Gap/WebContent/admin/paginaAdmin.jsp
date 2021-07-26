<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.* , java.lang.*"%>
<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	
	<link href="css/indexAdmin.css" rel="stylesheet" type="text/css">
	<link href="css/prodotto.css" rel="stylesheet" type="text/css">
	<link href="css/generale.css" rel="stylesheet" type="text/css">
	<link href="css/responsive.css" rel="stylesheet" type="text/css">
	<link href="admin.css" rel="stylesheet" type="text/css">
<style>

* {
  box-sizing: border-box;
}
div {
	width:100%;
	margin:0;
	padding:0;
	
	}
body {
  font: 16px Arial;  
}

.autocomplete {
  position: relative;
  display: inline-block;
}

input {
  border: 1px solid transparent;
  background-color: #f1f1f1;
  padding: 5px;
  font-size: 16px;
}

input[type=text] {
  width: 100%;
  height: 25px;
  border-radius: 5px;
  
}

input[type=submit] {
  background-color: #e6e6fa;
  color: bleck;
  cursor: pointer;
  height: 30px;
  border-radius: 5px;
}

.autocomplete-items {
  position: absolute;
  border: 1px solid #d4d4d4;
  border-bottom: none;
  border-top: none;
  z-index: 99;
  /position the autocomplete items to be the same width as the container:/
  top: 100%;
  left: 0;
  right: 0;
}

.autocomplete-items div {
  padding: 10px;
  cursor: pointer;
  background-color: #fff; 
  border-bottom: 1px solid #d4d4d4; 
}


.autocomplete-items div:hover {
  background-color: #e9e9e9; 
}


.autocomplete-active {
  background-color: DodgerBlue !important; 
  color: #ffffff; 
}

</style>


<header>	

<a href="javascript:void(0)" class="btn-menu" onclick=toggle()>&#9776;</a>
	 
<div>
	<p align="center">
	<a href="<%=response.encodeURL("./Index.jsp")%>"> <img src = "Elementi/logo.png" width="75" height="75"> </a>
	<p align="center">
</div>
					
</header>

<nav id="navbar">
	<ul><h3>
	<li><a href="<%=response.encodeURL("./admin/modifica.jsp")%>" class="dropdown">Modifica</a></li>
</h3></ul>
</nav>

<h1 align="center">Monitora gli ordini degli utenti</h1>

<%
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
	<div style="width:20rem;height:5rem; background-color:white;border-radius:5px;">
<span style="color:#0088b3"> <b>Ordine n°:</b></span> <%=bean.getNumeroOrdine()%>&nbsp; <br>
<span style="color:#0088b3"><b>Email:</b></span> <%=bean.getEmail()%><br>
<span style="color:#0088b3"><b>N° prodotti:</b></span> <%=bean.getNumeroProdotti()%><br>
<span style="color:#0088b3"><b>Importo:</b></span><%=bean.getPrezzoTotale()%><br>
<br>

</div>

</td>
<% }
} %>

</tr>		    
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