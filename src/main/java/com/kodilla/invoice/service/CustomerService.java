package com.kodilla.invoice.service;

import com.kodilla.invoice.client.InvoiceClient;
import com.kodilla.invoice.domain.CreatedCustomerDto;
import com.kodilla.invoice.domain.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private InvoiceClient invoiceClient;

    public CreatedCustomerDto createCustomer(final CustomerDto customerDto) {
        CreatedCustomerDto newCustomer = invoiceClient.postCustomer(customerDto);
        //        ofNullable(newCustomer).ifPresent(customer->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
////                "New customer: "+ card.getName() + " has been created on your Trello account")));

        return newCustomer;
    }
}
