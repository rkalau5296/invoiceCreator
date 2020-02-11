package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.domain.InvoiceDto;
import com.kodilla.invoice.mapper.InvoiceMapper;
import com.kodilla.invoice.service.InvoiceService;
import com.kodilla.invoice.validator.InvoiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceFacade {
    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private InvoiceValidator invoiceValidator;

    public List<InvoiceDto> fetchInvoices() {
        List<Invoice> invoices = invoiceMapper.mapToListInvoices(invoiceService.fetchInvoices());
        List<Invoice> filteredInvoices = invoiceValidator.validateInvoices(invoices);
        return invoiceMapper.mapToInvoiceDtoList(filteredInvoices);
    }
}
