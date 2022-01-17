package com.baeldung.springbootreact.service;

import com.baeldung.springbootreact.domain.Client;
import com.baeldung.springbootreact.domain.Contact;
import com.baeldung.springbootreact.repository.ClientRepository;
import com.baeldung.springbootreact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {



    @Autowired
    public ClientRepository clientRepository;

    @Autowired
    public ContactRepository contactRepository;


    public List<Contact> getcontacts(long id) {


        Optional<Client> clientOptional = clientRepository.findById(id);

        if(!clientOptional.isPresent()) {
            throw new IllegalStateException("client absent");
        }

        return clientOptional.get().getContact();
    }


    public ResponseEntity<Object> createcontact(long id, Contact contactvar) {

        //
        Optional<Client> clientOptional = clientRepository.findById(id);

        if(!clientOptional.isPresent()) {
            throw new IllegalStateException("client absent");
        }

        Client clientcurrent = clientOptional.get();

        contactvar.setClient(clientcurrent);

        contactRepository.save(contactvar);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contactvar.getCid())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    public void deletecontact(Long id1, Long id2) {


        contactRepository.deleteById(id2);


    }

   public Contact updatecontact(Long id1,
                                     Long id2, Contact contact) {


       Optional<Client> clientOptional = clientRepository.findById(id1);



       if(!clientOptional.isPresent()) {
           throw new IllegalStateException("client absent");
       }

       Client currentClient = clientOptional.get();



       Contact currentcontact=contactRepository.findById(id2).get();
       currentcontact.setName(contact.getName());
       currentcontact.setEmail(contact.getEmail());
       currentcontact.setPhoneNumber(contact.getPhoneNumber());
       currentcontact.setClient(currentClient);

        return contactRepository.save(currentcontact);

   }

    public Optional<Contact> getoneContact(Long id1, Long id2) {

        Optional<Client> clientOptional = clientRepository.findById(id1);



        if(!clientOptional.isPresent()) {
            throw new IllegalStateException("client absent");
        }
        else
        {
            return contactRepository.findById(id2);
        }

    }
}
