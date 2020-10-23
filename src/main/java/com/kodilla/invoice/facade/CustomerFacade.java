package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.ClientDto;
import com.kodilla.invoice.domain.CreatedCustomerDto;
import com.kodilla.invoice.domain.CustomerDto;
import com.kodilla.invoice.mapper.ClientMapper;
import com.kodilla.invoice.repository.ClientDtoRepository;
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
    @Autowired
    private ClientDtoRepository clientDtoRepository;

    public List<ClientDto> fetchClients() {
        List<ClientDto> clientDtoList = clientDtoService.fetchClients();
        for(ClientDto clientDto :clientDtoList) {
            clientDtoRepository.save(clientDto);
        }
        List<Client> clients = clientMapper.mapToListClients(clientDtoList);
        List<Client> filteredInvoices = clientValidator.validateClients(clients);
        List<ClientDto> filteredProductsDto = clientMapper.mapToListClientDto(filteredInvoices);
        return filteredProductsDto;
    }

    public ClientDto fetchClientById(Long id) {
        return clientDtoService.fetchClientById(id);
    }

    public CreatedCustomerDto createCustomer(final CustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }
    public void updateCustomer(final CustomerDto customerDto, Long id) {
        customerService.updateCustomer(customerDto, id);
    }
    public void deleteCustomer(Long id) {
        customerService.deleteCustomer(id);
    }

}
