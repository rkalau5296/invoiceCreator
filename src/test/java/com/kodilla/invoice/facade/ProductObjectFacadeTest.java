package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.*;
import com.kodilla.invoice.mapper.ProductMapper;
import com.kodilla.invoice.repository.ProductObjectRepository;
import com.kodilla.invoice.service.ProductObjectService;
import com.kodilla.invoice.validator.ProductValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductObjectFacadeTest {

    final double DELTA = 1e-15;
    @InjectMocks
    private ProductObjectFacade productObjectFacade;
    @Mock
    private ProductObjectService productObjectService;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private ProductValidator productValidator;
    @Mock
    private ProductObjectRepository productObjectRepository;
    @Test
    public void shouldFetchProducts() throws Exception {

        //Given
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, "name", "code", 10.00, "tax"));
        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(new ProductDto(1L, "name", "code", 10.00, "tax"));

        when(productObjectService.fetchProducts()).thenReturn(productDtoList);
        when(productMapper.mapToListProductDto(productList)).thenReturn(productDtoList);
        when(productMapper.mapToListProducts(productDtoList)).thenReturn(productList);
        when(productValidator.validateProducts(productList)).thenReturn(productList);
        for(ProductDto productDto : productDtoList) {
            when(productObjectRepository.save(productDto)).thenReturn(productDto);
        }
        //When
        List<ProductDto> productsDto = productObjectFacade.fetchProducts();

        //Then
        assertNotNull(productsDto);
        assertEquals(1, productsDto.size());
        assertEquals("name", productsDto.get(0).getName());
        assertEquals("code", productsDto.get(0).getCode());
        assertEquals(10.00, productsDto.get(0).getPrice_net(), DELTA);
        assertEquals("tax", productsDto.get(0).getTax());
    }
    @Test
    public void shouldFetchProductById() throws Exception {
        //Given
        ProductDto productDto = new ProductDto(1L, "name", "code", 10.00, "tax");

        when(productObjectService.fetchProductById(1L)).thenReturn(productDto);

        //When
        ProductDto anotherProductDto = productObjectFacade.fetchProductById(1L);

        //Then
        assertNotNull(anotherProductDto);
        assertEquals("name", anotherProductDto.getName());
        assertEquals("code", anotherProductDto.getCode());
        assertEquals(10.00, anotherProductDto.getPrice_net(), DELTA);
        assertEquals("tax", anotherProductDto.getTax());
    }
    @Test
    public void shouldCreateProduct() throws Exception {
        //Given
        Product product = new Product(1L, "name", "code", 10.00, "tax");
        ProductObjectDto productObjectDto = new ProductObjectDto(1l, "api_token", product);
        CreatedProductDto createdProductDto = new CreatedProductDto(1L, "name", "code", 10.00, "tax");
        when(productObjectService.createProduct(productObjectDto)).thenReturn(createdProductDto);

        //When
        CreatedProductDto productDto = productObjectFacade.createProduct(productObjectDto);

        //Then
        assertNotNull(productDto);
        assertEquals("name", productDto.getName());
        assertEquals("code", productDto.getCode());
        assertEquals(10.00, productDto.getPrice_net(), DELTA);
        assertEquals("tax", productDto.getTax());
    }
}
