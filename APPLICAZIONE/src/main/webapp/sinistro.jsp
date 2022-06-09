<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="API.API_2"
    import="API.API_7"
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
	<%
	API_7 sinistro = (API_7) request.getSession().getAttribute("sinistro");
	%>

<h2> SINISTRO APERTO</h2>
<br>
<%=utente.getNome() %>

<br>
<%=sinistro.getId() %>

<br><br><br> GESTIRE L'INSERIMENTO DELLO STATO DEL SINISTRO QUA

	<form name="stato_sinistro" action="ManagementServlet" method="post">
		<table>

			<tr>
				<td>stato:</td>
				<td>
				<select name="stato">
						<option value="aperto" selected="selected">APERTO</option>
						<option value="in_elaborazione">IN ELABORAZIONE</option>
						<option value="in_corso">IN CORSO</option>
						<option value="richiesta_documenti">RICHIESTA DOCUMENTI</option>
						<option value="chiusa_respinta">CHIUSA / RESPINTA</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>descrizione:</td>
				<td><input name="idPolizza" value="" type="text" ></td>
			</tr>
			<tr>
				<td>documenti:</td>
				<td><input name="doc" value="" type="text"></td>
			</tr>
			
		</table>
		<br> <input name="whatsend" value="sinistro" type="hidden">
		<input type="submit" value="invia">
	</form>


</body>
</html>