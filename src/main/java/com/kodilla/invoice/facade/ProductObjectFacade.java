package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.*;
import com.kodilla.invoice.mapper.ProductMapper;
import com.kodilla.invoice.repository.ProductObjectRepository;
import com.kodilla.invoice.service.*;
import com.kodilla.invoice.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductObjectFacade {

    @Autowired
    private ProductObjectService productObjectService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductValidator productValidator;
    @Autowired
    private ProductObjectRepository productObjectRepository;

    public List<ProductDto> fetchProducts() {
        List<ProductDto> fetchedProducts = productObjectService.fetchProducts();
        for(ProductDto productDto :fetchedProducts) {
            productObjectRepository.save(productDto);
        }

        return productMapper.mapToListProductDto(productValidator.validateProducts(productMapper.mapToListProducts(fetchedProducts)));
    }

    public ProductDto fetchProductById(Long id) {
        productValidator.validateProductById(id);
        return productObjectService.fetchProductById(id);
    }

    public CreatedProductDto createProduct(final ProductObjectDto productObjectDto) {
        productValidator.validateCreatingProduct(productObjectDto);
        return productObjectService.createProduct(productObjectDto);
    }

    public void deletedById(Long id) {
        productValidator.validateDeletingProduct(id);
        productObjectService.deleteById(id);
    }

    public void updateProduct(UpdateProductDto productObjectDto, Long id) {
        productValidator.validateUpdateProduct(productObjectDto,id);
        productObjectService.updateProduct(productObjectDto, id);
    }
}
