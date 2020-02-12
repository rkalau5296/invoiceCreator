package com.kodilla.invoice.validator;

import com.kodilla.invoice.domain.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceValidator.class);

    public List<Invoice> validateInvoices(final List<Invoice> invoices) {
        LOGGER.info("String filtering invoices...");
        List<Invoice> filteredInvoices = invoices.stream()
                .filter(invoice -> !invoice.getStatus().equalsIgnoreCase("test"))
                .collect(Collectors.toList());
        LOGGER.info("Invoices have been filtered. Current list size: " + filteredInvoices.size());
        return filteredInvoices;
    }

}
