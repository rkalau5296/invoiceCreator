package com.kodilla.invoice.service;

import com.kodilla.invoice.client.InvoiceClient;
import com.kodilla.invoice.config.AdminConfig;
import com.kodilla.invoice.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;
@Service
public class ProductObjectService {
    @Autowired
    private InvoiceClient invoiceClient;
    @Autowired
    private SimpleEmailService emailService;
    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "New product to fakturownia.pl";
    private static final String SUBJECT_DELETE = "Delete product from fakturownia.pl";
    private static final String SUBJECT_UPDATE = "Update product in fakturownia.pl";

    public List<ProductDto> fetchProducts() {
        return invoiceClient.getProducts();
    }
    public ProductDto fetchProductById(Long id) {
        return invoiceClient.getProductById(id);
    }
    public CreatedProductDto createProduct(final ProductObjectDto productObjectDto) {
        CreatedProductDto newProduct = invoiceClient.postProduct(productObjectDto);
                ofNullable(newProduct).ifPresent(product->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
                "New product: "+ product.getName() + " has been created, and sent to fakturownia.pl.")));
        return newProduct;
    }
    public void updateProduct(UpdateProductDto productObjectDto, Long productId) {
        invoiceClient.updateProduct(productObjectDto, productId);
        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_UPDATE,
                "The product id = " + productId + " has been updated, and sent to fakturownia.pl. New product name is " + productObjectDto.getProduct().getName()));
    }
    public void deleteById(Long id) {
        invoiceClient.deleteProductById(id);
        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_DELETE,
                "The product id = " + id + " has been deleted from fakturownia.pl."));
    }

}
