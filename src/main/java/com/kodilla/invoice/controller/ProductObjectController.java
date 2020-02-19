package com.kodilla.invoice.controller;

import com.kodilla.invoice.domain.*;
import com.kodilla.invoice.facade.CustomerFacade;
import com.kodilla.invoice.facade.ProductObjectFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/productObject")
@CrossOrigin("*")
public class ProductObjectController {
    @Autowired
    private ProductObjectFacade productObjectFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public List<ProductDto> getProducts() {
        return productObjectFacade.fetchProducts();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/products/{productId}")
    public ProductDto getProduct (@PathVariable Long productId) {
        return productObjectFacade.fetchProductById(productId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/products")
    public CreatedProductDto createProduct (@RequestBody ProductObjectDto productObjectDto) {
        return productObjectFacade.createProduct(productObjectDto);
    }
}
