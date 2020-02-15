package com.kodilla.invoice.controller;

import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.domain.Product;
import com.kodilla.invoice.domain.ProductDto;
import com.kodilla.invoice.facade.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductFacade productFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public List<ProductDto> getProducts() {
        return productFacade.fetchProducts();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/products/{productId}")
    public ProductDto getProduct (@PathVariable Long productId) {
        return productFacade.fetchProductById(productId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{productId}")
    public void deleteProduct (@PathVariable Long productId){
        productFacade.deletedById(productId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/products")
    public ProductDto updateProduct (@RequestBody ProductDto productDto){
        return productFacade.updateProduct(productDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/products", consumes = APPLICATION_JSON_VALUE)
    public Product createProduct (@RequestBody ProductDto productDto) {
        return productFacade.createProduct(productDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/productsDb")
    public List<Product> getProductsFromDb() {
        return productFacade.getAllProductsFromDb();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/productsDb/{productId}")
    public Product getProductByIdFromDb(@PathVariable Long productId) {
        return productFacade.getProductFromDbById(productId);
    }
}
