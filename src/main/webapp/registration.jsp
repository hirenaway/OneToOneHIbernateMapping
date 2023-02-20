<%@page import="com.bean.Employee"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add employee</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<style type="text/css">
tr, td {
	padding: 10px !important;
}
</style>
</head>
<body>
	<%
	Employee em = (Employee) request.getAttribute("employee");
	%>
	<b style="color: red;"> <%
 String message = "";
 if (request.getAttribute("message") != null) {
 	out.println(request.getAttribute("message"));
 }
 %>
	</b>
	<form name="action" action="EmployeeController" method="post">
		<table>
			<tr>
				<td>First Name:</td>
				<td>
					<%
					if (em != null) {
					%> 
						<input type="text" name="firstName" value="<%=em.getEmpInfo().getFirstname()%>" required="required">
					<%
					} else {
					%> 
						<input type="text" name="firstName" required="required">
					<%
					}
					%>
				</td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td>
					<%
					if (em != null) {
					%> 
						<input type="text" name="lastName" value="<%=em.getEmpInfo().getLastname()%>" required="required">
					<%
					} else {
					%>
						 <input type="text" name="lastName" required="required">
					<%
					}
					%>
				</td>
			</tr>
			<tr>
				<td>Email:</td>
				<td>
					<%
					if (em != null) {
					%> 
						<input type="email" name="email" value="<%=em.getEmpInfo().getEmail()%>" required="required">
					<%
					} else {
					%> 
						<input type="text" name="email" required="required"> <%
 					}
 					%>
				</td>
			</tr>
			<tr>
				<td>Mobile:</td>
				<td>
					<%
					if (em != null) {
					%> 
						<input type="text" name="mobile" value="<%=em.getEmpInfo().getMobile()%>" required="required">
					<%
					} else {
					%> 
						<input type="text" name="mobile" required="required"> <%
 					}
 					%>
				</td>
			</tr>
			<tr>
				<td>Username:</td>
				<td>
					<%
					if (em != null) {
					%> 
						<input type="text" name="userName" value="<%=em.getUsername()%>" required="required"> <%
 					} else {
 					%> 
 						<input type="text" name="userName" required="required"> <%
 					}
 					%>
				</td>
			</tr>
			<tr>
				<%
				if (em == null) {
				%>
					<td> Password: </td>
					<td>
						<input type="password" name="password" required="required">
					</td>
				<%
				} else {
				%>
					<td>
						<input type="hidden" name="password" value="<%=em.getPassword()%>" required="required">
					</td>
				<%
				}
				%>
			</tr>
			<%
			if (em != null) {
			%>
			<tr>
				<td>
					<input type="hidden" name="empID" value="<%=em.getEmpId()%>">
				</td>
			</tr>
			<%
			}
			%>
			<tr>
				<td colspan="2" align="center">
					<%
					if (em != null) {
					%> 
						<input type="submit" name="action" value="Update" class="btn btn-primary"> 
					<%
 					} else {
 					%> 
 						<input type="submit" name="action" value="Register" class="btn btn-primary"> 
 					<%
 					}
					 %>
				</td>
			</tr>
		</table>
	</form>
	<a href="display.jsp">Show all Employees</a>
</body>
</html>