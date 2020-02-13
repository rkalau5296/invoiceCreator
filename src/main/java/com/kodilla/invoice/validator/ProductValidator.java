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
        LOGGER.info("String filtering products...");
        List<Product> filteredProducts = products.stream()
                //.filter(product -> !product.getStatus().equalsIgnoreCase("test"))
                .collect(Collectors.toList());
        LOGGER.info("products have been filtered. Products list size: " + filteredProducts.size());
        return filteredProducts;
    }
}
