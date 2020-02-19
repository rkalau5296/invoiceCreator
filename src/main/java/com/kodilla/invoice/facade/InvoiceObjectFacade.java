package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.*;
import com.kodilla.invoice.mapper.InvoiceMapper;
import com.kodilla.invoice.service.InvoiceDtoService;
import com.kodilla.invoice.service.InvoiceObjectService;
import com.kodilla.invoice.validator.InvoiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceObjectFacade {

    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private InvoiceDtoService invoiceDtoService;
    @Autowired
    private InvoiceValidator invoiceValidator;
    @Autowired
    private InvoiceObjectService invoiceObjectService;

    public List<CreatedInvoiceDto> fetchInvoices() {
//        List<Invoice> products = invoiceMapper.mapToListInvoices(invoiceDtoService.fetchInvoices());
//        List<Invoice> filteredInvoices = invoiceValidator.validateInvoices(products);
//        List<InvoiceDto> filteredProductsDto = invoiceMapper.mapToInvoiceDtoList(filteredInvoices);
        return invoiceDtoService.fetchInvoices();
    }

    public CreatedInvoiceDto fetchProductById(Long id) {
        return invoiceDtoService.fetchInvoiceById(id);
    }

    public CreatedInvoiceDto createInvoice(final InvoiceObjectDto invoiceObjectDto) {
        return invoiceObjectService.createInvoice(invoiceObjectDto);
    }
}
