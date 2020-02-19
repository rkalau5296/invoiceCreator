package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.*;
import com.kodilla.invoice.mapper.ClientMapper;
import com.kodilla.invoice.mapper.ProductMapper;
import com.kodilla.invoice.service.*;
import com.kodilla.invoice.validator.ClientValidator;
import com.kodilla.invoice.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductObjectFacade {

    @Autowired
    private ProductDtoService productDtoService;
    @Autowired
    private ProductObjectService productObjectService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductValidator productValidator;

    public List<ProductDto> fetchProducts() {
        List<Product> products = productMapper.mapToListProducts(productDtoService.fetchProducts());
        List<Product> filteredInvoices = productValidator.validateProducts(products);
        List<ProductDto> filteredProductsDto = productMapper.mapToListProductDto(filteredInvoices);
        return filteredProductsDto;
    }

    public ProductDto fetchProductById(Long id) {
        return productDtoService.fetchProductById(id);
    }

    public CreatedProductDto createProduct(final ProductObjectDto productObjectDto) {
        return productObjectService.createProduct(productObjectDto);
    }


}
