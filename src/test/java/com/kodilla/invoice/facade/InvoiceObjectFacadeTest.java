package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.CreatedInvoiceDto;
import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.domain.InvoiceObjectDto;
import com.kodilla.invoice.domain.InvoicePosition;
import com.kodilla.invoice.service.InvoiceDtoService;
import com.kodilla.invoice.service.InvoiceObjectService;
import com.kodilla.invoice.validator.InvoiceValidator;
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
public class InvoiceObjectFacadeTest {
    @InjectMocks
    private InvoiceObjectFacade invoiceObjectFacade;
    @Mock
    private InvoiceDtoService invoiceDtoService;
    @Mock
    private InvoiceObjectService invoiceObjectService;
    @Mock
    private InvoiceValidator invoiceValidator;
    @Test
    public void shouldFetchInvoices() throws Exception {
        //Given
        List<CreatedInvoiceDto> createdInvoiceDtos = new ArrayList<>();
        createdInvoiceDtos.add(new CreatedInvoiceDto(1L, "price_net", "price_gross", "buyer_name", "product_cache"));

        when(invoiceDtoService.fetchInvoices()).thenReturn(createdInvoiceDtos);
        when(invoiceValidator.validateInvoices(createdInvoiceDtos)).thenReturn(createdInvoiceDtos);

        //When
        List<CreatedInvoiceDto> createdInvoiceDtoList = invoiceObjectFacade.fetchInvoices();

        assertNotNull(createdInvoiceDtoList);
        assertEquals(1, createdInvoiceDtoList.size());
        assertEquals("price_net", createdInvoiceDtoList.get(0).getPrice_net());
        assertEquals("price_gross", createdInvoiceDtoList.get(0).getPrice_gross());
        assertEquals("buyer_name", createdInvoiceDtoList.get(0).getBuyer_name());
        assertEquals("product_cache", createdInvoiceDtoList.get(0).getProduct_cache());
    }

    @Test
    public void shouldFetchInvoiceById() throws Exception{
        //Given
        CreatedInvoiceDto createdInvoiceDto = new CreatedInvoiceDto(1L, "price_net", "price_gross", "buyer_name", "product_cache");

        when(invoiceDtoService.fetchInvoiceById(1L)).thenReturn(createdInvoiceDto);

        //When
        CreatedInvoiceDto invoiceDto = invoiceObjectFacade.fetchInvoiceById(1L);
        //Then
        assertNotNull(invoiceDto);

        assertEquals("price_net", invoiceDto.getPrice_net());
        assertEquals("price_gross", invoiceDto.getPrice_gross());
        assertEquals("buyer_name", invoiceDto.getBuyer_name());
        assertEquals("product_cache", invoiceDto.getProduct_cache());

    }
    @Test
    public void shouldCreateInvoice() throws Exception {
        //Given
        CreatedInvoiceDto createdInvoiceDto = new CreatedInvoiceDto(1L, "price_net", "price_gross", "buyer_name", "product_cache");

        List<InvoicePosition> positions = new ArrayList<>();
        positions.add(new InvoicePosition(1L,1,1));
        Invoice invoice = new Invoice(1L, 1,1, positions );
        InvoiceObjectDto invoiceObjectDto = new InvoiceObjectDto(1L, "api_token", invoice);

        when(invoiceObjectService.createInvoice(invoiceObjectDto)).thenReturn(createdInvoiceDto);
        //When

        CreatedInvoiceDto anotherCreatedInvoiceDto = invoiceObjectFacade.createInvoice(invoiceObjectDto);
        //Then
        assertNotNull(anotherCreatedInvoiceDto);

        assertEquals("price_net", anotherCreatedInvoiceDto.getPrice_net());
        assertEquals("price_gross", anotherCreatedInvoiceDto.getPrice_gross());
        assertEquals("buyer_name", anotherCreatedInvoiceDto.getBuyer_name());
        assertEquals("product_cache", anotherCreatedInvoiceDto.getProduct_cache());
    }
}
