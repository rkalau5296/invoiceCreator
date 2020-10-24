package com.kodilla.invoice.validator;

import com.kodilla.invoice.domain.*;
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
    public void validateProductById(final Long id) {
        LOGGER.info("Starting fetching product id = " + id);
        LOGGER.info("Product id = " + id + " has been fetched.");
    }

    public void validateCreatingProduct(final ProductObjectDto productObjectDto) {
        LOGGER.info("Starting creating a new product name = " + productObjectDto);
        LOGGER.info("Product name = " + productObjectDto + " has been created.");
    }

    public void validateDeletingProduct(final Long id) {
        LOGGER.info("Starting deleting product id = " + id);
        LOGGER.info("Product id = " + id + " has been deleted.");
    }
    public void validateUpdateProduct(final UpdateProductDto updateProductDto, Long id) {
        LOGGER.info("Starting updating product id = " + id + " " + updateProductDto);
        LOGGER.info("Product id = " + id + " has been updated.");
    }
}
