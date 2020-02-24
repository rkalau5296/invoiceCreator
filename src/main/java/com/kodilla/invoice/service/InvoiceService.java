package com.kodilla.invoice.service;

import com.kodilla.invoice.config.AdminConfig;
import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.domain.Mail;
import com.kodilla.invoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;
    @Autowired
    private SimpleEmailService emailService;
    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "New invoice";

    public List<Invoice> getAllInvoicesFromDb() {
        return repository.findAll();
    }

    public Invoice getInvoiceByIdFromDb(Long id){
        return repository.findById(id).orElse(null);
    }

    public Invoice saveInvoice(final Invoice invoice) {
        ofNullable(invoice).ifPresent(customer->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
                "New invoice to customer id: "+ invoice.getClient_id()+ " has been created, and added to database only. Don't forget sending it to external services, in case using it by another users.")));
        return repository.save(invoice);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
