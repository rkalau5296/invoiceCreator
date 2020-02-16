package com.kodilla.invoice.service;

import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public List<Client> getAllClientsFromDb() {
        return repository.findAll();
    }

    public Client getClientsByIdFromDb(Long id){
        return repository.findById(id).orElse(null);
    }

    public Client saveClient(final Client clients) {
        return repository.save(clients);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
