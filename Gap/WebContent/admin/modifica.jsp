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
	<li><a href="<%=response.encodeURL("./paginaAdmin.jsp")%>" class="dropdown">Pagina Ordini</a></li>
</h3></ul>
</nav>	


<div id="modificaAmministratore">
<div id="left">
	<form  method="post" action="<%=response.encodeURL("../fileupload")%>" name="echo" enctype="multipart/form-data">
		<fieldset Style="background-color:white; border-radius:10px; border: none"> 
		<legend><h3 Style="color: #0088b3">Aggiungi Prodotto</h3></legend>
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
		<form Style="margin-bottom:50px; margin-top:0px;" method="get" action="<%=response.encodeURL("../ModificaControl")%>">
		<fieldset Style="background-color:white; border-radius:10px; border: none"> 
		<legend><h3 Style="color: #0088b3">Modifica Prodotto</h3></legend>
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
	<form Style="margin-bottom:50px; margin-top:-30px;" method="get" action="<%=response.encodeURL("../RemoveControl")%>">
		<fieldset Style="background-color:white; border-radius:10px; border: none"> 
		<legend><h3 Style="color: #0088b3">Rimuovi Prodotto</h3></legend>
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