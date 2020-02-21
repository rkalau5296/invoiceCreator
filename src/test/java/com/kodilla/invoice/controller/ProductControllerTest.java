package com.kodilla.invoice.controller;

import com.google.gson.Gson;
import com.kodilla.invoice.domain.Product;
import com.kodilla.invoice.domain.ProductDto;
import com.kodilla.invoice.domain.ProductObjectDto;
import com.kodilla.invoice.facade.ProductFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductFacade productFacade;

    @Test
    public void shouldGetProductsFromDb() throws Exception {
        //Given
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "name", "code", 100.00, "tax"));

        when(productFacade.getAllProductsFromDb()).thenReturn(products);

        //When & Then
        mockMvc.perform(get("/v1/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                //invoices fields
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].code", is("code")))
                .andExpect(jsonPath("$[0].price_net", is(100.00)))
                .andExpect(jsonPath("$[0].tax", is("tax")));

    }

    @Test
    public void shouldGetProductByIdFromDb() throws Exception {
        //Given
        Product product = new Product(1L, "name", "code", 100.00, "tax");
        when(productFacade.getProductFromDbById(ArgumentMatchers.any(Long.class))).thenReturn(product);

        //When & Then
        mockMvc.perform(get("/v1/products/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                //invoices fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.code", is("code")))
                .andExpect(jsonPath("$.price_net", is(100.00)))
                .andExpect(jsonPath("$.tax", is("tax")));
    }

    @Test
    public void shouldCreateProduct() throws Exception {
        //Given
        Product product = new Product(1L, "name", "code", 100.00, "tax");
        when(productFacade.createProduct(ArgumentMatchers.any(ProductObjectDto.class))).thenReturn(product);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(product);

        // When&Then
        mockMvc.perform(post("/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                //invoice fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.code", is("code")))
                .andExpect(jsonPath("$.price_net", is(100.00)))
                .andExpect(jsonPath("$.tax", is("tax")));
    }

    @Test
    public void shouldDeleteProduct() throws Exception {

        //Given & When & Then
        mockMvc.perform(delete("/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(productFacade,  times(1)).deletedById(any());
    }

    @Test
    public void shouldUpdateProduct() throws Exception {
        //Given
        Product product = new Product(1L, "name", "code", 100.00, "tax");
        when(productFacade.updateProduct(ArgumentMatchers.any(ProductDto.class))).thenReturn(product);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(product);

        // When&Then
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                //invoice fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.code", is("code")))
                .andExpect(jsonPath("$.price_net", is(100.00)))
                .andExpect(jsonPath("$.tax", is("tax")));
    }
}
