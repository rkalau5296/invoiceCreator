package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.*;
import com.kodilla.invoice.mapper.ProductMapper;
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
    private ProductService productService;
    @Autowired
    private ProductValidator productValidator;

    public List<Product> getAllProductsFromDb(){
        List<Product> products = productService.getAllProductsFromDb();
        productValidator.validateProducts(products);
        return products;
    }
    public Product getProductFromDbById(Long id){
        productValidator.validateProductById(id);
        return productService.getProductByIdFromDb(id);
    }
    public Product createProduct (ProductObjectDto productObjectDto) {
        productValidator.validateCreatingProduct(productObjectDto);
        return productService.save(productMapper.mapToProductFromProductObjectDto(productObjectDto));
    }
    public Product updateProduct(ProductDto productDto) {
        productValidator.validateUpdating(productDto);
        return productService.update(productMapper.mapToProduct(productDto));
    }
    public void deletedById(Long id) {
        productValidator.validateDeletingProduct(id);
        productService.deleteById(id);
    }

}
