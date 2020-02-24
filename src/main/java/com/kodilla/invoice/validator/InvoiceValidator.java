package com.kodilla.invoice.validator;

import com.kodilla.invoice.domain.CreatedInvoiceDto;
import com.kodilla.invoice.domain.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceValidator.class);

    public List<CreatedInvoiceDto> validateInvoices(final List<CreatedInvoiceDto> invoices) {
        LOGGER.info("Starting fetching invoices...");
        LOGGER.info("Invoices have been fetched. Current list size: " + invoices.size());
        return invoices;
    }

}
