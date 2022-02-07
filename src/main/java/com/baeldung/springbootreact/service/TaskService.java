package com.baeldung.springbootreact.service;

import com.baeldung.springbootreact.domain.Employee;
import com.baeldung.springbootreact.domain.Task;
import com.baeldung.springbootreact.repository.EmployeeRepository;
import com.baeldung.springbootreact.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public TaskRepository taskRepository;

    // retrieves list of all tasks
    public List<Task> gettasks(long id) {


        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(!employeeOptional.isPresent()) {
            throw new IllegalStateException("employee absent");
        }

        return employeeOptional.get().getTask();

    }
    //updating task data in database
    public Task updatetask(Long id1, Long id2, Task task) {



        Optional<Employee> employeeOptional = employeeRepository.findById(id1);


        if(!employeeOptional.isPresent()) {
            throw new IllegalStateException("employee absent");
        }

        Employee employeecurrent = employeeOptional.get();
        Task currenttask=taskRepository.findById(id2).get();

        currenttask.setTaskName(task.getTaskName());
        currenttask.setTaskPriority(task.getTaskPriority());
        currenttask.setTaskComplexity(task.getTaskComplexity());
        currenttask.setDeadline(task.getDeadline());
        currenttask.setCompleted(task.getCompleted());
        currenttask.setEmployee(employeecurrent);

        return taskRepository.save(currenttask);

    }
    //removing a task  from database
    public void deletetask(Long id1, Long id2)
    {
        taskRepository.deleteById(id2);
    }
    //creating tasks data in database
    public ResponseEntity<Object> createtasks(long id, Task task)
    {


        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(!employeeOptional.isPresent()) {
            throw new IllegalStateException("employee absent");
        }

        Employee employeecurrent = employeeOptional.get();

        task.setEmployee(employeecurrent);

        taskRepository.save(task);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(task.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    //retrieving single task data from database
    public Optional<Task> getonetask(Long id1, Long id2) {

        Optional<Employee> employeeOptional = employeeRepository.findById(id1);



        if(!employeeOptional.isPresent()) {
            throw new IllegalStateException("employee absent");
        }

            return taskRepository.findById(id2);


    }

//    public Task updatetaskfield(Long id1, Long id2, Task task) {
//        Optional<Employee> employeeOptional = employeeRepository.findById(id1);
//
//
//        if(!employeeOptional.isPresent()) {
//            throw new IllegalStateException("employee absent");
//        }
//
//        Employee employeecurrent = employeeOptional.get();
//        Task currenttask=taskRepository.findById(id2).get();
//
//        currenttask.setCompleted(task.getCompleted());
//
//        return taskRepository.save(currenttask);
//
//    }
}
