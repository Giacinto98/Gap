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
		<p align="left">
		<a href="Index.jsp"><img src = "Elementi/logo.png" width="75" height="75"></a>
		<p align="center">
	</div>
	
	<div>
		<p align="center" >
		<input id = "s" type="text" name="ricerca" >
		<a href="ricerca.jsp"><img src = "Elementi/ricerca.png" width="25" height="25"></a>
		</p>
	 </div >
	 
	<%
	UtenteBean utente = new UtenteBean();
	HttpSession sessione = request.getSession(false);
	   if (sessione != null)
	   {
			utente = (UtenteBean) sessione.getAttribute("utente");
			if(utente != null)
			{
			%>
			<div class="sposta" >
				<p class="sposta"><a href="profilo.jsp"><img src = "Elementi/profilo.png" width="30" height="30"></a>
				<%=utente.getNome()%></p>
			
			<% 
			CarrelloBean carrello = (CarrelloBean) sessione.getAttribute ("carrello");
			if(carrello != null)
			{ %>
				<p class="sposta"><a href="logout.jsp"><img src = "Elementi/logout.png" width="35" height="35"></a>
				<a href="carrello.jsp"><img src = "Elementi/carrello.png" width="35" height="35"></a></p>
				<span id="carrello"><%=carrello.getQuantita() %></span>
			</div>
			<%}
			}
	 		else 
	 		{
			 %>
			 
	<div>
	 	<p class="logRec" align="right" >
	 	<a  class="link" title="login" href="loginUser.jsp"> LOGIN</a> 
	 	<a  class="link" title="registrazione" href="Registrazione.jsp" >/REG</a>
		</p>
	</div> 
		
		<%	}
		} 	%>
</header>

<nav id="navbar">
	<ul><h3>
	<li><a class="dropdown" href="#">Aggiungi</a></li>
	<li><a href="#" class="dropdown">Modifica</a></li>
	<li><a href="#">Rimuovi</a></li>
</h3></ul>
</nav>

<h1 align="center">Monitora gli ordini degli utenti</h1>

<%
	response.encodeURL("paginaAdmin.jsp"); //URL rewriting
	Collection<?> ordini = (Collection<?>) request.getAttribute("ordini"); //leggo dalla request l'attributo products che ha la lista degli oggetti 
	
	if(ordini == null){ //se non sono riuscito a prednere i prodotti e non ho l'errore, c'è qualche problema
%>   

<h1>Non sono stati ancora effettuati ordini nel magazzino.</h1>

<%
}
else
	if(ordini != null && ordini.size() > 0) //controllo se ci sono prodotti all'interno dell'array
	{
		Iterator<?> it = ordini.iterator(); //iteriamo i prodotti
		while(it.hasNext()) //fin quando ho prodotti
		{
		OrdineBean bean = (OrdineBean) it.next(); //metto nel bean riferito alla tabella dei prodotti il prodotto i-esimo	
%>

<b><%=bean.getNumeroOrdine()%></b>&nbsp;
<b><%=bean.getEmail()%></b>
<br>

<% }
} %>



<footer style="background:grey; color:white;margin:100px 0px 0px 0px">
	<p align="center">GRAZIE DELLA VISIONE PER SUPPORTARE IL SITO POTETE DONARE AL SEGUENTE IBAN:(iserire qui ibad di Emanuele)</p></footer>


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