package com.kodilla.invoice.controller;

import com.google.gson.Gson;
import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.ClientDto;
import com.kodilla.invoice.domain.CustomerDto;
import com.kodilla.invoice.facade.ClientFacade;
import com.kodilla.invoice.service.ClientService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClientFacade clientFacade;
    @MockBean
    private ClientService clientService;
    @Test
    public void shouldFetchEmptyClientList() throws Exception {
        //Given
        List<Client> clientList = new ArrayList<>();
        when(clientFacade.getAllClientsFromDb()).thenReturn(clientList);

        //When & Then
        mockMvc.perform(get("/v1/clients").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }
    @Test
    public void shouldFetchClientList() throws Exception {
        //Given
        List<Client> clientList = new ArrayList<>();
        clientList.add(new Client(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no"));
        when(clientFacade.getAllClientsFromDb()).thenReturn(clientList);

        //When & Then
        mockMvc.perform(get("/v1/clients").contentType(MediaType.APPLICATION_JSON))
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
    public void shouldGetClientByIdFromDb() throws Exception{

        //Given
        Client client = new Client(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no");
        when(clientFacade.getClientFromDbById(ArgumentMatchers.any(Long.class))).thenReturn(client);


        // When&Then
        mockMvc.perform(get("/v1/clients/1")
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
    public void shouldCreateClient() throws Exception {

        //Given
        Client client = new Client(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no");

        when(clientFacade.createClient(ArgumentMatchers.any(CustomerDto.class))).thenReturn(client);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(client);

        // When&Then
        mockMvc.perform(post("/v1/clients")
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
    @Test
    public void shouldUpdateClient() throws Exception {
        //Given
        Client client = new Client(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no");
        when(clientFacade.updateClient(ArgumentMatchers.any(ClientDto.class))).thenReturn(client);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(client);
        // When&Then
        mockMvc.perform(put("/v1/clients")
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
    @Test
    public void shouldDeleteClient() throws Exception {
        //Given & When & Then
        Client client = new Client(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no");
        mockMvc.perform(delete("/v1/clients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(clientFacade,  times(1)).deletedById(any());
    }
}
