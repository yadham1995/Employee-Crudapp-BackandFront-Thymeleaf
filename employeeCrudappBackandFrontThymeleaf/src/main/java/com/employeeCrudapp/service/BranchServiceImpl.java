package com.employeeCrudapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employeeCrudapp.dao.BranchDAO;
import com.employeeCrudapp.dao.EmployeeDAO;
import com.employeeCrudapp.entity.Branch;
import com.employeeCrudapp.entity.Employee;

@Service
public class BranchServiceImpl implements BranchService {

	private BranchDAO branchDAO;

    @Autowired
    public BranchServiceImpl(BranchDAO theBranchDAO) {
    	branchDAO = theBranchDAO;
    }
	
	
    @Override
    @Transactional
    public Branch findBranchById(int theId) {
        return branchDAO.findBranchById(theId);
    }
   

}
