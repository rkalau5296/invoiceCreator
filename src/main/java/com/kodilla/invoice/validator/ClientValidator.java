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
        LOGGER.info("String filtering clients...");
        List<Client> filteredClients = clients.stream()
                //.filter(product -> !product.getStatus().equalsIgnoreCase("test"))
                .collect(Collectors.toList());
        LOGGER.info("Clients have been filtered. Current list size: " + filteredClients.size());
        return filteredClients;
    }
}
