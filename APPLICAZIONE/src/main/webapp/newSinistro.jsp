<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="API.API_2"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SINISTRO</title>
</head>
<body>
	<a href="ManagementServlet?whatsend=home" target="_center"> <input
		type="button" value="HOME"></a>&nbsp;

	<%
	API_2 utente = (API_2) request.getSession().getAttribute("testLogin");
	%>

<h2> VUOI AGGIUNGERE UN SINISTRO ?</h2>
<br>
<%=utente.getNome() %> fornisci i seguenti dati <br>

<br>

<form name="sinistro" action="ManagementServlet" method="post">
<table>

			<tr>
				<td>idPolizza:</td>
				<td><input name="idPolizza" value="" type="number" step="any"></td>
			</tr>
</table>
<br> <input name="whatsend" value="sinistro" type="hidden"> <input
			type="submit" value="invia">

</form>
</body>
</html>