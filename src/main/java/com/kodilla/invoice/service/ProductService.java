package com.kodilla.invoice.service;

import com.kodilla.invoice.client.InvoiceClient;
import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.domain.Product;
import com.kodilla.invoice.repository.InvoiceRepository;
import com.kodilla.invoice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getAllInvoices() {
        return repository.findAll();
    }

    public Product getInvoiceById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Product saveProduct(final Product product) {
        return repository.save(product);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
