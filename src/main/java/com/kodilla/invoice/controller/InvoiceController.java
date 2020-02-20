package com.kodilla.invoice.controller;

import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.domain.InvoiceDto;
import com.kodilla.invoice.domain.InvoiceObjectDto;
import com.kodilla.invoice.facade.InvoiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class InvoiceController {
    @Autowired
    private InvoiceFacade invoiceFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/invoices")
    public List<Invoice> getInvoicesFromDb() {
        return invoiceFacade.getAllInvoicesFromDb();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/invoices/{invoiceId}")
    public Invoice getInvoiceByIdFromDb(@PathVariable Long invoiceId) {
        return invoiceFacade.getInvoicesFromDbById(invoiceId);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/invoices", consumes = APPLICATION_JSON_VALUE)
    public Invoice createInvoice (@RequestBody InvoiceObjectDto invoiceObjectDto) {
        return invoiceFacade.createInvoice(invoiceObjectDto);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/invoices")
    public Invoice updateInvoice (@RequestBody InvoiceDto invoiceDto) {
        return invoiceFacade.updateInvoice(invoiceDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/invoices/{invoiceId}")
    public void deleteInvoice (@PathVariable Long invoiceId){
        invoiceFacade.deletedById(invoiceId);
    }





}
