package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Employee;	
	
	//interface vitra abstract method banauni jun chai body hudaina.
	
public interface EmployeeService {
	Employee saveEmployee(Employee employee);
	List<Employee> getAllEmployees();
	Employee getEmployeeById(long id);
	Employee updateEmployee(Employee employee, long id);
	void deleteEmployee(long id);
}
