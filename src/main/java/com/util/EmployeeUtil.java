package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bean.Employee;
import com.bean.EmployeeDetails;

public class EmployeeUtil {

	public static SessionFactory sf = null;
	
	public static SessionFactory getSession() {
		if(sf == null) {
			sf =  new Configuration()
					.addAnnotatedClass(EmployeeDetails.class)
					.addAnnotatedClass(Employee.class)
					.configure()
					.buildSessionFactory();
			return sf;
		}
		return sf;
	}

}
