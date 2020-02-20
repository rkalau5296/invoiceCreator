package com.kodilla.invoice.controller;

import com.google.gson.Gson;
import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.ClientDto;
import com.kodilla.invoice.domain.CreatedCustomerDto;
import com.kodilla.invoice.domain.CustomerDto;
import com.kodilla.invoice.facade.CustomerFacade;
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
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerFacade customerFacade;

    @Test
    public void shouldGetClients() throws Exception {

        //Given
        ClientDto clientDto = new ClientDto(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no");
        List<ClientDto> clientDtoList = new ArrayList<>();
        clientDtoList.add(clientDto);
        when(customerFacade.fetchClients()).thenReturn(clientDtoList);

        //When & Then
        mockMvc.perform(get("/v1/customers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("name")))
                .andExpect(jsonPath("$[0].tax_no", is("tax_no")))
                .andExpect(jsonPath("$[0].bank", is("bank")))
                .andExpect(jsonPath("$[0].bank_account", is("bank_account")))
                .andExpect(jsonPath("$[0].city", is("city")))
                .andExpect(jsonPath("$[0].country", is("country")))
                .andExpect(jsonPath("$[0].email", is("email")))
                .andExpect(jsonPath("$[0].person", is("person")))
                .andExpect(jsonPath("$[0].post_code", is("post_code")))
                .andExpect(jsonPath("$[0].phone", is("phone")))
                .andExpect(jsonPath("$[0].street", is("street")))
                .andExpect(jsonPath("$[0].street_no", is("street_no")));
    }
    @Test
    public void shouldGetCustomerByIdFromDb() throws Exception{

        //Given
        ClientDto clientDto = new ClientDto(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no");
        when(customerFacade.fetchClientById(ArgumentMatchers.any(Long.class))).thenReturn(clientDto);

        // When&Then
        mockMvc.perform(get("/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.tax_no", is("tax_no")))
                .andExpect(jsonPath("$.bank", is("bank")))
                .andExpect(jsonPath("$.bank_account", is("bank_account")))
                .andExpect(jsonPath("$.city", is("city")))
                .andExpect(jsonPath("$.country", is("country")))
                .andExpect(jsonPath("$.email", is("email")))
                .andExpect(jsonPath("$.person", is("person")))
                .andExpect(jsonPath("$.post_code", is("post_code")))
                .andExpect(jsonPath("$.phone", is("phone")))
                .andExpect(jsonPath("$.street", is("street")))
                .andExpect(jsonPath("$.street_no", is("street_no")));;
    }
    @Test
    public void shouldCreateCustomer() throws Exception {

        //Given
        CreatedCustomerDto createdCustomerDto = new CreatedCustomerDto(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no");
        when(customerFacade.createCustomer(ArgumentMatchers.any(CustomerDto.class))).thenReturn(createdCustomerDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(createdCustomerDto);

        // When&Then
        mockMvc.perform(post("/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.tax_no", is("tax_no")))
                .andExpect(jsonPath("$.bank", is("bank")))
                .andExpect(jsonPath("$.bank_account", is("bank_account")))
                .andExpect(jsonPath("$.city", is("city")))
                .andExpect(jsonPath("$.country", is("country")))
                .andExpect(jsonPath("$.email", is("email")))
                .andExpect(jsonPath("$.person", is("person")))
                .andExpect(jsonPath("$.post_code", is("post_code")))
                .andExpect(jsonPath("$.phone", is("phone")))
                .andExpect(jsonPath("$.street", is("street")))
                .andExpect(jsonPath("$.street_no", is("street_no")));;
    }

}
