
    package com.baeldung.springbootreact.service;

import com.baeldung.springbootreact.domain.Employee;
import com.baeldung.springbootreact.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

    @Service
    public class EmployeeService {

        @Autowired
        public EmployeeRepository employeeRepository;


        public List<Employee> getAllEmployees() {
            return employeeRepository.findAll();
        }


        public Optional<Employee> getoneEmployee(Long id) {
            return employeeRepository.findById(id);
        }


        public Employee createoneEmployee(Employee employee){
            Employee savedEmployee = employeeRepository.save(employee);

            return savedEmployee;
        }

        public Employee updatecurrentEmployee(@PathVariable Long id, @RequestBody Employee employee) {

            Employee currentEmployee = employeeRepository.findById(id).orElseThrow(RuntimeException::new);
            currentEmployee.setName(employee.getName());
            currentEmployee.setContact_details(employee.getContact_details());
            employeeRepository.save(currentEmployee);
            return currentEmployee;
        }

        public void deleteoneEmployee(Long id) {
            employeeRepository.deleteById(id);

        }

    }

