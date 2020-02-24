package com.kodilla.invoice.validator;

import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceValidator.class);

    public List<Product> validateProducts(final List<Product> products) {
        LOGGER.info("Start fetching products...");
        LOGGER.info("Products have been fetched. Products list size: " + products.size());
        return products;
    }
}
