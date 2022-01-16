package com.baeldung.springbootreact.controller;

import com.baeldung.springbootreact.domain.Client;
import com.baeldung.springbootreact.repository.ClientRepository;
import com.baeldung.springbootreact.repository.ContactRepository;
import com.baeldung.springbootreact.repository.TaskRepository;
import com.baeldung.springbootreact.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

// entries to try
/*[
    {
        "id": 1,
        "name": "yesh",
        "contact": [],
        "task": []
    },
    {
        "id": 2,
        "name": "krat",
        "contact": [
            {
                "cid": 4,
                "name": null,
                "email": "nana",
                "phoneNumber": null
            }
        ],
        "task": []
    },
    {
        "id": 3,
        "name": "nana",
        "contact": [
            {
                "cid": 5,
                "name": null,
                "email": "yeshgmail",
                "phoneNumber": null
            }
        ],
        "task": [
            {
                "taskName": "id problem",
                "taskPriority": null,
                "taskComplexity": null,
                "completed": false,
                "id": 6,
                "deadline": null
            }
        ]
    }
]*/


@RestController
@RequestMapping
public class ClientsController {

    @Autowired
    public ClientService clientService;

    @Autowired
    public ClientRepository clientRepository;

    @Autowired
    public TaskRepository taskRepository;

    @Autowired
    public ContactRepository contactRepository;


    @GetMapping(value = "", produces = MediaType.TEXT_HTML_VALUE)
    public String greeting(){


        return "<html>\n" + "<header><title>Welcome</title></header>\n" +
                "<body>\n" + "This is landing page for Employee Task Management Project\n" + "Go to postman and use below commands to retrieve data\n" + "1 /clients after current url to get all clients \n" + "2 /clients/2/tasks after current url to get all tasks \n" + "3 /clients/2/contacts after current url to get all contacts\n"  + "</body>\n" + "</html>";

    }

    @GetMapping("/clients")
    public List<Client> getClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/clients/{id}")
    public Optional<Client> getClient(@PathVariable Long id) {
        return clientService.getoneClient(id);
    }

    @PostMapping("/clients")
    public ResponseEntity createClient(@RequestBody Client client) throws URISyntaxException {
        Client savedClient = clientService.createoneClient(client);
        return ResponseEntity.created(new URI("/clients/" + savedClient.getId())).body(savedClient);
    }
//
    @PutMapping("/clients/{id}")
    public ResponseEntity updateClient(@PathVariable("id") Long id, @RequestBody Client client) {
        Client currentClient = clientService.updatecurrentClient(id,client);


        return ResponseEntity.ok(currentClient);
    }
//
    @DeleteMapping("/clients/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        clientService.deleteoneClient(id);
        return ResponseEntity.ok().build();
    }

}