package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.ClientDto;
import com.kodilla.invoice.domain.CustomerDto;
import com.kodilla.invoice.mapper.ClientMapper;
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
    private ClientService clientService;
    @Autowired
    private ClientValidator clientValidator;

    public List<Client> getAllClientsFromDb(){
        List<Client> clientDtoList = clientService.getAllClientsFromDb();
        clientValidator.validateClients(clientDtoList);
        return clientDtoList;
    }
    public Client getClientFromDbById(Long id){
        clientValidator.validateCustomerById(id);
        return clientService.getClientsByIdFromDb(id);
    }
    public Client createClient(CustomerDto clientDto) {
        clientValidator.validateCreatingCustomer(clientDto);
        return clientService.save(clientMapper.mapToClientFromCustomerDto(clientDto));
    }
    public Client updateClient(ClientDto clientDto) {
        clientValidator.validateUpdateClient(clientDto);
        return clientService.update(clientMapper.mapToClient(clientDto));
    }
    public void deletedById(Long id) {
        clientValidator.validateDeletingCustomer(id);
        clientService.deleteById(id);
    }




}
