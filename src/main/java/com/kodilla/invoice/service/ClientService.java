package com.kodilla.invoice.service;

import com.kodilla.invoice.config.AdminConfig;
import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.Mail;
import com.kodilla.invoice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;
    @Autowired
    private SimpleEmailService emailService;
    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "New client";
    public List<Client> getAllClientsFromDb() {
        return repository.findAll();
    }

    public Client getClientsByIdFromDb(Long id){
        return repository.findById(id).orElse(null);
    }

    public Client save(final Client client) {
        ofNullable(client).ifPresent(customer->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
                "New customer: "+ customer.getName() + " has been created, and added to database only. Don't forget sending it to external services, in case using it by another users.")));
        return repository.save(client);
    }
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
