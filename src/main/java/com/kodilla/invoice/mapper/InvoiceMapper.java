package com.kodilla.invoice.mapper;

import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.domain.InvoiceDto;
import com.kodilla.invoice.domain.InvoiceObjectDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class InvoiceMapper {
    public Invoice mapToInvoice (final InvoiceDto invoiceDto) {
        return new Invoice(
                invoiceDto.getId(),
                invoiceDto.getPayment_to_kind(),
                invoiceDto.getClient_id(),
                invoiceDto.getPositions()
        );
    }

    public Invoice mapToInvoiceFromInvoiceObjectDto (final InvoiceObjectDto invoiceObjectDto) {
        return new Invoice(
                invoiceObjectDto.getInvoice().getId(),
                invoiceObjectDto.getInvoice().getClient_id(),
                invoiceObjectDto.getInvoice().getPayment_to_kind(),
                invoiceObjectDto.getInvoice().getPositions()
                );
    }
}
