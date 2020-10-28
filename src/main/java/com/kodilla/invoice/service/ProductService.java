package com.kodilla.invoice.service;

import com.kodilla.invoice.config.AdminConfig;
import com.kodilla.invoice.domain.*;
import com.kodilla.invoice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SimpleEmailService emailService;
    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "New product to db";
    private static final String SUBJECT_DELETE = "Delete product from db";
    private static final String SUBJECT_UPDATE = "Update product in db";

    public List<Product> getAllProductsFromDb() {
        return productRepository.findAll();
    }
    public Product getProductByIdFromDb(Long id){
        return productRepository.findById(id).orElse(null);
    }
    public Product save(final Product product) {
        ofNullable(product).ifPresent(customer->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
                "New product: "+ product.getName() + " has been created, and added to database only. Don't forget sending it to external services, in case using it by another users.")));
        assert product != null;
        return productRepository.save(product);
    }
    public Product update(final Product product) {
        Product updateProduct = productRepository.save(product);
        Optional.of(product).ifPresent(customer->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_UPDATE,
                "The product: "+ product.getId() + " has been updated in database only. New product name: " + product.getName())));
        return updateProduct;
    }
    public void deleteById(Long id) {

            productRepository.deleteById(id);
            emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT_DELETE,
                    "The product id = " + id + " has been deleted from database only. Don't forget delete it from external services."));


    }
}
