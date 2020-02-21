package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.domain.InvoiceDto;
import com.kodilla.invoice.domain.InvoiceObjectDto;
import com.kodilla.invoice.domain.InvoicePosition;
import com.kodilla.invoice.mapper.InvoiceMapper;
import com.kodilla.invoice.service.InvoiceService;
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
public class InvoiceFacadeTest {
    @InjectMocks
    private InvoiceFacade invoiceFacade;
    @Mock
    private InvoiceService invoiceService;
    @Mock
    private InvoiceMapper invoiceMapper;

    @Test
    public void shouldGetAllInvoicesFromDb() {

        //Given
        List<InvoicePosition> positions = new ArrayList<>();
        positions.add(new InvoicePosition(1L,1,1));
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(new Invoice(1L, 1,1, positions ));

        when(invoiceService.getAllInvoicesFromDb()).thenReturn(invoices);
        //when
        List<Invoice> invoiceList = invoiceFacade.getAllInvoicesFromDb();
        //Then
        assertNotNull(invoiceList);
        assertEquals(1, invoiceList.size());
        assertEquals(1,invoiceList.get(0).getClient_id());
        assertEquals(1,invoiceList.get(0).getPayment_to_kind());
        assertEquals(1,invoiceList.get(0).getPositions().get(0).getProduct_id());
        assertEquals(1,invoiceList.get(0).getPositions().get(0).getQuantity());

    }
    @Test
    public void shouldGetInvoicesFromDbById() {

        //Given
        List<InvoicePosition> positions = new ArrayList<>();
        positions.add(new InvoicePosition(1L,1,1));
        Invoice invoice = new Invoice(1L, 1,1, positions );

        when(invoiceService.getInvoiceByIdFromDb(1L)).thenReturn(invoice);
        //when
        Invoice anotherInvoice = invoiceFacade.getInvoicesFromDbById(1L);
        //Then
        assertNotNull(anotherInvoice);

        assertEquals(1,anotherInvoice.getClient_id());
        assertEquals(1,anotherInvoice.getPayment_to_kind());
        assertEquals(1,anotherInvoice.getPositions().get(0).getProduct_id());
        assertEquals(1,anotherInvoice.getPositions().get(0).getQuantity());

    }

    @Test
    public void shouldCreateInvoice() {
        //Given
        List<InvoicePosition> positions = new ArrayList<>();
        positions.add(new InvoicePosition(1L,1,1));
        Invoice invoice = new Invoice(1L, 1,1, positions );
        InvoiceObjectDto invoiceObjectDto = new InvoiceObjectDto(1L, "api_token",invoice);

        when(invoiceMapper.mapToInvoiceFromInvoiceObjectDto(invoiceObjectDto)).thenReturn(invoice);
        when(invoiceService.saveInvoice(invoice)).thenReturn(invoice);

        //when
        Invoice anotherInvoice = invoiceFacade.createInvoice(invoiceObjectDto);

        //Then
        assertNotNull(anotherInvoice);

        assertEquals(1,anotherInvoice.getClient_id());
        assertEquals(1,anotherInvoice.getPayment_to_kind());
        assertEquals(1,anotherInvoice.getPositions().get(0).getProduct_id());
        assertEquals(1,anotherInvoice.getPositions().get(0).getQuantity());
    }

    @Test
    public void shouldUpdateInvoice() throws Exception {
        //Given
        List<InvoicePosition> positions = new ArrayList<>();
        positions.add(new InvoicePosition(1L,1,1));
        Invoice invoice = new Invoice(1L, 1,1, positions );
        List<InvoicePosition> positionsDto = new ArrayList<>();
        positions.add(new InvoicePosition(1L,1,1));
        InvoiceDto invoiceDto = new InvoiceDto(1L, 1,1, positionsDto );

        when(invoiceMapper.mapToInvoice(invoiceDto)).thenReturn(invoice);
        when(invoiceService.saveInvoice(invoice)).thenReturn(invoice);

        //when
        Invoice anotherInvoice = invoiceFacade.updateInvoice(invoiceDto);
        //Then
        assertNotNull(anotherInvoice);

        assertEquals(1,anotherInvoice.getClient_id());
        assertEquals(1,anotherInvoice.getPayment_to_kind());
        assertEquals(1,anotherInvoice.getPositions().get(0).getProduct_id());
        assertEquals(1,anotherInvoice.getPositions().get(0).getQuantity());

    }
}
