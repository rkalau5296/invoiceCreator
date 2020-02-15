package com.kodilla.invoice.service;

import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    public List<Invoice> getAllInvoicesFromDb() {
        return repository.findAll();
    }

    public Invoice getInvoiceByIdFromDb(Long id){
        return repository.findById(id).orElse(null);
    }

    public Invoice saveInvoice(final Invoice invoice) {
        return repository.save(invoice);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
