package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.ClientDto;
import com.kodilla.invoice.domain.CreatedCustomerDto;
import com.kodilla.invoice.domain.CustomerDto;
import com.kodilla.invoice.mapper.ClientMapper;
import com.kodilla.invoice.service.ClientDtoService;
import com.kodilla.invoice.service.CustomerService;
import com.kodilla.invoice.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerFacade {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private ClientDtoService clientDtoService;
    @Autowired
    private ClientValidator clientValidator;

    public List<ClientDto> fetchClients() {
        List<Client> clients = clientMapper.mapToListClients(clientDtoService.fetchClients());
        List<Client> filteredInvoices = clientValidator.validateProducts(clients);
        List<ClientDto> filteredProductsDto = clientMapper.mapToListClientDto(filteredInvoices);
        return filteredProductsDto;
    }

    public ClientDto fetchClientById(Long id) {
        return clientDtoService.fetchClientById(id);
    }

    public CreatedCustomerDto createCustomer(final CustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }

}
