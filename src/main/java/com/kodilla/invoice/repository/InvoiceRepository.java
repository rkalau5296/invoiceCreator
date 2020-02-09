package com.kodilla.invoice.repository;

import com.kodilla.invoice.domain.Invoice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    @Override
    List<Invoice> findAll();

    @Override
    Optional<Invoice> findById(Long id);

    @Override
    Invoice save (Invoice invoice);

    @Override
    void deleteById(Long id);

    @Override
    long count();
}
