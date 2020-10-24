package com.kodilla.invoice.validator;

import com.kodilla.invoice.domain.BuyerDto;
import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.CreatedInvoiceDto;
import com.kodilla.invoice.domain.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceValidator.class);

    public List<Client> validateClients(final List<Client> clients) {
        LOGGER.info("Start fetching customers...");
        LOGGER.info("Customers have been fetched. Current list size: " + clients.size());
        return clients;
    }
    public void validateCustomerById(final Long id) {
        LOGGER.info("Starting fetching customer id = " + id);
        LOGGER.info("Customer id = " + id + " has been fetched.");
    }

    public void validateCreatingCustomer(final CustomerDto customerDto) {
        LOGGER.info("Starting creating a new customer = " + customerDto);
        LOGGER.info("Customer = " + customerDto + " has been created.");
    }

    public void validateDeletingCustomer(final Long id) {
        LOGGER.info("Starting deleting customer id = " + id);
        LOGGER.info("Customer id = " + id + " has been deleted.");
    }
    public void validateUpdateCustomer(final CustomerDto customerDto, Long id) {
        LOGGER.info("Starting updating customer id = " + id + " " + customerDto);
        LOGGER.info("Customer id = " + id + " has been updated.");
    }
}
