package com.kodilla.invoice.service;

import com.kodilla.invoice.client.InvoiceClient;
import com.kodilla.invoice.domain.CreatedInvoiceDto;
import com.kodilla.invoice.domain.InvoiceDto;
import com.kodilla.invoice.repository.InvoiceDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InvoiceDtoService {

    @Autowired
    private InvoiceDtoRepository repository;
    @Autowired
    private InvoiceClient invoiceClient;

    public List<CreatedInvoiceDto> fetchInvoices() {
        return invoiceClient.getInvoices();
    }

    public CreatedInvoiceDto fetchInvoiceById(Long id) {
        return invoiceClient.getInvoicesById(id);
    }

    public InvoiceDto saveInvoice(final InvoiceDto invoiceDto) {
        return repository.save(invoiceDto);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
