package com.kodilla.invoice.controller;

import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.ClientDto;
import com.kodilla.invoice.facade.ClientFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class ClientController {
    @Autowired
    private ClientFacade clientFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/clients")
    public List<ClientDto> getClients() {
        return clientFacade.fetchClients();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clients/{clientId}")
    public ClientDto getClient (@PathVariable Long clientId) {
        return clientFacade.fetchClientById(clientId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/clients/{clientId}")
    public void deleteClient (@PathVariable Long productId){
        clientFacade.deletedById(productId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/clients")
    public ClientDto updateClient (@RequestBody ClientDto clientDto){
        return clientFacade.updateClient(clientDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/clients", consumes = APPLICATION_JSON_VALUE)
    public Client createClient (@RequestBody ClientDto clientDto) {
        return clientFacade.createClient(clientDto);
    }
}
