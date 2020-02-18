package com.kodilla.invoice.controller;

import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.ClientDto;
//import com.kodilla.invoice.domain.CreateClientDto;
import com.kodilla.invoice.domain.CustomerDto;
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
    public List<Client> getClientsFromDb() {
        return clientFacade.getAllClientsFromDb();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clients/{clientId}")
    public Client getClientByIdFromDb(@PathVariable Long clientId) {
        return clientFacade.getClientFromDbById(clientId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/clients", consumes = APPLICATION_JSON_VALUE)
    public Client createClient (@RequestBody CustomerDto createClientDto) {
        return clientFacade.createClient(createClientDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/clients/{clientId}")
    public void deleteClient (@PathVariable Long clientId){
        clientFacade.deletedById(clientId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/clients")
    public Client updateClient (@RequestBody ClientDto clientDto){
        return clientFacade.updateClient(clientDto);
    }

}
