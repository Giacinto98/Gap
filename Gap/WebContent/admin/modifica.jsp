<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.* , java.lang.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	
	<link href="../css/indexAdmin.css" rel="stylesheet" type="text/css">
	<link href="../css/admin.css" rel="stylesheet" type="text/css">
	<link href="../css/prodotto.css" rel="stylesheet" type="text/css">
	<link href="../css/generale.css" rel="stylesheet" type="text/css">
	<link href="../css/responsive.css" rel="stylesheet" type="text/css">
	
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
		<a href="../Index.jsp"><img src = "../Elementi/logo.png" width="75" height="75"></a>
		<p align="center">
	</div>
	
	
</header>

<nav id="navbar">
	<ul><h3>
	<li><a href="./paginaAdmin.jsp" class="dropdown">Pagina Ordini</a></li>
</h3></ul>
</nav>	


<div id="modificaAmministratore">
<div id="left">
	<form method="post" action="../fileupload" name="echo" enctype="multipart/form-data">
		<fieldset> <legend>Aggiungi Prodotto</legend>
		Nome Prodotto <input type = "text" name="nome" required> <br>
		Altezza <input type = "text" name="altezza" required> <br>
		Profondita' <input type = "text" name="profondita" required> <br>
		Larghezza <input type = "text" name="larghezza" required> <br>
		Tipologia <input type = "text" name="tipologia" required> <br>
		Quantita' <input type = "text" name="quantita" required> <br>
		Prezzo <input type = "text" name="prezzo" required> <br>
		Sconto <input type = "text" name="sconto" required> <br>
		<legend>Materiali</legend>
		<fieldset> 
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
		</fieldset> 
		Carica immagine <input type="file" name="file" accept="image/*" size="50" multiple /> <br>
		<input type="submit" value="Aggiungi Prodotto">
		<input type="reset" value="Reset">
		</fieldset>
	</form>
</div>



<div id="right">
	<fieldset><legend>Modifica Prodotto</legend>
		<form method="get" action="../ModificaControl">
			Codice<input type = "text" name="codice" required> <br>
			Prezzo <input type = "text" name="prezzo" required> <br>
			Sconto <input type = "text" name="sconto" required> <br>
			Quantita <input type = "text" name="quantita" required> <br>
			<input type="submit" value="Modifica Prodotto">
			<input type="reset" value="Reset">
		</form>
	</fieldset> 
</div>

<div id="center">
	<fieldset><legend>Rimuovi Prodotto</legend>
		<form method="get" action="../RemoveControl">
		Codice<input type = "text" name="codice" required> <br>
		<input type="submit" value="Rimuovi Prodotto">
		</form>
	</fieldset> 
</div>
</div>
	

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

<footer style="background:grey; color:white;margin:100px 0px 0px 0px">
	<p align="center">GRAZIE DELLA VISIONE PER SUPPORTARE IL SITO POTETE DONARE AL SEGUENTE IBAN:(iserire qui ibad di Emanuele)</p></footer>
	
</body>
</html>