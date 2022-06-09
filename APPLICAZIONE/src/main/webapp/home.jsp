<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="API.API_2"
    import="API.ManagementServlet"
   
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME</title>
<style><%@include file="CSSfile.css"%></style> 
</head>
<body>
	a <br>
	<!--  
	<div class="loader">
		<span></span> <span></span> <span></span> <span></span>
	</div>
	-->
	<%
	API_2 utente = (API_2) request.getSession().getAttribute("testLogin");
	%>
	<h1>
		<font color="blue"> <%if(utente != null){ %> CIAO &nbsp;<%=utente.getNome() %>
			<%}else{ %> HOME <%} %>
		</font>
	</h1>
	<br>
	<h2>
		<%if(utente ==null){ %>
		<a href="ManagementServlet?whatsend=login" target="_center"
			type="button"> Login</a>&nbsp;
		<%}else{ %>
		<a href="ManagementServlet?whatsend=form" target="_center"
			type="button"> Inserisci Nuovo Macchinario</a>&nbsp; <br>
		<br> <a href="ManagementServlet?whatsend=LsitaPolizze"
			target="_center" type="button"> Controlla le tue polizze</a>&nbsp;
			<br> <br>
			<a href="ManagementServlet?whatsend=sinistro" target="_center"
			type="button"> Aggiungi un sinistro</a>&nbsp;
			 <br>
			<br>
			 <a href="ManagementServlet?whatsend=listasinistri" target="_center"
			type="button"> Lista sinistri</a>&nbsp;
			 <br>
			<br> 
			<a href="ManagementServlet?whatsend=logout" target="_center"
			type="button"> Logout</a>&nbsp;
		
		<%} %>
	</h2>
</body>

</html>