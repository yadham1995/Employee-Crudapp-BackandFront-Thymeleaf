package com.employeeCrudapp.dao;

import java.util.List;

import com.employeeCrudapp.entity.Branch;
import com.employeeCrudapp.entity.Employee;

public interface EmployeeDAO {

	public List<Employee> findAll();
	
	public Employee findById(int theId);

	public Branch findBranchById(int theId) ;

	public void save(Employee theEmployee);

	public void update(Employee theEmployee);
	
	public void deleteById(int theId);
	
}
