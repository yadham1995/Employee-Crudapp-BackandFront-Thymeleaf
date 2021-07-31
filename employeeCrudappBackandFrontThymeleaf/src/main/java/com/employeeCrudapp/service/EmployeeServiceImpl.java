package com.employeeCrudapp.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.employeeCrudapp.Exception.InvalidValueException;
import com.employeeCrudapp.dao.EmployeeDAO;
import com.employeeCrudapp.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO) {
        employeeDAO = theEmployeeDAO;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    @Transactional
    public Employee findById(int theId) {
        return employeeDAO.findById(theId);
    }

    @Override
    @Transactional
    public void save(Employee theEmployee) {
        employeeDAO.save(theEmployee);
    }


    @Override
    @Transactional
    public void update(Employee theEmployee) {
        employeeDAO.update(theEmployee);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        employeeDAO.deleteById(theId);
    }

    @Override
    public void validateAgeFromNationalId(String nationalId, String age) {
    	
    	char Century = nationalId.charAt(0);
    	String birthDate ="";
    	
    	if(Century == '2') {
         birthDate = nationalId.substring(1, 7);
        birthDate = "19" + birthDate.substring(0, 2) + "/" + birthDate.substring(2, 4) + "/" + birthDate.substring(4, 6);
    	}else
    	 if(Century == '3') {
    		  birthDate = nationalId.substring(1, 7);
    	     birthDate = "20" + birthDate.substring(0, 2) + "/" + birthDate.substring(2, 4) + "/" + birthDate.substring(4, 6);
    	 }	
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate bDate = LocalDate.parse(birthDate, formatter);
        LocalDate today = LocalDate.now();

        int diff = today.getYear() - bDate.getYear();

        if (diff != Integer.parseInt(age)) {
        	
            throw new InvalidValueException("Age Validation Error : The Age " + age +" you Entered Doesn't Match The National ID");

        }

    }
    
    
	


}






