<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.* , java.lang.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	
	<link href="css/index.css" rel="stylesheet" type="text/css">
	<link href="css/indexAdmin.css" rel="stylesheet" type="text/css">
	<link href="css/prodotto.css" rel="stylesheet" type="text/css">
	<link href="css/generale.css" rel="stylesheet" type="text/css">
	<link href="css/responsive.css" rel="stylesheet" type="text/css">
	<link href="admin/admin.css" rel="stylesheet" type="text/css">
	
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
	<li><a href="./paginaAdmin.jsp" class="dropdown">Pagina Ordini</a></li>
</h3></ul>
</nav>	


<form action="ModificaControl" method="POST" enctype = "multipart/form-data">
	<fieldset> <legend>Aggiungi Prodotto</legend>
		Nome Prodotto <input type = "text" name="nome" required>
		Altezza <input type = "text" name="altezza" required>
		Profondita' <input type = "text" name="profondita" required>
		Larghezza <input type = "text" name="larghezza" required>
		Tipologia <input type = "text" name="tipologia" required>
		Quantita' <input type = "text" name="quantita" required>
		Prezzo <input type = "text" name="prezzo" required>
		Sconto <input type = "text" name="sconto" required>
		Carica immagine <input type="file" name="immagine" size="50" >
		<input type="submit" value="Aggiungi Prodotto">
	</fieldset>
</form>
	
	
	
	
	
	
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