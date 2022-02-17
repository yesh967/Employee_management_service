package com.baeldung.springbootreact.controller;


import com.baeldung.springbootreact.domain.Employee;
import com.baeldung.springbootreact.domain.Task;
import com.baeldung.springbootreact.repository.EmployeeRepository;
import com.baeldung.springbootreact.repository.TaskRepository;
import com.baeldung.springbootreact.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

//controller class for Endpoints of crud operations on task entity

@RestController
@RequestMapping("/employees")
public class TaskController {

    static Logger logger = Logger.getLogger(TaskController.class.getName());
    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public TaskService taskService;

    @Autowired
    public TaskRepository taskRepository;

        @GetMapping("/{id}/tasks")
        public List<Task> retrieveAlltasks(@PathVariable long id)
            {
                logger.info(" In retrieveAlltasks controller function ");
                try {
                    return taskService.gettasks(id);
                }
                catch(Exception e)
                {
                    logger.info("Exception in fetching tasks, tasks absent"+ e.getMessage());
                }
                return null;

            }

        @GetMapping("/{id1}/tasks/{id2}")
        public Optional<Task> getemployeetask(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2)
            {
                logger.info(" In getEmployeetask function ");
                try {
                    return taskService.getonetask(id1,id2);

                }
                catch(Exception e)
                {
                    logger.info("Exception in fetching tasks, tasks absent"+ e.getMessage());
                }
                return null;

            }

    @PostMapping("/{id}/tasks")
    public ResponseEntity<Task> createTask(@Valid @PathVariable long id, @RequestBody Task task)
        {

            logger.info(" In createTask function ");
            try {
                return taskService.createtasks(id,task);
            }
            catch(Exception e)
            {
                logger.info("Exception in fetching tasks, tasks absent"+ e.getMessage());
            }
            return null;

        }

    @DeleteMapping("/{id1}/tasks/{id2}")
    public ResponseEntity deleteEmployeetask(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2) throws NullPointerException, IOException {
        logger.info(" In deleteEmployeetask function ");
        try {
            taskService.deletetask(id1,id2);
            return ResponseEntity.ok().build();
        }
        catch(Exception e)
        {
            logger.info("Exception in fetching tasks, tasks absent"+ e.getMessage());
        }
        return null;
        }

    @PutMapping("/{id1}/tasks/{id2}")
    public ResponseEntity<Task> updateEmployeetasks(@PathVariable("id1") Long id1,@PathVariable("id2") Long id2, @RequestBody Task task) {
        logger.info(" In updateEmployeetasks function ");
        try {
            return ResponseEntity.ok(taskService.updatetask(id1,id2,task));
        }
        catch(Exception e)
        {
            logger.info("Exception in fetching tasks, tasks absent"+ e.getMessage());
        }
        return null;


    }

    @PatchMapping("/{id1}/tasks/{id2}")
    public ResponseEntity<Task> updateEmployeetasksfield(@PathVariable("id1") Long id1,@PathVariable("id2") Long id2, @RequestBody Task task) {
        logger.info(" In updateEmployeetasksfield function ");
        try {

            return ResponseEntity.ok(taskService.updatetaskfield(id1,id2,task));

        }
        catch(Exception e)
        {
            logger.info("Exception in fetching tasks, tasks absent"+ e.getMessage());
        }
        return null;

    }


}
