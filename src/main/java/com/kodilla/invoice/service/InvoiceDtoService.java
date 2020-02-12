package com.kodilla.invoice.service;

import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.domain.InvoiceDto;
import com.kodilla.invoice.repository.InvoiceDtoRepository;
import com.kodilla.invoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceDtoService {

    @Autowired
    private InvoiceDtoRepository repository;

    public List<InvoiceDto> getAllInvoices() {
        return repository.findAll();
    }

    public InvoiceDto getInvoiceById(Long id){
        return repository.findById(id).orElse(null);
    }

    public InvoiceDto saveInvoice(final InvoiceDto invoiceDto) {
        return repository.save(invoiceDto);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
