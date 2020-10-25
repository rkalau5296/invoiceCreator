package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.*;
import com.kodilla.invoice.mapper.ProductMapper;
import com.kodilla.invoice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductFacade {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductService productService;

    public void deletedById(Long id) {
        productService.deleteById(id);
    }
    public Product updateProduct(ProductDto productDto) {
        return productService.save(productMapper.mapToProduct(productDto));
    }
    public Product createProduct (ProductObjectDto productObjectDto) {
        return productService.save(productMapper.mapToProductFromProductObjectDto(productObjectDto));
    }
    public List<Product> getAllProductsFromDb(){
        return productService.getAllProductsFromDb();
    }
    public Product getProductFromDbById(Long id){
        return productService.getProductByIdFromDb(id);
    }
}
