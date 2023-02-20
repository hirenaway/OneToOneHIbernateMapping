package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Employee;
import com.bean.EmployeeDetails;
import com.dao.EmployeeDao;

public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	EmployeeDetails details = null;
	Employee employee = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("Register")) {
			details = EmployeeDao.checkEmail(request.getParameter("email"));
			employee = EmployeeDao.checkUsername(request.getParameter("userName"));
			saveUpdateEmployee(action, details, employee, request, response);
		} else if (action.equalsIgnoreCase("Update")) {
			if (!employee.getEmpInfo().getEmail().equals(request.getParameter("email")) && isEmailExist(request.getParameter("email"))) {
				request.setAttribute("message", "Email is already registered");
				request.getRequestDispatcher("registration.jsp").forward(request, response);
			} else if(!employee.getUsername().equals(request.getParameter("userName")) && isUsernameExist(request.getParameter("userName"))) {
				request.setAttribute("message", "Username " + employee.getUsername() + " is taken, please try a different one");
				request.getRequestDispatcher("registration.jsp").forward(request, response);
			} else {
				details = null;
				employee = null;
				saveUpdateEmployee(action, details, employee, request, response);
			}
		} else if (action.equalsIgnoreCase("Edit")) {
			employee = EmployeeDao.getEmployee(Integer.parseInt(request.getParameter("empID")));
			request.setAttribute("employee", employee);
			request.getRequestDispatcher("registration.jsp").forward(request, response);
		} else if (action.equalsIgnoreCase("Delete")) {
			String name = EmployeeDao.deleteEmployee(Integer.parseInt(request.getParameter("empID")));
			request.setAttribute("message","User: "+ name +" Deleted Sucessfully");
			request.getRequestDispatcher("display.jsp").forward(request, response);
		}
	}

	protected void saveUpdateEmployee(String action, EmployeeDetails empDetails, Employee emp,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (empDetails != null) {
			request.setAttribute("message", "Email is already registered");
			request.getRequestDispatcher("registration.jsp").forward(request, response);
		} else if (emp != null) {
			request.setAttribute("message", "Username " + emp.getUsername() + " is taken, please try a different one");
			request.getRequestDispatcher("registration.jsp").forward(request, response);
		} else {
			String editOrUpdate = action.equalsIgnoreCase("Register") ? "Registered" : "Updated";
			empDetails = new EmployeeDetails();
			emp = new Employee();
			if (action.equalsIgnoreCase("Update")) {
				emp.setEmpId(Integer.parseInt(request.getParameter("empID")));
				empDetails.setId(Integer.parseInt(request.getParameter("empID")));
			} 
			empDetails.setFirstname(request.getParameter("firstName"));
			empDetails.setLastname(request.getParameter("lastName"));
			empDetails.setEmail(request.getParameter("email"));
			empDetails.setMobile(Long.parseLong(request.getParameter("mobile")));
			EmployeeDao.saveOrUpdateEmployeeInfo(empDetails);
			emp.setUsername(request.getParameter("userName"));
			emp.setPassword(request.getParameter("password"));
			emp.setEmpInfo(empDetails);
			EmployeeDao.saveOrUpdateEmployee(emp);
			request.setAttribute("message", "Employee "+ editOrUpdate +" Successfully");
			request.getRequestDispatcher("display.jsp").forward(request, response);
		}
	}

	public boolean isEmailExist(String email) {
		details = EmployeeDao.checkEmail(email);
		return details != null;
	}

	public boolean isUsernameExist(String username) {
		employee = EmployeeDao.checkUsername(username);
		return employee != null;
	}

}
