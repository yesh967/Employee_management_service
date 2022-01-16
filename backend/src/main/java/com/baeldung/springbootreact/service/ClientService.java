package com.baeldung.springbootreact.service;

import com.baeldung.springbootreact.domain.Client;
import com.baeldung.springbootreact.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    public ClientRepository clientRepository;


    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }


    public Optional<Client> getoneClient(Long id) {
        return clientRepository.findById(id);
    }


    public Client createoneClient(Client client){
        Client savedClient = clientRepository.save(client);

    return savedClient;
    }

    public Client updatecurrentClient(@PathVariable Long id, @RequestBody Client client) {

        Client currentClient = clientRepository.findById(id).orElseThrow(RuntimeException::new);
        currentClient.setName(client.getName());

        return currentClient;
    }

    public void deleteoneClient(Long id) {
        clientRepository.deleteById(id);

    }

}
