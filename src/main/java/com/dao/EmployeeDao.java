package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bean.Employee;
import com.bean.EmployeeDetails;
import com.util.EmployeeUtil;

public class EmployeeDao {

	public static void saveOrUpdateEmployeeInfo(EmployeeDetails details) {
		SessionFactory sessionFactory = EmployeeUtil.getSession();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(details);
		session.getTransaction().commit();
		session.close();
	}

	public static void saveOrUpdateEmployee(Employee employee) {
		SessionFactory sessionFactory = EmployeeUtil.getSession();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(employee);
		session.getTransaction().commit();
		session.close();
	}

	public static EmployeeDetails checkEmail(String email) {
		SessionFactory sessionFactory = EmployeeUtil.getSession();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		EmployeeDetails details = (EmployeeDetails) session.createQuery("FROM EmployeeDetails ED WHERE ED.email = :e").setParameter("e", email).uniqueResult();
		session.close();
		return details;
	}

	public static Employee checkUsername(String username) {
		SessionFactory sessionFactory = EmployeeUtil.getSession();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Employee employee = (Employee) session.createQuery("FROM Employee E WHERE E.username = :user").setParameter("user", username).uniqueResult();
		session.close();
		return employee;
	}

	public static List<Employee> getListOfEmployee() {
		SessionFactory sessionFactory = EmployeeUtil.getSession();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Employee> list = session.createQuery("FROM Employee").list();
		session.close();
		return list;
	}

	public static Employee getEmployee(int id) {
		SessionFactory sessionFactory = EmployeeUtil.getSession();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Employee selectedEmp = (Employee) session.createQuery("FROM Employee E WHERE E.empId = :eid").setParameter("eid", id).uniqueResult();
		session.close();
		return selectedEmp;
	}

	public static String deleteEmployee(int id) {
		SessionFactory sessionFactory = EmployeeUtil.getSession();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Employee selectedEmp = session.get(Employee.class, id);
		session.delete(selectedEmp);
		session.getTransaction().commit();
		session.close();
		return selectedEmp.getEmpInfo().getFirstname() +" "+ selectedEmp.getEmpInfo().getLastname();
	}

}
