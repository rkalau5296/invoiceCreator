package com.kodilla.invoice.controller;

import com.google.gson.Gson;
import com.kodilla.invoice.domain.CreatedInvoiceDto;
import com.kodilla.invoice.domain.InvoiceObjectDto;
import com.kodilla.invoice.facade.InvoiceObjectFacade;
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
@WebMvcTest(InvoiceObjectController.class)
public class InvoiceObjectControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private InvoiceObjectFacade invoiceObjectFacade;

    @Test
    public void shouldGetInvoices() throws Exception{
        //Given
        List<CreatedInvoiceDto> createdInvoiceDtoList = new ArrayList<>();
        createdInvoiceDtoList.add(new CreatedInvoiceDto(1L, "price_net","price_gross","buyer_name","product_cache"));

        when(invoiceObjectFacade.fetchInvoices()).thenReturn(createdInvoiceDtoList);

        //When & Then
        mockMvc.perform(get("/v1/invoiceObject/invoices").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].price_net", is("price_net")))
                .andExpect(jsonPath("$[0].price_gross", is("price_gross")))
                .andExpect(jsonPath("$[0].buyer_name", is("buyer_name")))
                .andExpect(jsonPath("$[0].product_cache", is("product_cache")));
    }

    @Test
    public void shouldGetInvoice() throws Exception {
        //Given
        CreatedInvoiceDto createdInvoiceDto = new CreatedInvoiceDto(1L, "price_net","price_gross","buyer_name","product_cache");
        when(invoiceObjectFacade.fetchProductById(ArgumentMatchers.any(Long.class))).thenReturn(createdInvoiceDto);

        //When & Then
        mockMvc.perform(get("/v1/invoiceObject/invoices/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.price_net", is("price_net")))
                .andExpect(jsonPath("$.price_gross", is("price_gross")))
                .andExpect(jsonPath("$.buyer_name", is("buyer_name")))
                .andExpect(jsonPath("$.product_cache", is("product_cache")));
    }

    @Test
    public void shouldCreateInvoice() throws Exception {
        //Given
        CreatedInvoiceDto createdInvoiceDto = new CreatedInvoiceDto(1L, "price_net","price_gross","buyer_name","product_cache");
        when(invoiceObjectFacade.createInvoice(ArgumentMatchers.any(InvoiceObjectDto.class))).thenReturn(createdInvoiceDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(createdInvoiceDto);

        //When & Then
        mockMvc.perform(post("/v1/invoiceObject/invoices")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.price_net", is("price_net")))
                .andExpect(jsonPath("$.price_gross", is("price_gross")))
                .andExpect(jsonPath("$.buyer_name", is("buyer_name")))
                .andExpect(jsonPath("$.product_cache", is("product_cache")));
    }

}
