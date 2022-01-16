package com.baeldung.springbootreact.controller;


import com.baeldung.springbootreact.domain.Task;
import com.baeldung.springbootreact.repository.ClientRepository;
import com.baeldung.springbootreact.repository.TaskRepository;
import com.baeldung.springbootreact.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class TaskController {

    @Autowired
    public ClientRepository clientRepository;

    @Autowired
    public TaskService taskService;

    @Autowired
    public TaskRepository taskRepository;

    @GetMapping("/{id}/tasks")
    public List<Task> retrieveAlltasks(@PathVariable long id) {
        return taskService.gettasks(id);

    }



    @PostMapping("/{id}/tasks")
    public ResponseEntity<Object> createTask(@PathVariable long id, @RequestBody Task task) {



        return taskService.createtasks(id,task);

    }

    @DeleteMapping("/{id1}/tasks/{id2}")
    public ResponseEntity deleteClienttask(@PathVariable("id1") Long id1, @PathVariable("id2") int id2) {


        taskService.deletetask(id1,id2);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id1}/tasks/{id2}")
    public ResponseEntity updateClienttasks(@PathVariable("id1") Long id1,@PathVariable("id2") Long id2, @RequestBody Task task) {


        Task task1=taskService.updatetask(id1,id2,task);
        return ResponseEntity.ok(task1);
    }



}
