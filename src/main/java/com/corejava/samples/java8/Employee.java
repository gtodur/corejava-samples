package com.corejava.samples.java8;

import java.io.Serializable;

public class Employee implements Serializable, Comparable<Employee>{
	
	private static final long serialVersionUID = -1872557248685468294L;
	
	private String firstName;
	private String lastName;
	private String designation;
	private Float salary;
	public Employee(String firstName, String lastName, String designation, Float salary) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.designation = designation;
		this.salary = salary;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", designation=" + designation
				+ ", salary=" + salary + "]";
	}
	@Override
	public int compareTo(Employee o) {
		return (this.salary < o.getSalary()) ? -1 : (this.salary > o.getSalary()) ? 1 : 0;
	}

}
