<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
</head>
<body>


<%
   String message = (String)request.getAttribute("message");
   String error = (String)request.getAttribute("error");
%>
    <h3>JSP File Upload</h3>

<% if(message != null && !message.equals("")) { %>	
	<p><%=message %>
<% } %>	
<div style="position:relative">
<div style="width: 30%; float:left;" >
	<form method="post" action="<%=response.encodeURL("fileupload")%>" name="echo" enctype="multipart/form-data">
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


<div Style="width: 30%; float:center;">
	<fieldset><legend>Modifica Prodotto</legend>
		<form method="get" action="<%=response.encodeURL("ModificaControl")%>">
			Codice<input type = "text" name="codice" required> <br>
			Prezzo <input type = "text" name="prezzo" required> <br>
			Sconto <input type = "text" name="sconto" required> <br>
			Quantita <input type = "text" name="quantita" required> <br>
			<input type="submit" value="Modifica Prodotto">
			<input type="reset" value="Reset">
		</form>
	</fieldset> 
</div>


<div Style="width: 30%; float:right;">
	<fieldset><legend>Rimuovi Prodotto</legend>
		<form method="get" action="<%=response.encodeURL("RemoveControl")%>">
		Codice<input type = "text" name="codice" required> <br>
		<input type="submit" value="Rimuovi Prodotto">
		</form>
	</fieldset> 
</div>

</div>
	
	
</body>
</html>