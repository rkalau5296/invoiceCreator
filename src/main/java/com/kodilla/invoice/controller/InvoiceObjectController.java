package com.kodilla.invoice.controller;

//import com.kodilla.invoice.domain.*;
import com.kodilla.invoice.domain.BuyerDto;
import com.kodilla.invoice.domain.CreatedInvoiceDto;
import com.kodilla.invoice.domain.InvoiceObjectDto;
import com.kodilla.invoice.facade.InvoiceObjectFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/invoiceObject")
@CrossOrigin("*")
public class InvoiceObjectController {

    @Autowired
    private InvoiceObjectFacade invoiceObjectFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/invoices")
    public List<CreatedInvoiceDto> getInvoices() {
        return invoiceObjectFacade.fetchInvoices();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/invoices/{invoiceId}")
    public CreatedInvoiceDto getInvoice (@PathVariable Long invoiceId) {
        return invoiceObjectFacade.fetchInvoiceById(invoiceId);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/invoices")
    public CreatedInvoiceDto createInvoice (@RequestBody InvoiceObjectDto invoiceObjectDto) {
        return invoiceObjectFacade.createInvoice(invoiceObjectDto);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/invoices/{invoiceId}")
    public void deleteInvoice (@PathVariable Long invoiceId){
        invoiceObjectFacade.deleteInvoice(invoiceId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/invoices/{invoiceId}")
    public void updateInvoice (@RequestBody BuyerDto buyerDto, @PathVariable Long invoiceId){
         invoiceObjectFacade.updateInvoice(buyerDto,invoiceId);
    }
}
