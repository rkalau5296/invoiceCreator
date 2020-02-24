package com.kodilla.invoice.validator;

import com.kodilla.invoice.domain.Client;
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
}
