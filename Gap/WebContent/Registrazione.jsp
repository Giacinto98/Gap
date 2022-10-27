<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.*, bean.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	 <meta name="viewport" content="width=device-width, initial-scale=1.0">
	
<title>Registrati</title>

<link href="css/generale.css" rel="stylesheet" type="text/css">
<link href="css/index.css" rel="stylesheet" type="text/css">
<link href="css/responsive.css" rel="stylesheet" type="text/css">


<style>
	div 
	{
		width:100%;
		margin=0;
		padding=0;
 	}
 	
 	input[type=button]
	{
	padding:3px; 
	height:28px;
	border-radius: 5px;
    border: 3px solid #0088b3;
    background-color:#0088b3;
    color: white;
	box-shadow: 0px 15px 10px -10px rgba(0,0,0,0.4);
    transition: all 0.5s;
	}

	input[type=button]:hover
	{
	box-shadow: 0px 45px 20px -30px rgba(0,0,0,0.2);
    transform: translateY(-5px);
	}

 	
…
[22:19, 28/12/2021] Emanuele Giammarino: <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	 <meta name="viewport" content="width=device-width, initial-scale=1.0">
	
<title>Registrati</title>

<link href="css/generale.css" rel="stylesheet" type="text/css">
<link href="css/index.css" rel="stylesheet" type="text/css">
<link href="css/responsive.css" rel="stylesheet" type="text/css">


<style>
	div 
	{
		width:100%;
		margin=0;
		padding=0;
 	}
 	
 	input[type=button]
	{
	padding:3px; 
	height:28px;
	border-radius: 5px;
    border: 3px solid #0088b3;
    background-color:#0088b3;
    color: white;
	box-shadow: 0px 15px 10px -10px rgba(0,0,0,0.4);
    transition: all 0.5s;
	}

	input[type=button]:hover
	{
	box-shadow: 0px 45px 20px -30px rgba(0,0,0,0.2);
    transform: translateY(-5px);
	}

 	
	input[type=text]:focus
	{
		background-color:#7fffd4;
	}
	
	input[type=password]
	{
		height:25px;
		border-radius: 5px;
	}
	
	input[type=password]:focus
	{
		background-color:#7fffd4;
	}
</style>

</head>
<body>

<jsp:include page="common/header.jsp"/>

<form name="form" method="post">

<fieldset class="fieldReg">
	<legend><h2>Registrazione</h2></legend>
     
<div>    
    <% 
    	String error="ciao" ;
    	error = (String) request.getAttribute("error");
    	if(error != "" && error != null)
    	{
    %>
    		<p Style= "color:red;"><%=error %></p>
 	<%  } %>
    	
    
    	<table align="center" >
    		<tr>
    			<th>CF</th>
    			<th><input onBlur="controllaCF(cf)" type="text" id="cf" class="clasinp" placeholder="Codie fiscale" name="cf" required> </th>
    			<th><span id="demo" style="font-weight: bold;"></span></th>
    		</tr>
    	
    		<tr>
    			<th>Nome</th>
    			<th><input type="text" id="nome" onBlur="ControlloNome(this.value)" class="clasinp"  placeholder="Nome*" name="nome" required> </th>
    			<th><span id="no" style="font-weight: bold;"></span></th>
    		</tr>
    		
    		<tr>
    			<th>Cognome</th>
    			<th><input type="text" id="cognome" onBlur="ControlloCognome(this.value)" placeholder="Cognome" name="cognome" required> </th>
    			<th><span id="cg" style="font-weight: bold;"></span></th>
    		</tr>
    		
    		<tr>
    			<th>Email</th>
    			<th><input type="text" id="email" onBlur= "validateEmail(email)" placeholder="Email" name="email" required> </th>
    			<th><span id="em" style="font-weight: bold;"></span></th>
    		</tr>
    		
    		<tr>
    			<th>Password</th>
    			<th><input type="password"  id="pass" onBlur="controllaPassword(this.value)" placeholder="Password" name="password" required></th>
    			<th><span id="pa" style="font-weight: bold;"></span></th>
    		</tr>
    		
    		
    		<tr style="margin-top:-50px" >
    			<th></th>
    			<th><div style="font-size:75%; color: #0088b3">(Almeno 6 caratteri, una maiuscola<br>una minuscola,un carattere speciale)</div></th>
    		</tr>
    		
    		<tr>
    			<th>Conferma Password</th>
    			<th><input type="password" id="conferma" onBlur="controllaConfermaPassword(this.value)" placeholder="Conferma Password" name="conferma" required> </th>
    			<th><span id="co" style="font-weight: bold;"></span></th>
    		</tr>
    		
    		<tr>
    			<th>Telefono</th>
    			<th><input type="text" id="telefono" onBlur="phonenumber(telefono)" placeholder="Num_Tel" name="telefono" required> </th>
    			<th><span id="te" style="font-weight: bold;"></span></th>
    		</tr>
    		
    		<tr>
    			<th>Indirizzo</th>
    			<th><input type="text" id="indirizzo" onBlur="ControlloIndirizzo(this.value)" placeholder="Indirizzo n°" name="indirizzo" required> </th>
    			<th><span id="in" style="font-weight: bold;"></span></th>
    		</tr>
    		<tr>
    			<th><input type="button" value="Registrati" onClick="ControlloFinale()"></th>
    		</tr>
    	</table>
    		</div>
		</fieldset>
	</form>


<jsp:include page="common/futher.jsp"/>

<script type="text/javascript">


function ControlloFinale()
{
	var cf = document.form.cf.value;
	var email = document.form.email.value;
	var pass = document.form.password.value;
	var conferma = document.form.conferma.value;
	var telefono = document.form.telefono.value;
	var cont = 0;
	var nome2 = document.form.nome.value;
	var cognome2 = document.form.cognome.value;
	var indirizzo2 = document.form.indirizzo.value;
	
	var mailformat = /^\w+([\.-]?\w+)@\w+([\.-]?\w+)(\.\w{2,3})+$/;
	if(!(email.match(mailformat)))
	{
		document.getElementById("em").style.color = "red";
		document.getElementById("em").innerHTML = "E-mail Errata";
		document.getElementById("email").style.border = "solid 2px red";
		cont++;
	}
	else
	{
		document.getElementById("em").style.color = "green";
			
		document.getElementById("email").style.border = "solid 2px green";
	}
	
	
	
	var passExpression = /^(?=.*[a-z])(?=.*\d)(?=.*[@\.#?!$ %^&*-])(?=.*[A-Z])[a-zA-Z\d@\.#?!$ %^&*-]{6,}$/;
    if(!(passExpression.test(pass)))
	{
		document.getElementById("pa").style.color = "red";
		document.getElementById("pa").innerHTML = "Password Errata";
		document.getElementById("pass").style.border = "solid 2px red";
		cont++;
	}
	else
	{
		document.getElementById("pa").style.color = "green";
		document.getElementById("pa").innerHTML = "";	
		document.getElementById("pass").style.border = "solid 2px green";
	}
	
    
    
	if(conferma != pass)
	{
		document.getElementById("co").style.color = "red";
		document.getElementById("co").innerHTML = "Le password non sono Uguali";
		document.getElementById("conferma").style.border = "solid 2px red";
		cont++;
	}
	else
	{
		document.getElementById("co").style.color = "green";
		document.getElementById("co").innerHTML = "";
		document.getElementById("conferma").style.border = "solid 2px green";
	}
	
	
	
	var pattern = /^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]$/;
	if (cf.search(pattern) == -1)
		{
		document.getElementById("demo").style.color = "red";
		document.getElementById("demo").innerHTML = "Cf Errato";
		document.getElementById("cf").style.border = "solid 2px red";
		cont++;
		}
	else
		{
		document.getElementById("demo").style.color = "green";
			
		document.getElementById("cf").style.border = "solid 2px green";
		}
	
	
	
	var phoneno = /^\d{10}$/;
	if(!(telefono.match(phoneno)))
	{
		document.getElementById("te").style.color = "red";
		document.getElementById("te").innerHTML = "Numero Errato";
		document.getElementById("telefono").style.border = "solid 2px red";
		cont++;
	}
	else
	{
		document.getElementById("te").style.color = "green";
			
		document.getElementById("telefono").style.border = "solid 2px green";
	}
	

	if((nome2!= "") && (nome2!= undefined) && (nome2!=null) )
	{
	
	document.getElementById("no").innerHTML = "";
	document.getElementById("nome").style.border = "solid 2px green";
	}else{
		document.getElementById("no").style.color = "red";
		document.getElementById("no").innerHTML = "Inserisci Nome";
		document.getElementById("nome").style.border = "solid 2px red";
		cont++;		
	}
	
	
	if((cognome2!= "") && (cognome2!= undefined) && (cognome2!=null)){
		document.getElementById("cg").style.color = "green";
		document.getElementById("cg").innerHTML = "";
		document.getElementById("cognome").style.border = "solid 2px green";
		
	}else{
		document.getElementById("cg").style.color = "red";
		document.getElementById("cg").innerHTML = "Inserisci Cognome";
		document.getElementById("cognome").style.border = "solid 2px red";
		cont++;		
	}
	
	
	if((indirizzo2!= "") && (indirizzo2!= undefined) && (indirizzo2!=null)){
		document.getElementById("in").style.color = "green";
		document.getElementById("indirizzo").style.border = "solid 2px green";
		document.getElementById("in").innerHTML = "";
	}else{
		document.getElementById("in").style.color = "red";
		document.getElementById("in").innerHTML = "Inserisci Indirizzo";
		document.getElementById("indirizzo").style.border = "solid 2px red";
		cont++;		
	}
	
	if(cont == 0)
	{
		document.form.action = "<%=response.encodeURL("RegControl")%>";
		document.form.submit();
	}
	
	
}


var password;

function ControlloNome(nome)
{
	console.log(nome);
	if((nome!= "") && (nome!= undefined) && (nome!=null))
	{
	document.getElementById("no").style.color = "green";
	document.getElementById("no").innerHTML = "";
	document.getElementById("nome").style.border = "solid 2px green";
	}else{
		document.getElementById("no").style.color = "red";
		document.getElementById("nome").style.border = "solid 2px red";
		
	}
}

function ControlloCognome(cognome)
{
	console.log(cognome );
	if((cognome!= "") && (cognome!= undefined) && (cognome!=null))
	{
		
	document.getElementById("cg").style.color = "green";
	document.getElementById("cg").innerHTML = "";
	document.getElementById("cognome").style.border = "solid 2px green";
	}else{
		document.getElementById("cg").style.color = "red";
		document.getElementById("cognome").style.border = "solid 2px red";
		
	}
}

function ControlloIndirizzo(indirizzo)
{
	console.log(indirizzo );
	if((indirizzo!= "") && (indirizzo!= undefined) && (indirizzo!=null))
	{
	document.getElementById("in").style.color = "green";
	document.getElementById("in").innerHTML = "";
	document.getElementById("indirizzo").style.border = "solid 2px green";
	}else{
		document.getElementById("in").style.color = "red";
		document.getElementById("indirizzo").style.border = "solid 2px red";
		
	}
}

function validateEmail(email)
{
	var mailformat = /^\w+([\.-]?\w+)@\w+([\.-]?\w+)(\.\w{2,3})+$/;
	if(!(email.value.match(mailformat)))
	{
		document.getElementById("em").style.color = "red";
		
		document.getElementById("email").style.border = "solid 2px red";
		
	}
	else
	{
		document.getElementById("em").style.color = "green";
		document.getElementById("em").innerHTML = "";	
		document.getElementById("email").style.border = "solid 2px green";
	}
}
	
function controllaPassword(pass) 
{
	password = pass;
	//console.log(password);
    var passExpression = /^(?=.*[a-z])(?=.*\d)(?=.*[@\.#?!$ %^&*-])(?=.*[A-Z])[a-zA-Z\d@\.#?!$ %^&*-]{6,}$/;
    if(!(passExpression.test(pass)))
	{
		document.getElementById("pa").style.color = "red";
		document.getElementById("pa").innerHTML = "Password Errata";
		document.getElementById("pass").style.border = "solid 2px red";
	}
	else
	{
		document.getElementById("pa").style.color = "green";
		document.getElementById("pa").innerHTML = "";	
		document.getElementById("pass").style.border = "solid 2px green";
	}
}
	
	
	
	function controllaConfermaPassword(conferma)
	{
		//console.log(password,conferma);
		if(conferma != password)
		{
			document.getElementById("co").style.color = "red";
			document.getElementById("co").innerHTML = "Le password non sono Uguali";
			document.getElementById("conferma").style.border = "solid 2px red";
		}
		else
		{
			document.getElementById("co").style.color = "green";
			document.getElementById("co").innerHTML = "";
			document.getElementById("conferma").style.border = "solid 2px green";
		}
	}
	
	
	function controllaCF(CodiceFiscale)
	{ 
		// 6 caratteri lettera da a a z 2 caratteri numeri ecc...
		var pattern = /^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]$/;
		if (CodiceFiscale.value.search(pattern) == -1)
			{
			document.getElementById("demo").style.color = "red";
			
			document.getElementById("cf").style.border = "solid 2px red";
			}
		else
			{
			document.getElementById("demo").style.color = "green";
			document.getElementById("demo").innerHTML = "";
			document.getElementById("cf").style.border = "solid 2px green";
			}
	}
	
	function phonenumber(inputtxt)
	{
		var phoneno = /^\d{10}$/;
		if(!(inputtxt.value.match(phoneno)))
		{
			document.getElementById("te").style.color = "red";
			
			document.getElementById("telefono").style.border = "solid 2px red";
		}
		else
		{
			document.getElementById("te").style.color = "green";
			document.getElementById("te").innerHTML = "";
			document.getElementById("telefono").style.border = "solid 2px green";
		}
	}
	
</script>

</body>
</html>