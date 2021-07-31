package com.employeeCrudapp.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employeeCrudapp.entity.Branch;
import com.employeeCrudapp.entity.Employee;

@Repository
public class BranchDAOHibernateImpl implements BranchDAO {
	
	 // define field for entitymanager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public BranchDAOHibernateImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    
    @Override
    public Branch findBranchById(int theId) {
        
        Session currentSession = entityManager.unwrap(Session.class);
		
		// get the BRANCH
        Branch branch = currentSession.get(Branch.class, theId);
		
		
		// return the BRANCH
		return branch;
    }
    
}
