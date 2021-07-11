<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.* , java.lang.*"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link href="css/index.css" rel="stylesheet" type="text/css">
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
<jsp:include page="common/header.jsp"/>

	<%
	 HttpSession sessione = request.getSession(false);
	if (sessione != null)
	{
		CarrelloBean carrello = (CarrelloBean) sessione.getAttribute ("carrello");
		if(carrello != null)
		{
			if(carrello.getQuantita() > 0)
			{
				//codice se carrello ha elementi
				ArrayList <ProdottoBean> prodotti = carrello.getProdotti();
				for (int i=0; i<prodotti.size(); i++)
				{
				 %>
				 <%=prodotti.get(i).getNome()%>
				 <%=prodotti.get(i).getQuantita()%>
				  <br>
				<% 
				}
				//sessione.setAttribute("array", prodotti);
				
				%> 
				<form method = "GET" action = "AcquistoControl"> 
  				<button type="submit" name="Acquista">Acquista</button>  
				</form>
				<%
			}
			else
			{
				//codice se carrello è vuoto
				%> 
				

				<%
			}
		}
    }
	%>-->



<jsp:include page="common/futher.jsp"/>

</body>
</html>