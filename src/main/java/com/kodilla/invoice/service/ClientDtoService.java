package com.kodilla.invoice.service;

import com.kodilla.invoice.client.InvoiceClient;

import com.kodilla.invoice.domain.ClientDto;
import com.kodilla.invoice.domain.CreateClientDto;
import com.kodilla.invoice.repository.ClientDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class ClientDtoService {
    @Autowired
    private ClientDtoRepository repository;
    @Autowired
    private InvoiceClient invoiceClient;


    public List<ClientDto> fetchClients() {
        return invoiceClient.getClients();
    }

    public ClientDto fetchClientById(Long id) {
        return invoiceClient.getClientById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public CreateClientDto createClient(final CreateClientDto clientDto){
        CreateClientDto newClient =  invoiceClient.postClient();
//        ofNullable(newClient).ifPresent(client->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
////                "New card: "+ card.getName() + " has been created on your Trello account")));
        return newClient;
    }
}
