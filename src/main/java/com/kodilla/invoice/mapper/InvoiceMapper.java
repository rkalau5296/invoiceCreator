package com.kodilla.invoice.mapper;

import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.domain.InvoiceDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class InvoiceMapper {
    public Invoice mapToInvoice (final InvoiceDto invoiceDto) {
        return new Invoice(
                invoiceDto.getId(),
                invoiceDto.getSeller_name(),
                invoiceDto.getStatus()
        );
    }
    public InvoiceDto mapToInvoiceDto (final Invoice invoice) {
        return new InvoiceDto(
                invoice.getId(),
                invoice.getSeller_name(),
                invoice.getStatus()
        );
    }
    public List<InvoiceDto> mapToInvoiceDtoList(final List<Invoice> invoiceList) {
        return invoiceList.stream()
                .map(t->new InvoiceDto(t.getId(), t.getSeller_name(), t.getStatus()))
                .collect(Collectors.toList());
    }
    public List<Invoice> mapToListInvoices(final List<InvoiceDto>  invoiceDto) {
        return invoiceDto.stream()
                .map(invoice -> new Invoice(invoice.getId(), invoice.getSeller_name(), invoice.getStatus()))
                .collect(toList());
    }
}
