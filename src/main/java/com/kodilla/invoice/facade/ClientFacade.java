package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.ClientDto;
import com.kodilla.invoice.domain.CustomerDto;
import com.kodilla.invoice.mapper.ClientMapper;
import com.kodilla.invoice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientFacade {

    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private ClientService clientService;

    public void deletedById(Long id) {
        clientService.deleteById(id);
    }
    public Client updateClient(ClientDto clientDto) {
        return clientService.update(clientMapper.mapToClient(clientDto));
    }
    public Client createClient(CustomerDto clientDto) {

        return clientService.save(clientMapper.mapToClientFromCustomerDto(clientDto));
    }
    public List<Client> getAllClientsFromDb(){
        return clientService.getAllClientsFromDb();
    }
    public Client getClientFromDbById(Long id){
        return clientService.getClientsByIdFromDb(id);
    }

}
