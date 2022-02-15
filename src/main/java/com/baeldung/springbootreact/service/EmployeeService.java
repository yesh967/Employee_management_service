
package com.baeldung.springbootreact.service;

import com.baeldung.springbootreact.domain.Employee;
import com.baeldung.springbootreact.domain.Task;
import com.baeldung.springbootreact.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
        return employeeRepository.findAll();
    }

    // retrieves employee only if it exists
    public Optional<Employee> getoneEmployee(Long id)
    {
        return employeeRepository.findById(id);
    }

    //employee creation and saving it to database
    public Employee createoneEmployee(Employee employee)
    {
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

    //updating employee data in database
    public Employee updatecurrentEmployee(@PathVariable Long id, @RequestBody Employee employee)
    {

        Employee currentEmployee = employeeRepository.findById(id).orElseThrow(RuntimeException::new);
        currentEmployee.setName(employee.getName());
        currentEmployee.setContact_details(employee.getContact_details());
        employeeRepository.save(currentEmployee);
        return currentEmployee;
    }
    //removing an employee and its contents from database
    public void deleteoneEmployee(Long id)
    {

            employeeRepository.deleteById(id);

    }

}

