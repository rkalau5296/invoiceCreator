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
    private static final String SUBJECT_DELETE = "Delete client from Db";
    private static final String SUBJECT_UPDATE = "Update client in Db";

    public List<Client> getAllClientsFromDb() {
        return repository.findAll();
    }
    public Client getClientsByIdFromDb(Long id){
        return repository.findById(id).orElse(null);
    }
    public Client save(final Client client) {
        ofNullable(client).ifPresent(customer->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
                "The client: "+ customer.getName() + " has been created, and added to database only. Don't forget sending it to external services, in case using it by another users.")));
        assert client != null;
        return repository.save(client);
    }
    public Client update(final Client client) {
        ofNullable(client).ifPresent(customer->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_UPDATE,
                "The client: "+ customer.getName() + " has been updated in database only.")));
        assert client != null;
        return repository.save(client);
    }
    public void deleteById(Long id) {
        repository.deleteById(id);
        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_DELETE,
                "The client: "+ id + " has been deleted from database only. Don't forget delete it from external services."));
    }

}
