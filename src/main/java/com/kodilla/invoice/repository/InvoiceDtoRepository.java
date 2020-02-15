package com.kodilla.invoice.repository;

import com.kodilla.invoice.domain.InvoiceDto;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceDtoRepository extends CrudRepository<InvoiceDto, Long> {

    @Override
    InvoiceDto save (InvoiceDto invoiceDto);

    @Override
    void deleteById(Long id);

}
