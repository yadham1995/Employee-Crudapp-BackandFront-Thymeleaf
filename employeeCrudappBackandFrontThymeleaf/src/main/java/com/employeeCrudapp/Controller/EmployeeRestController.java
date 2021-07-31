package com.employeeCrudapp.Controller;

import java.util.List;

import javax.management.InvalidAttributeValueException;
import javax.validation.Valid;
import org.springframework.web.bind.*;
import com.employeeCrudapp.Exception.InvalidValueException;
import com.employeeCrudapp.entity.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.employeeCrudapp.entity.Employee;
import com.employeeCrudapp.service.BranchService;
import com.employeeCrudapp.service.EmployeeService;

@Controller
@RequestMapping("/api")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BranchService branchService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }
    
 

    // expose "/employees" and return list of employees
    @GetMapping("/employees")
    public String findAll(Model theModel) {
    	
		List<Employee> theEmployees = employeeService.findAll();
		

		// add to the spring model
				theModel.addAttribute("employees", theEmployees);
				
				return "employees/list-employees";
    }

    // add mapping for GET /employees/{employeeId}

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new InvalidValueException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }
    
    @GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Employee theEmployee = new Employee();
		
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}

    // add mapping for POST /employees - add new employee

    @PostMapping("/employees")
    public String addEmployee(@RequestParam Integer branchId, @Valid @ModelAttribute("employee") Employee theEmployee) {

      // validate age from National ID
    	employeeService.validateAgeFromNationalId(theEmployee.getNationalId(),theEmployee.getAge());

        Branch branch = branchService.findBranchById(branchId);
        
        if (branch == null) {
            throw new InvalidValueException("The Branch with ID: "+branchId+" is not found " );
        }
        
        theEmployee.setBranch(branch);
        employeeService.save(theEmployee);

		return "redirect:/api/employees";
    }

    @GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,
									Model theModel) {
		
		// get the employee from the service
		Employee theEmployee = employeeService.findById(theId);
		
		if (theEmployee == null) {
            throw new InvalidValueException("Employee id not found - " + theEmployee.getId());
        }
		
		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", theEmployee);
		
		// send over to our form
		return "employees/employee-form";			
	}

    // add mapping for DELETE /employees/{employeeId} - delete employee

    @GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int employeeId) {
    	

        Employee tempEmployee = employeeService.findById(employeeId);
        
        

		if (tempEmployee == null) {
            throw new InvalidValueException("Employee id not found - " + employeeId);
        }
        employeeService.deleteById(employeeId);

		return "redirect:/api/employees";
    }
    
    //Exception Handling

    @ExceptionHandler
    public String handleInvalidValueException(InvalidValueException exception) {
    	
		return exception.getMessage();
    	
    	
    }
     
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public String exception(MethodArgumentNotValidException exception) {
    	
    	if(exception.getMessage().contains("^[ء-ي ]")) {
    		return "name must be in arabic !!!";
    	}else 
    	 if(exception.getMessage().contains("size must be between 14 and 14"))
    	{
    		return "NationalId length must be 14 !!!";
    	}
    	
		return exception.getMessage();

    	}
    		
    	
		
    


}










