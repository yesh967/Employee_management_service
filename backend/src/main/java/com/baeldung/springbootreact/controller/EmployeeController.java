package com.baeldung.springbootreact.controller;

import com.baeldung.springbootreact.domain.Employee;
import com.baeldung.springbootreact.repository.EmployeeRepository;
import com.baeldung.springbootreact.repository.TaskRepository;
import com.baeldung.springbootreact.repository.TeamRepository;
import com.baeldung.springbootreact.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class EmployeeController {



        @Autowired
        public EmployeeService employeeService;

        @Autowired
        public EmployeeRepository employeeRepository;

        @Autowired
        public TaskRepository taskRepository;

        @Autowired
        public TeamRepository teamRepository;


        @GetMapping("/employees")
        public List<Employee> getEmployees() {
            return employeeService.getAllEmployees();
        }

        @GetMapping("/employees/{id}")
        public Optional<Employee> getEmployee(@PathVariable Long id) {
            return employeeService.getoneEmployee(id);
        }

        @PostMapping("/employees")
        public ResponseEntity createEmployee(@RequestBody Employee employee) throws URISyntaxException {
            Employee savedEmployee = employeeService.createoneEmployee(employee);
            return ResponseEntity.created(new URI("/employees/" + savedEmployee.getId())).body(savedEmployee);
        }
        //
        @PutMapping("/employees/{id}")
        public ResponseEntity updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
            Employee currentEmployee = employeeService.updatecurrentEmployee(id,employee);


            return ResponseEntity.ok(currentEmployee);
        }
        //
        @DeleteMapping("/employees/{id}")
        public ResponseEntity deleteEmployee(@PathVariable Long id) {
            employeeService.deleteoneEmployee(id);
            return ResponseEntity.ok().build();
        }

    }

