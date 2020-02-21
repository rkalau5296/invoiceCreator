package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.Product;
import com.kodilla.invoice.domain.ProductDto;
import com.kodilla.invoice.domain.ProductObjectDto;
import com.kodilla.invoice.mapper.ProductMapper;
import com.kodilla.invoice.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductFacadeTest {

    final double DELTA = 1e-15;
    @InjectMocks
    private ProductFacade productFacade;
    @Mock
    private ProductService productService;
    @Mock
    private ProductMapper productMapper;

    @Test
    public void shouldGetAllProductsFromDb() throws Exception {
        //Given
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, "name", "code", 10.00, "tax"));

        when(productService.getAllProductsFromDb()).thenReturn(productList);

        //When
        List<Product> products = productFacade.getAllProductsFromDb();

        //Then
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("name", products.get(0).getName());
        assertEquals("code", products.get(0).getCode());
        assertEquals(10.00, products.get(0).getPrice_net(), DELTA);
        assertEquals("tax", products.get(0).getTax());
    }
    @Test
    public void shouldGetProductFromDbById() throws Exception {
        //Given
        Product product = new Product(1L, "name", "code", 10.00, "tax");

        when(productService.getProductByIdFromDb(1L)).thenReturn(product);
        //When
        Product anotherProduct = productFacade.getProductFromDbById(1L);

        //Then

        assertEquals("name", anotherProduct.getName());
        assertEquals("code", anotherProduct.getCode());
        assertEquals(10.00, anotherProduct.getPrice_net(), DELTA);
        assertEquals("tax", anotherProduct.getTax());
    }
    @Test
    public void shouldCreateProduct() throws Exception {
        //Given
        Product product = new Product(1L, "name", "code", 10.00, "tax");
        ProductObjectDto productObjectDto = new ProductObjectDto(1L, "api_token", product);

        when(productMapper.mapToProductFromProductObjectDto(productObjectDto)).thenReturn(product);
        when(productService.save(product)).thenReturn(product);

        //When
        Product anotherProduct = productFacade.createProduct(productObjectDto);

        //Then

        assertEquals("name", anotherProduct.getName());
        assertEquals("code", anotherProduct.getCode());
        assertEquals(10.00, anotherProduct.getPrice_net(), DELTA);
        assertEquals("tax", anotherProduct.getTax());
    }
    @Test
    public void shouldUpdateProduct() throws Exception {
        //Given
        Product product = new Product(1L, "name", "code", 10.00, "tax");
        ProductDto productDto = new ProductDto(1L, "name", "code", 10.00, "tax");

        when(productMapper.mapToProduct(productDto)).thenReturn(product);
        when(productService.save(product)).thenReturn(product);

        //When
        Product anotherProduct = productFacade.updateProduct(productDto);

        //Then

        assertEquals("name", anotherProduct.getName());
        assertEquals("code", anotherProduct.getCode());
        assertEquals(10.00, anotherProduct.getPrice_net(), DELTA);
        assertEquals("tax", anotherProduct.getTax());
    }
}
