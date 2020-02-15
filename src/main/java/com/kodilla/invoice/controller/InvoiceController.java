package com.kodilla.invoice.controller;

import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.domain.InvoiceDto;
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
    public List<InvoiceDto> getInvoices() {
        return invoiceFacade.fetchInvoices();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/invoices/{invoiceId}")
    public InvoiceDto getInvoice (@PathVariable Long invoiceId) {
        return invoiceFacade.fetchInvoicesById(invoiceId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/invoices/{invoiceId}")
    public void deleteInvoice (@PathVariable Long invoiceId){
        invoiceFacade.deletedById(invoiceId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/invoices")
    public InvoiceDto updateInvoice (@RequestBody InvoiceDto invoiceDto) {
        return invoiceFacade.updateInvoice(invoiceDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/invoices", consumes = APPLICATION_JSON_VALUE)
    public Invoice createInvoice (@RequestBody InvoiceDto invoiceDto) {
        return invoiceFacade.createInvoice(invoiceDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/invoicesDb")
    public List<Invoice> getInvoicesFromDb() {
        return invoiceFacade.getAllInvoicesFromDb();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/invoicesDb/{invoiceId}")
    public Invoice getInvoiceByIdFromDb(@PathVariable Long invoiceId) {
        return invoiceFacade.getInvoicesFromDbById(invoiceId);
    }
}
