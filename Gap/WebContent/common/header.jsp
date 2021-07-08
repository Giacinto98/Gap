<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.* , java.lang.*" %>


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
			<div class="sposta" >
			
				<p class="sposta"><a href="profilo.jsp"><img src = "Elementi/profilo.png" width="30" height="30"></a>
				<%=utente.getNome()%></p>
				
				<p class="sposta"><a href="logout.jsp"><img src = "Elementi/logout.png" width="35" height="35"></a>
				<a href="carrello.jsp"><img src = "Elementi/carrello.png" width="35" height="35"></a></p>
				<span id="carrello"></span>
			</div>
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
		} 	%>
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



