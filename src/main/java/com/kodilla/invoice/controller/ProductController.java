package com.kodilla.invoice.controller;

import com.kodilla.invoice.domain.*;
import com.kodilla.invoice.facade.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductFacade productFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public List<Product> getProductsFromDb() {
        return productFacade.getAllProductsFromDb();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/products/{productId}")
    public Product getProductByIdFromDb(@PathVariable Long productId) {
        return productFacade.getProductFromDbById(productId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/products", consumes = APPLICATION_JSON_VALUE)
    public Product createProduct (@RequestBody ProductObjectDto productObjectDto) {
        return productFacade.createProduct(productObjectDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{productId}")
    public void deleteProduct (@PathVariable Long productId){
            productFacade.deletedById(productId);

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/products")
    public Product updateProduct (@RequestBody ProductDto productDto){
        return productFacade.updateProduct(productDto);
    }
}
