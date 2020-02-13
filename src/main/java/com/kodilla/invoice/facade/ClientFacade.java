package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.ClientDto;
import com.kodilla.invoice.mapper.ClientMapper;
import com.kodilla.invoice.service.ClientDtoService;
import com.kodilla.invoice.service.ClientService;
import com.kodilla.invoice.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientFacade {

    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private ClientDtoService clientDtoService;
    @Autowired
    private ClientValidator clientValidator;
    @Autowired
    private ClientService clientService;

    public List<ClientDto> fetchClients() {
        List<Client> clients = clientMapper.mapToListClients(clientDtoService.fetchClients());
        List<Client> filteredInvoices = clientValidator.validateProducts(clients);
        List<ClientDto> filteredProductsDto = clientMapper.mapToListClientDto(filteredInvoices);
        for (ClientDto clientDto : filteredProductsDto)
        {
            clientDtoService.saveClient(clientDto);
        }
        return filteredProductsDto;
    }

    public ClientDto fetchClientById (Long id) {
        return clientDtoService.saveClient(clientDtoService.fetchClientById(id));
    }

    public void deletedById(Long id) {

        clientDtoService.deleteById(id);
    }
    public ClientDto updateClient(ClientDto clientDto) {
        return clientDtoService.saveClient(clientDto);
    }
    public Client createClient(ClientDto clientDto) {
        return clientService.saveClient(clientMapper.mapToClient(clientDto));
    }
}
