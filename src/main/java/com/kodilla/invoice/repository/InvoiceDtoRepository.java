package com.kodilla.invoice.repository;

import com.kodilla.invoice.domain.InvoiceDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceDtoRepository extends CrudRepository<InvoiceDto, Long> {
    @Override
    List<InvoiceDto> findAll();

    @Override
    Optional<InvoiceDto> findById(Long id);

    @Override
    InvoiceDto save (InvoiceDto invoiceDto);


    @Override
    void deleteById(Long id);

    @Override
    long count();
}
