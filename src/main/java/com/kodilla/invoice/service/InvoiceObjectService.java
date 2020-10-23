package com.kodilla.invoice.service;

import com.kodilla.invoice.client.InvoiceClient;
import com.kodilla.invoice.config.AdminConfig;
import com.kodilla.invoice.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
public class InvoiceObjectService {

    @Autowired
    private SimpleEmailService emailService;
    @Autowired
    private InvoiceClient invoiceClient;
    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "New Invoice ";
    public CreatedInvoiceDto createInvoice(final InvoiceObjectDto invoiceObjectDto) {
        CreatedInvoiceDto newInvoice = invoiceClient.postInvoice(invoiceObjectDto);
                ofNullable(newInvoice).ifPresent(invoiceDto->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
            "New invoice to: "+ invoiceDto.getBuyer_name() + " has been created, and sent to fakturownia.pl.")));

        return newInvoice;
    }
    public void deleteById(Long id) {
        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
                "The invoice id = " + id + " has been deleted from fakturownia.pl."));
        invoiceClient.deleteById(id);
    }
    public void updateInvoice(final BuyerDto buyerDto, Long id){

        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
                "The invoice id = " + id + " has been updated, and sent to fakturownia.pl. New buyer name is " + buyerDto));

         invoiceClient.updateInvoice(buyerDto, id);

    }
}
