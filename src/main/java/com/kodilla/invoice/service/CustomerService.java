package com.kodilla.invoice.service;

import com.kodilla.invoice.client.InvoiceClient;
import com.kodilla.invoice.config.AdminConfig;
import com.kodilla.invoice.domain.CreatedCustomerDto;
import com.kodilla.invoice.domain.CustomerDto;
import com.kodilla.invoice.domain.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
public class CustomerService {

    @Autowired
    private InvoiceClient invoiceClient;
    @Autowired
    private SimpleEmailService emailService;
    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "New customer";
    public CreatedCustomerDto createCustomer(final CustomerDto customerDto) {
        CreatedCustomerDto newCustomer = invoiceClient.postCustomer(customerDto);
                ofNullable(newCustomer).ifPresent(customer->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
                "New customer: "+ customer.getName() + " has been created, and sent to fakturownia.pl.")));

        return newCustomer;
    }

    public void updateCustomer(final CustomerDto customerDto, Long id) {
        invoiceClient.updateCustomer(customerDto, id);
    }
    public void deleteCustomer(Long id) {
        invoiceClient.deleteCustomerById(id);
    }
}
