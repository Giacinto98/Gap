<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.* , java.lang.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	<link href="css/index.css" rel="stylesheet" type="text/css">
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
<jsp:include page="common/header.jsp"/>

<section id=StampaProdotti style="margin: 3px 3px 3px 3px;">

<table class="tabellaProdotti">
<tr>
<%
	Collection<?> prodotti = (Collection<?>) request.getAttribute("prodotti1"); 
	if(prodotti == null)
		%> <h1 align="center">ERRORE</h1>
		<% 
	if(prodotti != null && prodotti.size() > 0 )
	{	
		Iterator<?> it = prodotti.iterator(); //iteriamo i prodotti
		while(it.hasNext()) //fin quando ho prodotti
		{
		ProdottoBean bean = (ProdottoBean) it.next();
%>

<td> 
		<div id="carta" >
  		<img class="card-img-top" height=200 width=100% src="Elementi/<%=bean.getNome()%>.jpg " alt="Card image cap">
  			<div class="card-body">
    	<h3 class="card-title"><%=bean.getNome()%> <%if(bean.getSconto()>0) {%> <b style="color:red;"> &nbsp;&nbsp; SCONTO <%=bean.getSconto()%> &percnt; </b> <%} %></h3>
    	<p class="card-text">Apri per maggiori iformazioni </p>
    	<form action="<%=response.encodeURL("CercaProdottoControl")%>" method="get"> <button  type="submit" name="bottone" value="<%=bean.getNome()%>">Apri Prodotto</button> </form>
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


<jsp:include page="common/futher.jsp"/>

</body>
</html>