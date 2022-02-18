package com.baeldung.springbootreact.service;

import com.baeldung.springbootreact.domain.Employee;
import com.baeldung.springbootreact.domain.Task;
import com.baeldung.springbootreact.repository.EmployeeRepository;
import com.baeldung.springbootreact.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TaskService {

    static Logger logger = Logger.getLogger(TaskService.class.getName());
    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public TaskRepository taskRepository;

    // retrieves list of all tasks
    public List<Task> gettasks(long id) {
        logger.info(" In getAllEmployee function ");
        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(id);

            if (!employeeOptional.isPresent()) {
                throw new NullPointerException("employee absent");
            } else {
                return employeeOptional.get().getTask();
            }

        } catch (Exception e) {
            logger.info("Exception in fetching tasks, tasks absent" + e.getMessage());
            throw new RuntimeException();
        }

    }


    //updating task data in database
    public Task updatetask(Long id1, Long id2, Task task) {
        logger.info(" In getAllEmployee function ");

        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(id1);
            if (employeeOptional.isPresent()) {
                Employee employeecurrent = employeeOptional.get();
                Optional<Task> currenttaskOp = taskRepository.findById(id2);
                if (currenttaskOp.isPresent()) {
                    Task currenttask = currenttaskOp.get();

                    currenttask.setTaskName(task.getTaskName());
                    currenttask.setTaskPriority(task.getTaskPriority());
                    currenttask.setTaskComplexity(task.getTaskComplexity());
                    currenttask.setDeadline(task.getDeadline());
                    currenttask.setCompleted(task.getCompleted());

                    currenttask.setEmployee(employeecurrent);
                    return taskRepository.save(currenttask);
                }
            } else {
                throw new IOException(" cannot update Exception thrown");
            }
        } catch (Exception e) {
            logger.info("Exception in fetching task, task absent" + e.getMessage());
            throw new RuntimeException();

        }
        return null;
    }


    //removing a task  from database
    public void deletetask(Long id1, Long id2) throws NullPointerException, IOException {
        logger.info(" In getAllEmployee function ");

        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(id1);
            if (employeeOptional.isPresent()) {
                Optional<Task> taskOptional = taskRepository.findById(id2);
                if (taskOptional.isPresent()) {
                    taskRepository.deleteById(id2);
                } else {
                    throw new NullPointerException();
                }
            } else {
                throw new NullPointerException();
            }
        } catch (Exception e) {
            logger.info("Exception in fetching task, task absent" + e.getMessage());
        }
    }


    //creating tasks data in database
    public ResponseEntity<Task> createtasks(long id, Task task) {
        logger.info(" In getAllEmployee function ");

        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(id);

            if (!employeeOptional.isPresent()) {
                throw new NullPointerException("employee absent");
            }
            Employee employeecurrent = employeeOptional.get();
            task.setEmployee(employeecurrent);
            taskRepository.save(task);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(task.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            logger.info("Exception while creation of task" + e.getMessage());
            throw new RuntimeException();
        }

    }


    //retrieving single task data from database
    public Optional<Task> getonetask(Long id1, Long id2) {
        logger.info(" In getAllEmployee function ");

        try {
            Optional<Employee> employeeOptional = Optional.of(new Employee());

            employeeOptional = employeeRepository.findById(id1);

            // System.out.println(employeeOptional);
            if (!employeeOptional.isPresent()) {
                throw new NullPointerException("employee absent");
            } else {
                Optional<Task> taskfound = Optional.of(new Task());

                taskfound = taskRepository.findById(id2);
                if (!taskfound.isPresent()) {
                    throw new NullPointerException("employee absent");
                } else
                    return taskfound;
            }
        } catch (NullPointerException e) {
            logger.info("Task Not present" + e.getMessage());
            throw new RuntimeException();
        }

    }


    public Task updatetaskfield(Long id1, Long id2, Task task) {

        logger.info(" In getAllEmployee function ");
        try {
            Optional<Employee> employeeOptional = Optional.of(new Employee());
            employeeOptional = employeeRepository.findById(id1);

            if (!employeeOptional.isPresent()) {
                throw new NullPointerException("employee absent");
            } else {
                Optional<Task> taskOptional = Optional.of(new Task());
                taskOptional = getonetask(id1, id2);


                if (!taskOptional.isPresent()) {
                    throw new NullPointerException("employee absent");
                } else {
                    Task curtask = taskOptional.get();
                    curtask.setCompleted(task.getCompleted());
                    return taskRepository.save(curtask);
                }
            }
        } catch (Exception e) {
            logger.info("Exception thrown while Updating" + e.getMessage());
            throw new RuntimeException();
        }

    }

}
