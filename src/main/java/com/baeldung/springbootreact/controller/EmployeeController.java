package com.baeldung.springbootreact.controller;

import com.baeldung.springbootreact.domain.Employee;
import com.baeldung.springbootreact.repository.EmployeeRepository;
import com.baeldung.springbootreact.repository.TaskRepository;
import com.baeldung.springbootreact.repository.TeamRepository;
import com.baeldung.springbootreact.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

//controller class for Endpoints of crud operations on Employee entity

@RestController
@RequestMapping
public class EmployeeController {

    static Logger logger = Logger.getLogger(EmployeeController.class.getName());

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
            logger.info(" In getEmployees controller function ");
            try {
                return employeeService.getAllEmployees();
            }
            catch(Exception e)
            {
                logger.info("Exception in fetching tasks, tasks absent"+ e.getMessage());
            }
            return null;

        }

        @GetMapping("/employees/{id}")
        public Optional<Employee> getEmployee(@PathVariable Long id) {
            logger.info(" In getEmployee controller function ");
            try {
                return employeeService.getoneEmployee(id);
            }
            catch(Exception e)
            {
                logger.info("Exception in fetching tasks, tasks absent"+ e.getMessage());
            }
            return null;

        }

        @PostMapping("/employees")
        public ResponseEntity createEmployee(@Valid @RequestBody Employee employee) throws URISyntaxException {

            try {
                Employee savedEmployee = employeeService.createoneEmployee(employee);
                return ResponseEntity.created(new URI("/employees/" + savedEmployee.getId())).body(savedEmployee);
            }
            catch (Exception e){
                logger.info("error creating data in database"+ e.getMessage());
            }
            return  null;
        }
        //
        @PutMapping("/employees/{id}")
        public ResponseEntity updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
            logger.info(" In updateEmployee controller function ");
            try {
                return ResponseEntity.ok(employeeService.updatecurrentEmployee(id,employee));
            }
            catch(Exception e)
            {
                logger.info("Exception in fetching tasks, tasks absent"+ e.getMessage());
            }
            return null;

        }
        //
        @DeleteMapping("/employees/{id}")
        public ResponseEntity deleteEmployee(@PathVariable Long id) {
            logger.info(" In deleteEmployee controller function ");
            try {
                employeeService.deleteoneEmployee(id);
                return ResponseEntity.ok().build();
            }
            catch(Exception e)
            {
                logger.info("Exception in fetching tasks, tasks absent"+ e.getMessage());
            }
            return null;

        }

    }

