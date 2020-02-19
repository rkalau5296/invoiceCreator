package com.kodilla.invoice.service;

import com.kodilla.invoice.client.InvoiceClient;
import com.kodilla.invoice.domain.CreatedInvoiceDto;
import com.kodilla.invoice.domain.CreatedProductDto;
import com.kodilla.invoice.domain.InvoiceObjectDto;
import com.kodilla.invoice.domain.ProductObjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceObjectService {
    @Autowired
    private InvoiceClient invoiceClient;

    public CreatedInvoiceDto createInvoice(final InvoiceObjectDto invoiceObjectDto) {
        CreatedInvoiceDto newProduct = invoiceClient.postInvoice(invoiceObjectDto);
        //        ofNullable(newCustomer).ifPresent(customer->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
////                "New customer: "+ card.getName() + " has been created on your Trello account")));

        return newProduct;
    }
}
