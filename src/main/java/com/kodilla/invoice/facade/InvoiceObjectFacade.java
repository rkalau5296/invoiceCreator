package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.*;
import com.kodilla.invoice.repository.InvoiceObjectRepository;
import com.kodilla.invoice.service.InvoiceObjectService;
import com.kodilla.invoice.validator.InvoiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceObjectFacade {

    @Autowired
    private InvoiceObjectService invoiceObjectService;
    @Autowired
    private InvoiceValidator invoiceValidator;
    @Autowired
    private InvoiceObjectRepository invoiceObjectRepository;

    public List<CreatedInvoiceDto> fetchInvoices() {
        List<CreatedInvoiceDto> filteredInvoices = invoiceObjectService.fetchInvoices();
        for(CreatedInvoiceDto createdInvoiceDto :filteredInvoices) {
            invoiceObjectRepository.save(createdInvoiceDto);
        }
        invoiceValidator.validateInvoices(filteredInvoices);
        return filteredInvoices;
    }
    public CreatedInvoiceDto fetchInvoiceById(Long id) {
        invoiceValidator.validateInvoicesById(id);
        return invoiceObjectService.fetchInvoiceById(id);
    }
    public CreatedInvoiceDto createInvoice(final InvoiceObjectDto invoiceObjectDto) {
        CreatedInvoiceDto fetchedInvoice = invoiceObjectService.createInvoice(invoiceObjectDto);
        invoiceValidator.validateCreatingInvoice(fetchedInvoice);
        return fetchedInvoice;
    }
    public void deletedById(Long id) {
        invoiceValidator.validateDeletingInvoice(id);
        invoiceObjectService.deleteById(id);
    }
    public void updateInvoice(final BuyerDto buyerDto, Long id) {
        invoiceValidator.validateUpdateInvoice(buyerDto, id);
        invoiceObjectService.updateInvoice(buyerDto, id);
    }
}
