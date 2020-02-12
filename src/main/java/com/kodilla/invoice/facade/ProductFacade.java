package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.domain.InvoiceDto;
import com.kodilla.invoice.domain.Product;
import com.kodilla.invoice.domain.ProductDto;
import com.kodilla.invoice.mapper.ProductMapper;
import com.kodilla.invoice.service.ProductDtoService;
import com.kodilla.invoice.service.ProductService;
import com.kodilla.invoice.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductFacade {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductDtoService productDtoService;
    @Autowired
    private ProductValidator productValidator;
    @Autowired
    private ProductService productService;

    public List<ProductDto> fetchProducts() {
        List<Product> products = productMapper.mapToListProducts(productDtoService.fetchProducts());
        List<Product> filteredInvoices = productValidator.validateProducts(products);
        List<ProductDto> filteredProductsDto = productMapper.mapToListProductDto(filteredInvoices);
        for (ProductDto productDto: filteredProductsDto)
        {
            productDtoService.saveProduct(productDto);
        }
        return filteredProductsDto;
    }

    public ProductDto fetchProductById (Long id) {
        return productDtoService.saveProduct(productDtoService.fetchProductById(id));
    }

    public void deletedById(Long id) {

        productDtoService.deleteById(id);
    }
    public ProductDto updateProduct(ProductDto productDto) {
        return productDtoService.saveProduct(productDto);
    }
    public Product createProduct(ProductDto productDto) {
        return productService.saveProduct(productMapper.mapToProduct(productDto));
    }
}
