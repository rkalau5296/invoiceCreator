package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.domain.InvoiceDto;
import com.kodilla.invoice.mapper.InvoiceMapper;
import com.kodilla.invoice.service.InvoiceDtoService;
import com.kodilla.invoice.service.InvoiceService;
import com.kodilla.invoice.validator.InvoiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceFacade {

    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private InvoiceValidator invoiceValidator;
    @Autowired
    private InvoiceDtoService invoiceDtoService;
    @Autowired
    private InvoiceService invoiceService;

    public List<InvoiceDto> fetchInvoices() {
        List<Invoice> invoices = invoiceMapper.mapToListInvoices(invoiceDtoService.fetchInvoices());
        List<Invoice> filteredInvoices = invoiceValidator.validateInvoices(invoices);
        List<InvoiceDto> filteredInvoicesDto = invoiceMapper.mapToInvoiceDtoList(filteredInvoices);
        for (InvoiceDto invoiceDto: filteredInvoicesDto)
        {
            invoiceDtoService.saveInvoice(invoiceDto);
        }
        return filteredInvoicesDto;
    }

    public InvoiceDto fetchInvoicesById(Long id) {
        return invoiceDtoService.saveInvoice(invoiceDtoService.fetchInvoiceById(id));
    }

    public void deletedById(Long id) {

        invoiceDtoService.deleteById(id);
    }

    public InvoiceDto updateInvoice(InvoiceDto invoiceDto) {
        return invoiceDtoService.saveInvoice(invoiceDto);
    }
    public Invoice createInvoice(InvoiceDto invoiceDto) {
        return invoiceService.saveInvoice(invoiceMapper.mapToInvoice(invoiceDto));
    }


}
