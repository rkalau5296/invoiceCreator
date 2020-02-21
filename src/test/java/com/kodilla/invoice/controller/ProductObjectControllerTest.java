package com.kodilla.invoice.controller;

import com.google.gson.Gson;
import com.kodilla.invoice.domain.*;
import com.kodilla.invoice.facade.ProductObjectFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductObjectController.class)
public class ProductObjectControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductObjectFacade productObjectFacade;

    @Test
    public void shouldGetProducts() throws Exception {
        //Given
        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(new ProductDto(1L, "name", "code", 100.00, "tax"));
        when(productObjectFacade.fetchProducts()).thenReturn(productDtoList);

        //When & Then
        mockMvc.perform(get("/v1/productObject/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].code", is("code")))
                .andExpect(jsonPath("$[0].price_net", is(100.00)))
                .andExpect(jsonPath("$[0].tax", is("tax")));
    }

    @Test
    public void shouldGetProduct() throws Exception {
        //Given
        ProductDto productDto = new ProductDto(1L, "name", "code", 100.00, "tax");
        when(productObjectFacade.fetchProductById(ArgumentMatchers.any(Long.class))).thenReturn(productDto);
        //When & Then
        mockMvc.perform(get("/v1/productObject/products/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.code", is("code")))
                .andExpect(jsonPath("$.price_net", is(100.00)))
                .andExpect(jsonPath("$.tax", is("tax")));
    }

    @Test
    public void shouldCreateProduct() throws Exception {
        //Given
        CreatedProductDto createdProductDto = new CreatedProductDto(1L, "name", "code", 100.00, "tax");
        when(productObjectFacade.createProduct(ArgumentMatchers.any(ProductObjectDto.class))).thenReturn(createdProductDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(createdProductDto);

        //When & Then
        mockMvc.perform(post("/v1/productObject/products")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.code", is("code")))
                .andExpect(jsonPath("$.price_net", is(100.00)))
                .andExpect(jsonPath("$.tax", is("tax")));
    }
}
