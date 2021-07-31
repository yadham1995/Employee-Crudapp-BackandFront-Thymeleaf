package com.employeeCrudapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.employeeCrudapp.entity.Branch;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employeeCrudapp.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    // define field for entitymanager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }


    @Override
    public List<Employee> findAll() {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

//		entityManager.getTransaction().begin();

        // create a query
        Query<Employee> theQuery =
                currentSession.createQuery("from Employee", Employee.class);

        // execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        // return the results
        return employees;
    }


    @Override
    public Employee findById(int theId) {
    	 
Session currentSession = entityManager.unwrap(Session.class);
		
		// get the employee
		Employee theEmployee =
				currentSession.get(Employee.class, theId);
		
		// return the employee
		return theEmployee;
    }

    @Override
    public Branch findBranchById(int theId) {
        Branch branch = entityManager.find(Branch.class, new Integer(theId));
        entityManager.detach(branch);
        return branch;
    }


    @Override
    public void save(Employee theEmployee) {
        entityManager.persist(theEmployee);
    }

    @Override
    public void update(Employee theEmployee) {
        entityManager.merge(theEmployee);
    }


    @Override
    public void deleteById(int theId) {

        Employee employee = entityManager.find(Employee.class, new Integer(theId));
        entityManager.remove(employee);

    }

}







