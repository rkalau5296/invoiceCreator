package com.kodilla.invoice.controller;

import com.google.gson.Gson;
import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.domain.InvoiceDto;
import com.kodilla.invoice.domain.InvoiceObjectDto;
import com.kodilla.invoice.domain.InvoicePosition;
import com.kodilla.invoice.facade.InvoiceFacade;
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
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private InvoiceFacade invoiceFacade;

    @Test
    public void shouldGetInvoicesFromDb() throws Exception {
        //Given
        List<InvoicePosition> positions = new ArrayList<>();
        positions.add(new InvoicePosition(1L,1,1));
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(new Invoice(1L, 5, 5, positions));

        when(invoiceFacade.getAllInvoicesFromDb()).thenReturn(invoices);

        //When & Then
        mockMvc.perform(get("/v1/invoices").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                //invoices fields
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].payment_to_kind", is(5)))
                .andExpect(jsonPath("$[0].client_id", is(5)))
                //Invoice position
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].positions[0].id", is(1)))
                .andExpect(jsonPath("$[0].positions[0].product_id", is(1)))
                .andExpect(jsonPath("$[0].positions[0].quantity", is(1)));
    }

    @Test
    public void shouldGetInvoiceByIdFromDb() throws Exception {
        //Given
        List<InvoicePosition> positions = new ArrayList<>();
        positions.add(new InvoicePosition(1L,1,1));
        Invoice invoice = new Invoice(1L, 5, 5, positions);

        when(invoiceFacade.getInvoicesFromDbById(ArgumentMatchers.any(Long.class))).thenReturn(invoice);

        // When&Then
        mockMvc.perform(get("/v1/invoices/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                //invoice fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.payment_to_kind", is(5)))
                .andExpect(jsonPath("$.client_id", is(5)))
                //Invoice position
                .andExpect(jsonPath("$.positions", hasSize(1)))
                .andExpect(jsonPath("$.positions[0].id", is(1)))
                .andExpect(jsonPath("$.positions[0].product_id", is(1)))
                .andExpect(jsonPath("$.positions[0].quantity", is(1)));

    }
    @Test
    public void shouldCreateInvoice() throws Exception {

        //Given
        List<InvoicePosition> positions = new ArrayList<>();
        positions.add(new InvoicePosition(1L,1,1));
        Invoice invoice = new Invoice(1L, 5, 5, positions);

        when(invoiceFacade.createInvoice(ArgumentMatchers.any(InvoiceObjectDto.class))).thenReturn(invoice);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(invoice);

        // When&Then
        mockMvc.perform(post("/v1/invoices")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                //invoice fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.payment_to_kind", is(5)))
                .andExpect(jsonPath("$.client_id", is(5)))
                //Invoice position
                .andExpect(jsonPath("$.positions", hasSize(1)))
                .andExpect(jsonPath("$.positions[0].id", is(1)))
                .andExpect(jsonPath("$.positions[0].product_id", is(1)))
                .andExpect(jsonPath("$.positions[0].quantity", is(1)));
    }

    @Test
    public void shouldUpdateInvoice() throws Exception{

        //Given
        List<InvoicePosition> positions = new ArrayList<>();
        positions.add(new InvoicePosition(1L,1,1));
        Invoice invoice = new Invoice(1L, 5, 5, positions);

        when(invoiceFacade.updateInvoice(ArgumentMatchers.any(InvoiceDto.class))).thenReturn(invoice);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(invoice);
        // When&Then
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/invoices")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                //invoice fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.payment_to_kind", is(5)))
                .andExpect(jsonPath("$.client_id", is(5)))
                //Invoice position
                .andExpect(jsonPath("$.positions", hasSize(1)))
                .andExpect(jsonPath("$.positions[0].id", is(1)))
                .andExpect(jsonPath("$.positions[0].product_id", is(1)))
                .andExpect(jsonPath("$.positions[0].quantity", is(1)));
    }

    @Test
    public void shouldDeleteInvoice() throws Exception {

        //Given & When & Then
        mockMvc.perform(delete("/v1/invoices/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(invoiceFacade,  times(1)).deletedById(any());
    }
}
