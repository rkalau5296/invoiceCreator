package com.kodilla.invoice.service;

import com.kodilla.invoice.domain.*;
import com.kodilla.invoice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllInvoicesFromDb() {
        return productRepository.findAll();
    }

    public Product getInvoiceByIdFromDb(Long id){
        return productRepository.findById(id).orElse(null);
    }
}
