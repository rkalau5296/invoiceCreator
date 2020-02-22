package com.kodilla.invoice.mapper;

import com.kodilla.invoice.domain.Product;
import com.kodilla.invoice.domain.ProductDto;
import com.kodilla.invoice.domain.ProductObjectDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductMapperTest {
    final double DELTA = 1e-15;
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void mapToListProductsTest() {
        //Given
        List<ProductDto> productDto = new ArrayList<>();
        productDto.add(new ProductDto(1L, "name", "code", 10.00, "tax"));
        //When
        List<Product> products = productMapper.mapToListProducts(productDto);
        //Then
        assertEquals(productDto.get(0).getId(), products.get(0).getId());
        assertEquals(productDto.get(0).getName(), products.get(0).getName());
        assertEquals(productDto.get(0).getCode(), products.get(0).getCode());
        assertEquals(productDto.get(0).getPrice_net(), products.get(0).getPrice_net(), DELTA);
        assertEquals(productDto.get(0).getTax(), products.get(0).getTax());
    }
    @Test
    public void mapToListProductDtoTest() {
        //Given
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "name", "code", 10.00, "tax"));
        //When
        List<ProductDto> productDtoList = productMapper.mapToListProductDto(products);
        //Then
        assertEquals(products.get(0).getId(), productDtoList.get(0).getId());
        assertEquals(products.get(0).getName(), productDtoList.get(0).getName());
        assertEquals(products.get(0).getCode(), productDtoList.get(0).getCode());
        assertEquals(products.get(0).getPrice_net(), productDtoList.get(0).getPrice_net(), DELTA);
        assertEquals(products.get(0).getTax(), productDtoList.get(0).getTax());
    }
    @Test
    public void mapToProductTest() {
        //Given
        ProductDto productDto = new ProductDto(1L, "name", "code", 10.00, "tax");
        //When
        Product product = productMapper.mapToProduct(productDto);
        //Then
        assertEquals(productDto.getId(), product.getId());
        assertEquals(productDto.getName(), product.getName());
        assertEquals(productDto.getCode(), product.getCode());
        assertEquals(productDto.getPrice_net(), product.getPrice_net(), DELTA);
        assertEquals(productDto.getTax(), product.getTax());
    }
    @Test
    public void mapToProductFromProductObjectDtoTest() {
        //Given
        Product product = new Product(1L, "name", "code", 10.00, "tax");
        ProductObjectDto productObjectDto = new ProductObjectDto(1L, "api_token", product);
        //When
        Product anotherProduct = productMapper.mapToProductFromProductObjectDto(productObjectDto);
        //Then
        assertEquals(productObjectDto.getProduct().getId(), anotherProduct.getId());
        assertEquals(productObjectDto.getProduct().getName(), anotherProduct.getName());
        assertEquals(productObjectDto.getProduct().getCode(), anotherProduct.getCode());
        assertEquals(productObjectDto.getProduct().getPrice_net(), anotherProduct.getPrice_net(), DELTA);
        assertEquals(productObjectDto.getProduct().getTax(), anotherProduct.getTax());
    }
}
