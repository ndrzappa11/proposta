<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="API.API_2" import="API.API_7" import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista Sinistri</title>
</head>
<body>
	<a href="ManagementServlet?whatsend=home" target="_center"> <input
		type="button" value="HOME"></a>&nbsp;

	<%
	API_2 utente = (API_2) request.getSession().getAttribute("testLogin");
	%>

	<h2>
		LISTA SINISTRI DI &nbsp;<%=utente.getNome().toUpperCase() %>&nbsp;&nbsp;
	</h2>
	
	
	<%
	API_7 prova = new API_7();
	Connection conn=null;
	int j = utente.getIdU(utente);
	int k = prova.numSinistri(j, conn);
	System.out.println("test"+k);
	%>
	<%=k%>
	<%
	API_7[] api = new API_7[k];
	%>
	<%
	api = prova.getSinistri(j);
	%>
	<br>
	<%
	for (int i = 0; i < k; i++) {
	%>
	<br>
	<form name="form assistenza" action="ManagementServlet" method="post">
		<table>
			<tr>
				<td>sinistro numero</td>
				<td><%=i + 1 %></td>
			</tr>
			<tr>
				<td>id sinistro</td>
				<td><%=api[i].getId()%></td>
			</tr>
			<tr>
				<td>id polizza</td>
				<td><%=api[i].getId_polizza()%></td>
			</tr>
			<tr>
				<td>data </td>
				<td><%=api[i].getData()%></td>
			</tr>
			<tr>
				<td>user</td>
				<td><%=api[i].getId_user()%></td>
			</tr>
			
			
				<!-- <td><a href="ManagementServlet?whatsend=assistenza"
					target="_center" type="button"> ASSISTENZA</a>&nbsp; <input
					name="whatsend" value="assistenza" type="hidden"> <input
					name="api" value="<%=api[i]%>" type="hidden"> <input
					type="submit" value="ASSISTENZA"></td> -->
			<tr>_____________________________________________________________________ <br><br></tr>
		</table>
		
	</form>
	<br>
	<br>
	<%
	}
	%>
	


</body>
</html>