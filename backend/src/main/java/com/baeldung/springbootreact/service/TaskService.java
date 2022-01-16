package com.baeldung.springbootreact.service;

import com.baeldung.springbootreact.domain.Client;
import com.baeldung.springbootreact.domain.Task;
import com.baeldung.springbootreact.repository.ClientRepository;
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
    public ClientRepository clientRepository;

    @Autowired
    public TaskRepository taskRepository;

    public List<Task> gettasks(long id) {


        Optional<Client> clientOptional = clientRepository.findById(id);

        if(!clientOptional.isPresent()) {
            throw new IllegalStateException("client absent");
        }

        return clientOptional.get().getTask();

    }

    public ResponseEntity updatetask(Long id1, Long id2, Task task) {



        Optional<Client> clientOptional = clientRepository.findById(id1);
             Integer i = id2.intValue();


        if(!clientOptional.isPresent()) {
            throw new IllegalStateException("client absent");
        }

        Client clientcurrent = clientOptional.get();

        Task currenttask=taskRepository.findById(i).get();


        currenttask.setTaskName(task.getTaskName());
        currenttask.setTaskPriority(task.getTaskPriority());
        currenttask.setTaskComplexity(task.getTaskComplexity());
        currenttask.setDeadline(task.getDeadline());
        currenttask.setCompleted(task.getCompleted());
        currenttask.setClient(clientcurrent);

        taskRepository.save(currenttask);



        return ResponseEntity.ok(currenttask);

    }

    public void deletetask(Long id1, int id2) {


taskRepository.deleteById(id2);

    }

    public ResponseEntity<Object> createtasks(long id, Task task) {


        Optional<Client> clientOptional = clientRepository.findById(id);

        if(!clientOptional.isPresent()) {
            throw new IllegalStateException("client absent");
        }

        Client clientcurrent = clientOptional.get();

        task.setClient(clientcurrent);

        taskRepository.save(task);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(task.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }
}
