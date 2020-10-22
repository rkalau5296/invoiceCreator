package com.kodilla.invoice.service;

import com.kodilla.invoice.client.InvoiceClient;
import com.kodilla.invoice.domain.CreatedProductDto;
import com.kodilla.invoice.domain.ProductDto;
import com.kodilla.invoice.domain.ProductObjectDto;
import com.kodilla.invoice.repository.ProductDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductDtoService {

    @Autowired
    private ProductDtoRepository productDtoRepository;
    @Autowired
    private InvoiceClient invoiceClient;

    public List<ProductDto> fetchProducts() {
        return invoiceClient.getProducts();
    }

    public ProductDto fetchProductById(Long id) {
        return invoiceClient.getProductById(id);
    }

    public ProductDto saveProductDto(final ProductDto productDto) {
        return productDtoRepository.save(productDto);
    }

    public void deleteById(Long id) {
        productDtoRepository.deleteById(id);
    }


}
