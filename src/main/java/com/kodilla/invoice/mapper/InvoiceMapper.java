package com.kodilla.invoice.mapper;

import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.domain.InvoiceDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceMapper {
    public Invoice mapToInvoice (final InvoiceDto invoiceDto) {
        return new Invoice(
                invoiceDto.getId(),
                invoiceDto.getTitle(),
                invoiceDto.getContent()
        );
    }
    public InvoiceDto mapToInvoiceDto (final Invoice invoice) {
        return new InvoiceDto(
                invoice.getId(),
                invoice.getTitle(),
                invoice.getContent()
        );
    }
    public List<InvoiceDto> mapToInvoiceDtoList(final List<Invoice> taskList) {
        return taskList.stream()
                .map(t->new InvoiceDto(t.getId(), t.getTitle(), t.getContent()))
                .collect(Collectors.toList());
    }
}
