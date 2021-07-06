<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.* , java.lang.*" %>

<%
	response.encodeURL("Index.jsp"); //URL rewriting
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
	<meta name="author" content= "Adinolfi Giacinto">
	 
	<title>Store</title>
	
	<link href="css/responsive.css" rel="stylesheet" type="text/css">
	<link href="css/index.css" rel="stylesheet" type="text/css">
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

<header>
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
	HttpSession sessione = request.getSession(false);
	   if (sessione != null)
	   {
			UtenteBean utente = (UtenteBean) session.getAttribute("utente");
			if(utente != null)
			{
			%>
			<div>
				<p align = "center"><a href="profilo.jsp"><img src = "Elementi/profilo.png" width="30" height="30"></a>
				<br><%=utente.getNome()%></p>
			</div>
				<a href="logout.jsp"><img src = "Elementi/logout.png" width="35" height="35"></a>
				<a href="carrello.jsp"><img src = "Elementi/carrello.png" width="35" height="35"></a>
			<%
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
		} %>
		
		<a href="javascript:void(0)" class="btn-menu" onclick=toggle()>&#9776;</a>	
</header>

<nav id="navbar">
	<ul><h3>
	<li><a class="dropdown" href="#">Poltrone</a>
		<div class="dropdown-content">
			<a href="#">Manuali</a>
			<a href="#">Elettriche</a>
			<a href="#">Pouf</a>
		</div>
	</li>
	<li><a href="#" class="dropdown">Pareti Attrezzate</a>
		<div class="dropdown-content">
			<a href="#">PA1</a>
			<a href="#">PA2</a>
			<a href="#">PA3</a>
		</div>
	</li>
	<li><a href="#">Chi siamo</a></li>
	
	<% 
	UtenteBean utente = (UtenteBean) session.getAttribute("utente"); 
	if(utente != null)
		if(utente.getRuolo().equals("amministratore"))
		{%>
		<li><a href="#">Amministratore</a></li>
		<%} %>
</h3></ul>
</nav>

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
			ProdottoBean bean = (ProdottoBean) it.next(); //metto nel bean riferito alla tabella dei prodotti il prodotto i-esimo	
		%>	

	<td> 

	
		<div class="card" id="carta" >
  		<img class="card-img-top" height=200 width=100% src="Elementi/<%=bean.getNome()%>.jpg " alt="Card image cap">
  			<div class="card-body">
    	<h3 class="card-title"><%=bean.getNome()%></h3>
    	<p class="card-text">Apri per maggiori iformazioni </p>
    	<form action="CercaProdottoControl" method="get"> <button type="submit" name="bottone" value="<%=bean.getNome()%>">Apri Prodotto</button>
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



<footer style="background:grey; color:white;margin:10px 0px 0px 0px">
	<p align="center">GRAZIE DELLA VISIONE PER SUPPORTARE IL SITO POTETE DONARE AL SEGUENTE IBAN:(iserire qui ibad di Emanuele)</p>
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