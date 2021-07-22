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
	div {
	width:100%;
	margin:0;
	padding:0;
	}
	</style>
	
</head>
<body>

<%
	HttpSession sessione = request.getSession(false);
	if (sessione != null)
	   {
			CarrelloBean carrello = new CarrelloBean();
			if(carrello != null)
			{
				session.setAttribute("carrello", carrello);
			}
	   }
	%>
	
<jsp:include page="common/header.jsp"/>
	
	<p align="center"><h1 >Acquisto effettuato correttamente</h1></p>
	<p align="center"><h2>Torna alla <a href="<%=response.encodeURL("Index.jsp")%>">HOME PAGE</a></h2></p>

<jsp:include page="common/futher.jsp"/>
</body>
</html>