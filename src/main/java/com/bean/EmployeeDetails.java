package com.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "employee_details")
public class EmployeeDetails {
	
	@Id
	@GenericGenerator(name = "inc", strategy = "increment")
	@GeneratedValue(generator = "inc")
	private int id;
	private String firstname;
	private String lastname;
	private String email;
	private long mobile;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "empInfo")
	//One to one annotation establish one to one relation between two entities
	//Fetch attribute lets you decide whether you want to load the class when defined class is loaded or you want to load it when required
	//cascade attribute lets you decide if you want to make change in associated table when any changes occur in the parent table
	//mappedby attribute how your class is connected to in defined class, you have to create a variable with the same name in the child class that is specified in the attribute
	private Employee emp;
	
	

}
