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
    public InvoiceDto mapToInvoiceDto (final Invoice invoice) {
        return new InvoiceDto(
                invoice.getId(),
                invoice.getPayment_to_kind(),
                invoice.getClient_id(),
                invoice.getPositions()
        );
    }
    public List<InvoiceDto> mapToInvoiceDtoList(final List<Invoice> invoiceList) {
        return invoiceList.stream()
                .map(t->new InvoiceDto(
                        t.getId(),
                        t.getPayment_to_kind(),
                        t.getClient_id(),
                        t.getPositions()
                ))
                .collect(Collectors.toList());
    }
    public List<Invoice> mapToListInvoices(final List<InvoiceDto>  invoiceDto) {
        return invoiceDto.stream()
                .map(invoice -> new Invoice(
                        invoice.getId(),
                        invoice.getPayment_to_kind(),
                        invoice.getClient_id(),
                        invoice.getPositions()
                ))
                .collect(toList());
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
