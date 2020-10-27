package com.kodilla.invoice.validator;

import com.kodilla.invoice.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceValidator.class);

    public List<CreatedInvoiceDto> validateInvoices(final List<CreatedInvoiceDto> invoices) {
        LOGGER.info("Starting fetching invoices...");
        LOGGER.info("Invoices have been fetched. Current list size: " + invoices.size());
        return invoices;
    }
    public void validatingInvoice(final List<Invoice> invoices) {
        LOGGER.info("Starting fetching invoices...");
        LOGGER.info("Invoices have been fetched. Current list size: " + invoices.size());
    }
    public void validateInvoicesById(final Long id) {
        LOGGER.info("Starting fetching invoice id = " + id);
        LOGGER.info("Invoice id = " + id + " has been fetched.");
    }

    public void validateCreatingInvoice(final CreatedInvoiceDto invoices) {
        LOGGER.info("Starting creating a new invoice = " + invoices);
        LOGGER.info("Invoice = " + invoices + " has been created.");
    }

    public void validateDeletingInvoice(final Long id) {
        LOGGER.info("Starting deleting invoice id = " + id);
        LOGGER.info("Invoice id = " + id + " has been deleted.");
    }
    public void validateUpdateInvoice(final BuyerDto buyerDto, Long id) {
        LOGGER.info("Starting updating invoice id = " + id + " " + buyerDto);
        LOGGER.info("Invoice id = " + id + " has been updated.");
    }
    public void validateCreating(final InvoiceObjectDto invoiceObjectDto) {
        LOGGER.info("Starting creating a new invoice = " + invoiceObjectDto);
        LOGGER.info("Invoice = " + invoiceObjectDto + " has been created.");
    }
    public void validateUpdating(final InvoiceDto invoiceDto) {
        LOGGER.info("Starting creating a new invoice = " + invoiceDto);
        LOGGER.info("Invoice = " + invoiceDto + " has been created.");
    }

}
