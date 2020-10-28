package com.kodilla.invoice.service;

import com.kodilla.invoice.client.InvoiceClient;
import com.kodilla.invoice.config.AdminConfig;
import com.kodilla.invoice.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class InvoiceObjectService {

    @Autowired
    private SimpleEmailService emailService;
    @Autowired
    private InvoiceClient invoiceClient;
    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "New Invoice to fakturownia.pl ";
    private static final String SUBJECT_DELETE = "Delete Invoice from fakturownia.pl";
    private static final String SUBJECT_UPDATE = "Update Invoice in fakturownia.pl";

    public List<CreatedInvoiceDto> fetchInvoices() {
        return invoiceClient.getInvoices();
    }
    public CreatedInvoiceDto fetchInvoiceById(Long id) {
        return invoiceClient.getInvoicesById(id);
    }
    public CreatedInvoiceDto createInvoice(final InvoiceObjectDto invoiceObjectDto) {
        CreatedInvoiceDto newInvoice = invoiceClient.postInvoice(invoiceObjectDto);
                ofNullable(newInvoice).ifPresent(invoiceDto->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
            "New invoice to: "+ invoiceDto.getBuyer_name() + " has been created, and sent to fakturownia.pl.")));
        return newInvoice;
    }
    public void updateInvoice(final BuyerDto buyerDto, Long id){
        invoiceClient.updateInvoice(buyerDto, id);
        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_UPDATE,
                "The invoice id = " + id + " has been updated, and sent to fakturownia.pl. New buyer name is " + buyerDto.getInvoice().getBuyer_name()));

    }
    public void deleteInvoice(Long id) {
        invoiceClient.deleteInvoice(id);
        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_DELETE,
                "The invoice id = " + id + " has been deleted from fakturownia.pl."));
    }


}
