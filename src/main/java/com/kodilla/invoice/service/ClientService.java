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

    public List<Client> getAllInvoices() {
        return repository.findAll();
    }

    public Client getInvoiceById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Client saveProduct(final Client client) {
        return repository.save(client);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
