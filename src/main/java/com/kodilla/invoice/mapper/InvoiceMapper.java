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
                invoiceDto.getKind(),
                invoiceDto.getNumber(),
                invoiceDto.getSell_date(),
                invoiceDto.getIssue_date(),
                invoiceDto.getPayment_to(),
                invoiceDto.getSeller_name(),
                invoiceDto.getSeller_tax_no(),
                invoiceDto.getBuyer_name(),
                invoiceDto.getBuyer_tax_no(),
                invoiceDto.getPositions()
        );
    }
    public InvoiceDto mapToInvoiceDto (final Invoice invoice) {
        return new InvoiceDto(
                invoice.getId(),
                invoice.getKind(),
                invoice.getNumber(),
                invoice.getSell_date(),
                invoice.getIssue_date(),
                invoice.getPayment_to(),
                invoice.getSeller_name(),
                invoice.getSeller_tax_no(),
                invoice.getBuyer_name(),
                invoice.getBuyer_tax_no(),
                invoice.getPositions()
        );
    }
    public List<InvoiceDto> mapToInvoiceDtoList(final List<Invoice> invoiceList) {
        return invoiceList.stream()
                .map(t->new InvoiceDto(
                        t.getId(),
                        t.getKind(),
                        t.getNumber(),
                        t.getSell_date(),
                        t.getIssue_date(),
                        t.getPayment_to(),
                        t.getSeller_name(),
                        t.getSeller_tax_no(),
                        t.getBuyer_name(),
                        t.getBuyer_tax_no(),
                        t.getPositions()
                ))
                .collect(Collectors.toList());
    }
    public List<Invoice> mapToListInvoices(final List<InvoiceDto>  invoiceDto) {
        return invoiceDto.stream()
                .map(invoice -> new Invoice(
                        invoice.getId(),
                        invoice.getKind(),
                        invoice.getNumber(),
                        invoice.getSell_date(),
                        invoice.getIssue_date(),
                        invoice.getPayment_to(),
                        invoice.getSeller_name(),
                        invoice.getSeller_tax_no(),
                        invoice.getBuyer_name(),
                        invoice.getBuyer_tax_no(),
                        invoice.getPositions()
                ))
                .collect(toList());
    }
}
