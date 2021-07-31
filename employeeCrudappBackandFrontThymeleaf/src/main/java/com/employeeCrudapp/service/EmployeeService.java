package com.employeeCrudapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employeeCrudapp.entity.Employee;

@Service
public interface EmployeeService {

	public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee);

	public void update(Employee theEmployee);

	public void deleteById(int theId);

	public void validateAgeFromNationalId(String nationalId, String age);
	
}
