package com.kodilla.invoice.service;

import com.kodilla.invoice.client.InvoiceClient;
import com.kodilla.invoice.domain.InvoiceDto;
import com.kodilla.invoice.domain.ProductDto;
import com.kodilla.invoice.repository.InvoiceDtoRepository;
import com.kodilla.invoice.repository.ProductDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDtoService {
    @Autowired
    private ProductDtoRepository repository;
    @Autowired
    private InvoiceClient invoiceClient;

    public List<ProductDto> fetchProducts() {
        return invoiceClient.getProducts();
    }
    public ProductDto fetchProductById(Long id) {
        return invoiceClient.getProductById(id);
    }
    public List<ProductDto> getAllInvoices() {
        return repository.findAll();
    }

    public InvoiceDto getInvoiceById(Long id){
        return invoiceClient.getInvoicesById(id);
    }

    public ProductDto saveProduct(final ProductDto productDto) {
        return repository.save(productDto);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
