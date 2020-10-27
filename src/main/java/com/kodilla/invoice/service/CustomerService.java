package com.kodilla.invoice.service;

import com.kodilla.invoice.client.InvoiceClient;
import com.kodilla.invoice.config.AdminConfig;
import com.kodilla.invoice.domain.ClientDto;
import com.kodilla.invoice.domain.CreatedCustomerDto;
import com.kodilla.invoice.domain.CustomerDto;
import com.kodilla.invoice.domain.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class CustomerService {

    @Autowired
    private InvoiceClient invoiceClient;
    @Autowired
    private SimpleEmailService emailService;
    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "New customer to fakturownia.pl";
    private static final String SUBJECT_DELETE = "Delete customer from fakturownia.pl";
    private static final String SUBJECT_UPDATE = "Update customer in fakturownia.pl";

    public List<ClientDto> fetchClients() {
        return invoiceClient.getCustomers();
    }
    public ClientDto fetchClientById(Long id) {
        return invoiceClient.getCustomerById(id);
    }
    public CreatedCustomerDto createCustomer(final CustomerDto customerDto) {
        CreatedCustomerDto newCustomer = invoiceClient.postCustomer(customerDto);
                ofNullable(newCustomer).ifPresent(customer->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
                "New customer: "+ customer.getName() + " has been created, and sent to fakturownia.pl.")));
        return newCustomer;
    }
    public void updateCustomer(final CustomerDto customerDto, Long id) {
        invoiceClient.updateCustomer(customerDto, id);
        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_UPDATE,
                "The customer id = " + id + " has been updated, and sent to fakturownia.pl. New customer name is " + customerDto.getClient().getName()));
    }
    public void deleteCustomer(Long id) {
        invoiceClient.deleteCustomerById(id);
        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_DELETE,
                "The customer id = " + id + " has been deleted from fakturownia.pl."));
    }
}
