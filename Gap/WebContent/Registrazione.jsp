<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	 <meta name="viewport" content="width=device-width, initial-scale=1.0">
	
<title>Registrati</title>

<link href="css/index.css" rel="stylesheet" type="text/css">
<link href="css/responsive.css" rel="stylesheet" type="text/css">	

<style>
	div 
	{
		width:100%;
		margin=0;
		padding=0;
 	}
	input:focus
	{
		background-color:#7fffd4;
	}
</style>

</head>
<body>

<header >
<p>	<div>
		<p align="left">
		<a href="Index.jsp"><img src = "Elementi/logo.png" width="75" height="75"></a>
		</p>
	</div>
	<div>
	<p align="center" >
		<input id = "s" type="text" name="ricerca" >
		<input type="submit" name= "ricerca" value="Ricerca" placeholder="Nome prodotto" >
		</p>
	 </div >
	 
	 	<div >
	 	 <p class="logRec" align="right" >
	 	<a  class="link" title="login" href="inserisci link"> LOGIN</a> 
	 	<a  class="link" title="registrazione" href="Registrazione.jsp" >/REG</a>
		</p>
		</div>    
		
	<a href="javascript:void(0)" class="btn-menu" onclick=toggle()>&#9776;</a>
	 
</header>

<nav id="navbar" >
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
</h3></ul>

</nav>



<form action="RegControl" method="post">

<fieldset>
	<legend><h2>Registrazione</h2></legend>
     
<div>    
    <% 
    	String error = (String) request.getParameter("error");
    	if(error != null)
    %>
    		<p><%=error %></p>
 	
    	
    <p id="demo" style="color:red"></p>
    	<table style= >
    		<tr>
    			<th>CF</th>
    			<th><input onBlur="controllaCF(cf)" type="text" class="clasinp" placeholder="Codic fiscale" name="cf" required> </th>
    		</tr>
    	
    		<tr>
    			<th>Nome</th>
    			<th><input type="text" class="clasinp"  placeholder="Nome" name="nome" required> </th>
    		</tr>
    		
    		<tr>
    			<th>Cognome</th>
    			<th><input type="text" placeholder="Cognome" name="cognome" required> </th>
    		</tr>
    		
    		<tr>
    			<th>Email</th>
    			<th><input type="text" onBlur= "validateEmail(email)" placeholder="email" name="email" required> </th>
    		</tr>
    		
    		<tr>
    			<th>Password</th>
    			<th><input type="password" onBlur="password(password)" placeholder="Password" name="password" required></th>
    		</tr>
    		
    		<tr>
    			<th>Conferma Password</th>
    			<th><input type="password"    placeholder="Password" name="conferma_password" required> </th>
    		</tr>
    		
    		<tr>
    			<th>Telefono</th>
    			<th><input type="text" onBlur="phonenumber(telefono)" placeholder="Num_Tel" name="telefono" required> </th>
    		</tr>
    		
    		<tr>
    			<th>Indirizzo</th>
    			<th><input type="text" placeholder="Indirizzo n�" name="indirizzo" required> </th>
    		</tr>
    		
    	</table>
    <input type="submit" value="Registrati"/>
	</form>
	</div>
</fieldset>

<script type="text/javascript">

	function toggle() 
	{
		if(document.getElementById("navbar").style.display=="none"){
			document.getElementById("navbar").style.display="block";
		}else{
			document.getElementById("navbar").style.display="none";
		}
	}

	function validateEmail(email)
	{
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(!(email.value.match(mailformat)))
		document.getElementById("demo").innerHTML = "Inserire campo email correttamente";
	else
		document.getElementById("demo").innerHTML = "";
	}
	
	
	function password (password) 
	{
	    var regularExpression  = /^(?=.[a-z])(?=.[A-Z])(?=.\d)(?=.[#$^+=!*()@%&]).{8,}$/; 
	    if(!(regularExpression.value.match(password)))
	    	document.getElementById("demo").innerHTML = "Inserire campo password e conferma correttamente";
	    else
	    	document.getElementById("demo").innerHTML = "";
	}
	
	
	function controllaCF(CodiceFiscale)
	{ 
		// 6 caratteri lettera da a a z 2 caratteri numeri ecc...
		var pattern = /^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]$/;
		if (CodiceFiscale.value.search(pattern) == -1)
			document.getElementById("demo").innerHTML = "Inserire campo CF correttamente";
		else
			document.getElementById("demo").innerHTML = "";	
	}
	
	function phonenumber(inputtxt)
	{
		var phoneno = /^\d{10}$/;
		if(!(inputtxt.value.match(phoneno)))
			document.getElementById("demo").innerHTML = "Inserire numero di telefono corretto";
		else
			document.getElementById("demo").innerHTML = "";	
	}
	
</script>


<footer style="background:grey; color:white;margin:10px 0px 0px 0px">
<p align="center">GRAZIE DELLA VISIONE PER SUPPORTARE IL SITO POTETE DONARE AL SEGUENTE IBAN:(iserire qui ibad di Emanuele)
</p>
</footer>

</body>
</html>