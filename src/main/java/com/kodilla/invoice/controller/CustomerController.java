package com.kodilla.invoice.controller;

import com.kodilla.invoice.domain.ClientDto;
import com.kodilla.invoice.domain.CreatedCustomerDto;
import com.kodilla.invoice.domain.CustomerDto;
import com.kodilla.invoice.facade.CustomerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private CustomerFacade customerFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/customers")
    public List<ClientDto> getClients() {
        return customerFacade.fetchClients();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers/{customerId}")
    public ClientDto getClient (@PathVariable Long customerId) {
        return customerFacade.fetchClientById(customerId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customers")
    public CreatedCustomerDto createCustomer (@RequestBody CustomerDto customerDto) {
        return customerFacade.createCustomer(customerDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/customers/{customerId}")
    public void updateCustomer (@RequestBody CustomerDto customerDto, @PathVariable Long customerId) {
        customerFacade.updateCustomer(customerDto,customerId);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/customers/{customerId}")
    public void deleteCustomer (@PathVariable Long customerId) {
        customerFacade.deleteCustomer(customerId);
    }


}
