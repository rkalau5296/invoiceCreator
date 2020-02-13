package com.kodilla.invoice.controller;

import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.ClientDto;
import com.kodilla.invoice.facade.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class ClientController {
    @Autowired
    private ProductFacade productFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/clients")
    public List<ClientDto> getClients() {
        return productFacade.fetchClients();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clients/{clientId}")
    public ClientDto getClient (@PathVariable Long productId) {
        return productFacade.fetchClientById(productId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/clients/{clientId}")
    public void deleteClient (@PathVariable Long productId){
        productFacade.deletedById(productId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/clients")
    public ClientDto updateClient (@RequestBody ClientDto clientDto){
        return productFacade.updateClient(clientDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/clients", consumes = APPLICATION_JSON_VALUE)
    public Client createClient (@RequestBody ClientDto clientDto) {
        return productFacade.createClient(clientDto);
    }
}
