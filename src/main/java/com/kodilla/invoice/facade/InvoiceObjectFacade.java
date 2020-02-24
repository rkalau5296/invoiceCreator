package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.*;
import com.kodilla.invoice.mapper.InvoiceMapper;
import com.kodilla.invoice.repository.CreatedInvoiceDtoRepository;
import com.kodilla.invoice.service.InvoiceDtoService;
import com.kodilla.invoice.service.InvoiceObjectService;
import com.kodilla.invoice.validator.InvoiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceObjectFacade {

    @Autowired
    private InvoiceDtoService invoiceDtoService;
    @Autowired
    private InvoiceObjectService invoiceObjectService;
    @Autowired
    private InvoiceValidator invoiceValidator;
    @Autowired
    private CreatedInvoiceDtoRepository createdInvoiceDtoRepository;

    public List<CreatedInvoiceDto> fetchInvoices() {
        List<CreatedInvoiceDto> filteredInvoices = invoiceDtoService.fetchInvoices();
        for(CreatedInvoiceDto createdInvoiceDto :filteredInvoices) {
            createdInvoiceDtoRepository.save(createdInvoiceDto);
        }
        invoiceValidator.validateInvoices(filteredInvoices);
        return filteredInvoices;
    }

    public CreatedInvoiceDto fetchInvoiceById(Long id) {
        return invoiceDtoService.fetchInvoiceById(id);
    }

    public CreatedInvoiceDto createInvoice(final InvoiceObjectDto invoiceObjectDto) {
        return invoiceObjectService.createInvoice(invoiceObjectDto);
    }
}
