<%@page import="com.dao.EmployeeDao"%>
<%@page import="com.bean.Employee"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of employee</title>
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
<b style="color:blue;" >
		<%
		if (request.getAttribute("message") != null) {
			out.println(request.getAttribute("message"));
		}
		%>
</b>
	<form method="post" action="EmployeeController">
		<table width=100% border="1" cellpadding="10px" cellspacing="10px">
			<tr>
				<td>ID</td>
				<td>First Name</td>
				<td>Last name</td>
				<td>Mobile</td>
				<td>Email</td>
				<td>Username</td>
				<td>Password</td>
				<td>Employee Id</td>
				<td>Edit</td>
				<td>Delete</td>
			</tr>
			<%
			List<Employee> employeeList = EmployeeDao.getListOfEmployee();
			if (!employeeList.isEmpty()) {
				for (Employee em : employeeList) {
			%>
			<tr>
				<td><%=em.getEmpInfo().getId()%></td>
				<td><%=em.getEmpInfo().getFirstname()%></td>
				<td><%=em.getEmpInfo().getLastname()%></td>
				<td><%=em.getEmpInfo().getMobile()%></td>
				<td><%=em.getEmpInfo().getEmail()%></td>
				<td><%=em.getUsername()%></td>
				<td><%=em.getPassword()%></td>
				<td><%=em.getEmpId()%></td>
				<td>
					<form name="edit" method="post" action="EmployeeController">
						<input type="hidden" name="empID" value="<%=em.getEmpId()%>"> <input
							type="submit" name="action" value="Edit" class="btn btn-primary" />
					</form>
				</td>
				<td>
					<form name="delete" method="post" action="EmployeeController">
						<input type="hidden" name="empID" value="<%=em.getEmpId()%>"> <input
							type="submit" name="action" value="Delete"
							class="btn btn-danger" />
					</form>
				</td>
			</tr>
			<%
			}
			}
			%>
		</table>
	</form>
	<a href="registration.jsp">Add Employee</a>
</body>
</html>