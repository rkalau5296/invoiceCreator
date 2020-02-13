package com.kodilla.invoice.facade;

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
        List<Product> filteredProducts = productValidator.validateProducts(products);
        List<ProductDto> filteredProductsDto = productMapper.mapToListProductDto(filteredProducts);
        for (ProductDto productDto : filteredProductsDto)
        {
            productDtoService.saveProductDto(productDto);
        }
        return filteredProductsDto;
    }

    public ProductDto fetchProductById (Long id) {
        return productDtoService.saveProductDto(productDtoService.fetchProductById(id));
    }

    public void deletedById(Long id) {

        productDtoService.deleteById(id);
    }
    public ProductDto updateProduct(ProductDto productDto) {
        return productDtoService.saveProductDto(productDto);
    }
    public Product createProduct(ProductDto productDto) {
        return productService.saveProduct(productMapper.mapToProduct(productDto));
    }
}
