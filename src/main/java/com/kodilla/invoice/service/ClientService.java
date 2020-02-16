package com.kodilla.invoice.service;

import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.CreateClient;
import com.kodilla.invoice.repository.ClientRepository;
import com.kodilla.invoice.repository.CreateClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;
    @Autowired
    private CreateClientRepository createClientRepository;

    public List<Client> getAllClientsFromDb() {
        return repository.findAll();
    }

    public Client getClientsByIdFromDb(Long id){
        return repository.findById(id).orElse(null);
    }

    public CreateClient saveClient(final CreateClient clients) {
        return createClientRepository.save(clients);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
