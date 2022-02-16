package com.baeldung.springbootreact.service;

import com.baeldung.springbootreact.domain.Employee;
import com.baeldung.springbootreact.domain.Task;
import com.baeldung.springbootreact.repository.EmployeeRepository;
import com.baeldung.springbootreact.repository.TaskRepository;
import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.management.Query;
import java.lang.reflect.Field;
import java.net.URI;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
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


        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(!employeeOptional.isPresent()) {
            throw new NullPointerException("employee absent");
        }

        return employeeOptional.get().getTask();

    }


    //updating task data in database
    public Task updatetask(Long id1, Long id2, Task task) {

        Optional<Employee> employeeOptional = Optional.of(new Employee());
        Task currenttask=new Task();
    try {

        employeeOptional = employeeRepository.findById(id1);


        if (employeeOptional.isPresent()) {
            Employee employeecurrent = employeeOptional.get();
            currenttask = taskRepository.findById(id2).get();

            currenttask.setTaskName(task.getTaskName());
            currenttask.setTaskPriority(task.getTaskPriority());
            currenttask.setTaskComplexity(task.getTaskComplexity());
            currenttask.setDeadline(task.getDeadline());
            currenttask.setCompleted(task.getCompleted());

            currenttask.setEmployee(employeecurrent);
        }

    }
    catch(Exception e)
    {
        logger.info("Exception in fetching task, task absent"+ e.getMessage());
    }
        return taskRepository.save(currenttask);
    }



    //removing a task  from database
    public void deletetask(Long id1, Long id2) throws NullPointerException, SQLException
    {


        try{
            Optional<Employee> employeeOptional = employeeRepository.findById(id1);
            if(employeeOptional.isPresent())
            {
                Optional<Task> taskOptional = taskRepository.findById(id2);
                if(taskOptional.isPresent())
                {
                    taskRepository.deleteById(id2);
                }
                else{
                    throw new NullPointerException();
                }
            }
            else{
                throw new NullPointerException();
            }
        }
        catch (Exception e)
        {
            logger.info("Exception in fetching task, task absent"+ e.getMessage());
        }
    }


    //creating tasks data in database
    public ResponseEntity<Task> createtasks(long id, Task task)
    {

        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(!employeeOptional.isPresent()) {
            throw new NullPointerException("employee absent");
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


//    public Task updatetaskfield(Long id1, Long id2, Map<Object,Object> task) {
//
//        Query query = sessionFactory.getCurrentSession().createQuery("update Manager set username = :username, password = :password where id = :id");
//        query.setParameter("username", manager.getUsername());
//        query.setParameter("password", manager.getPassword());
//        query.setParameter("id", manager.getId());
//        query.executeUpdate();
//
//
//
//
////
////        Optional<Employee> employeeOptional = employeeRepository.findById(id1);
////
////
////        if(!employeeOptional.isPresent()) {
////            throw new IllegalStateException("employee absent");
////        }
////
////        Employee employeecurrent = employeeOptional.get();
////
////        Optional<Task> currenttask=taskRepository.findById(id2);
////
////        if(!currenttask.isPresent()) {
////            throw new IllegalStateException("employee absent");
////        }
////else{
////    task.forEach((key,value)->{
////
////        Field params= ReflectionUtils.findField(Task.class,(String) key);
////        params.setAccessible(true);
////        ReflectionUtils.setField(params,Task.class, value);
////    });
////
////
////        }
////        return taskRepository.save(currenttask.get());
////
//////        currenttask.setCompleted(task.getCompleted());
////
////       // return taskRepository.save(currenttask);
//
//   }

}
