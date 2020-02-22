package com.kodilla.invoice.mapper;

import com.kodilla.invoice.domain.*;
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
public class InvoiceMapperTest {

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Test
    public void mapToInvoiceTest() {
        //Given
        List<InvoicePosition> positions = new ArrayList<>();
        positions.add(new InvoicePosition(1L, 1,1));
        InvoiceDto invoiceDto = new InvoiceDto(1L,1,1, positions);
        //When
        Invoice clientList = invoiceMapper.mapToInvoice(invoiceDto);
        //Then
        assertEquals(invoiceDto.getId(), clientList.getId());
        assertEquals(invoiceDto.getPayment_to_kind(), clientList.getClient_id());
        assertEquals(invoiceDto.getClient_id(), clientList.getClient_id());
        assertEquals(invoiceDto.getPositions().get(0).getId(), clientList.getPositions().get(0).getId());
        assertEquals(invoiceDto.getPositions().get(0).getProduct_id(), clientList.getPositions().get(0).getProduct_id());
        assertEquals(invoiceDto.getPositions().get(0).getQuantity(), clientList.getPositions().get(0).getQuantity());
    }
    @Test
    public void mapToInvoiceFromInvoiceObjectDtoTest() {
        //Given
        List<InvoicePosition> positions = new ArrayList<>();
        positions.add(new InvoicePosition(1L, 1,1));
        Invoice invoice = new Invoice(1L, 1,1,positions);
        InvoiceObjectDto invoiceObjectDto = new InvoiceObjectDto(1L, "api_token", invoice);
        //When
        Invoice anotherInvoice = invoiceMapper.mapToInvoiceFromInvoiceObjectDto(invoiceObjectDto);
        //Then
        assertEquals(anotherInvoice.getId(), invoice.getId());
        assertEquals(anotherInvoice.getPayment_to_kind(), invoice.getPayment_to_kind());
        assertEquals(anotherInvoice.getClient_id(), invoice.getClient_id());
        assertEquals(anotherInvoice.getPositions().get(0).getId(), invoice.getPositions().get(0).getId());
        assertEquals(anotherInvoice.getPositions().get(0).getProduct_id(), invoice.getPositions().get(0).getProduct_id());
        assertEquals(anotherInvoice.getPositions().get(0).getQuantity(), invoice.getPositions().get(0).getQuantity());
    }
}
