package com.baeldung.springbootreact.controller;


import com.baeldung.springbootreact.domain.Task;
import com.baeldung.springbootreact.repository.EmployeeRepository;
import com.baeldung.springbootreact.repository.TaskRepository;
import com.baeldung.springbootreact.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//controller class for Endpoints of crud operations on task entity

@RestController
@RequestMapping("/employees")
public class TaskController {

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public TaskService taskService;

    @Autowired
    public TaskRepository taskRepository;

        @GetMapping("/{id}/tasks")
        public List<Task> retrieveAlltasks(@PathVariable long id)
            {

                return taskService.gettasks(id);

            }

        @GetMapping("/{id1}/tasks/{id2}")
        public Optional<Task> getemployeetask(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2)
            {
                return taskService.getonetask(id1,id2);
            }

    @PostMapping("/{id}/tasks")
    public ResponseEntity<Object> createTask(@Valid @PathVariable long id, @RequestBody Task task)
        {
            return taskService.createtasks(id,task);
        }

    @DeleteMapping("/{id1}/tasks/{id2}")
    public ResponseEntity deleteEmployeetask(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2) throws SQLException {
            taskService.deletetask(id1,id2);
            return ResponseEntity.ok().build();
        }

    @PutMapping("/{id1}/tasks/{id2}")
    public ResponseEntity updateEmployeetasks(@PathVariable("id1") Long id1,@PathVariable("id2") Long id2, @RequestBody Task task) {


        Task task1=taskService.updatetask(id1,id2,task);
        return ResponseEntity.ok(task1);
    }

//    @PatchMapping("/{id1}/tasks/{id2}")
//    public ResponseEntity updateEmployeetasksfield(@PathVariable("id1") Long id1,@PathVariable("id2") Long id2, @RequestBody Task task) {
//
//
//        Task task1=taskService.updatetaskfield(id1,id2,task);
//        return ResponseEntity.ok(task1);
//    }


}
