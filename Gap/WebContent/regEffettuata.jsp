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
<jsp:include page="common/header.jsp"/>

	<p align="center"><h1 >Complimenti, ti sei registrato correttamente</p></h1>
	<p align="center"><h2>Torna alla home ed effettua il <a href="<%=response.encodeURL("Index.jsp")%>">LOGIN</h2></a></p>

<jsp:include page="common/futher.jsp"/>
</body>
</html>