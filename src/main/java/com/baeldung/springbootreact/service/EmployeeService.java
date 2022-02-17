
package com.baeldung.springbootreact.service;

import com.baeldung.springbootreact.domain.Employee;
import com.baeldung.springbootreact.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class EmployeeService {

    static Logger logger = Logger.getLogger(EmployeeService.class.getName());

    @Autowired
    public EmployeeRepository employeeRepository;


    // retrieves list of all employees and its related contents
    public List<Employee> getAllEmployees()
    {
        logger.info(" In getAllEmployee function ");
        try {
            List<Employee> employees = new ArrayList<Employee>();
            employees.addAll(employeeRepository.findAll());
            return employees;
        }
        catch (Exception e){
            logger.info("error while fetching Employees"+ e.getMessage());
        }
          return null;
    }


    // retrieves employee only if it exists
    public Optional<Employee> getoneEmployee(Long id)
    {
        logger.info(" In getoneEmployee function ");
        Optional<Employee> employeeOptional= Optional.of(new Employee());
try {
    employeeOptional = employeeRepository.findById(id);
}
catch (Exception e){
     logger.info("error while fetching Emplouee"+ e.getMessage());
}
        return employeeOptional;
    }

    //employee creation and saving it to database
    public Employee createoneEmployee(Employee employee) throws Exception
    {
        logger.info(" In createoneEmployee function ");
        Employee savedEmployee=new Employee();
        try{
            savedEmployee = employeeRepository.save(employee);
        }
        catch (Exception e){
        logger.info("Error saving Employee"+ e.getMessage());
        }
        return savedEmployee;
    }

    //updating employee data in database
    public Employee updatecurrentEmployee(@PathVariable Long id, @RequestBody Employee employee)
    {
        logger.info(" In UpdateCurrentEmployee function ");
        Employee currentEmployee=new Employee();
try {
    currentEmployee = employeeRepository.findById(id).orElseThrow(RuntimeException::new);
    currentEmployee.setName(employee.getName());
    currentEmployee.setEmail(employee.getEmail());
    employeeRepository.save(currentEmployee);
}catch (Exception e){
    logger.info("Exception while updating "+ e.getMessage());
}
        return currentEmployee;
    }
    //removing an employee and its contents from database
    public void deleteoneEmployee(Long id)
    {

            employeeRepository.deleteById(id);

    }

}

