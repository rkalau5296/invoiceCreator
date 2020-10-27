package com.kodilla.invoice.service;

import com.kodilla.invoice.config.AdminConfig;
import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.domain.Mail;
import com.kodilla.invoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;
    @Autowired
    private SimpleEmailService emailService;
    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "New invoice to db";
    private static final String SUBJECT_DELETE = "Delete Invoice from db ";
    private static final String SUBJECT_UPDATE = "Update Invoice in db";
    public List<Invoice> getAllInvoicesFromDb() {
        return repository.findAll();
    }
    public Invoice getInvoiceByIdFromDb(Long id){
        return repository.findById(id).orElse(null);
    }
    public Invoice saveInvoice(final Invoice invoice) {
        Invoice saveInvoice = repository.save(invoice);
        Optional.of(invoice).ifPresent(customer->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
                "New invoice to customer id: "+ invoice.getClient_id()+ " has been created, and added to database only. Don't forget sending it to external services, in case using it by another users.")));
        return saveInvoice;
    }
    public Invoice updateInvoice(final Invoice invoice) {
        Invoice updateInvoice = repository.save(invoice);
        Optional.of(invoice).ifPresent(customer->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_UPDATE,
                "New invoice to customer id: "+ invoice.getClient_id()+ " has been updated, and added to database only.")));
        return updateInvoice;
    }
    public void deleteById(Long id) {
        repository.deleteById(id);
        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_DELETE,
                "The invoice id = " + id + " has been deleted from database only. Don't forget delete it from external services."));
    }
}
