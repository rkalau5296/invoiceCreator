package com.kodilla.invoice.repository;

import com.kodilla.invoice.domain.CreatedInvoiceDto;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceObjectRepository extends CrudRepository<CreatedInvoiceDto, Long> {

    @Override
    CreatedInvoiceDto save (CreatedInvoiceDto createdInvoiceDto);

}
