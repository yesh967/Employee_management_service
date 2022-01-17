package com.baeldung.springbootreact.controller;


import com.baeldung.springbootreact.domain.Contact;
import com.baeldung.springbootreact.repository.ClientRepository;
import com.baeldung.springbootreact.repository.ContactRepository;
import com.baeldung.springbootreact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ContactController {

    @Autowired
    public ClientRepository clientRepository;

    @Autowired
    public ContactRepository contactRepository;

    @Autowired
    public ContactService contactService;

    // Contacts



    @GetMapping("/{id}/contacts")
    public List<Contact> retrieveAllClients(@PathVariable long id) {

        return contactService.getcontacts(id);
    }

    @GetMapping("/clients/{id1}/contacts/{id2}")
    public Optional<Contact> getClient(@PathVariable("id1") Long id1,@PathVariable("id2") Long id2) {
        return contactService.getoneContact(id1,id2);
    }

    @PostMapping("/{id}/contacts")
    public ResponseEntity<Object> createTask(@PathVariable long id, @RequestBody Contact contactvar) {


        return contactService.createcontact(id,contactvar);

    }

    @DeleteMapping("/{id1}/contacts/{id2}")
    public ResponseEntity deleteClientcontact(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2) {

        contactService.deletecontact(id1,id2);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id1}/contacts/{id2}")
    public ResponseEntity updateClientcontacts(@PathVariable("id1") Long id1,@PathVariable("id2") Long id2, @RequestBody Contact contact)
    {

        Contact currentcontact=contactService.updatecontact(id1, id2, contact);
    return ResponseEntity.ok(currentcontact);
   }


}
