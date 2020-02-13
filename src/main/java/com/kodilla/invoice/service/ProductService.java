package com.kodilla.invoice.service;

import com.kodilla.invoice.client.InvoiceClient;
import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.ClientDto;
import com.kodilla.invoice.domain.Product;
import com.kodilla.invoice.domain.ProductDto;
import com.kodilla.invoice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InvoiceClient invoiceClient;

    public List<ProductDto> fetchProducts() {
        return invoiceClient.getProducts();
    }
    public ClientDto fetchClientById(Long id) {
        return invoiceClient.getClientById(id);
    }
    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
