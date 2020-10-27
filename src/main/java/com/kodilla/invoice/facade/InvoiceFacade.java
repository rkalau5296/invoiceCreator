package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.CreatedInvoiceDto;
import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.domain.InvoiceDto;
import com.kodilla.invoice.domain.InvoiceObjectDto;
import com.kodilla.invoice.mapper.InvoiceMapper;
import com.kodilla.invoice.service.InvoiceService;
import com.kodilla.invoice.validator.InvoiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceFacade {

    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private InvoiceValidator invoiceValidator;

    public List<Invoice> getAllInvoicesFromDb(){
        List<Invoice> invoices = invoiceService.getAllInvoicesFromDb();
        invoiceValidator.validatingInvoice(invoices);
        return invoices;
    }
    public Invoice getInvoicesFromDbById(Long id){
        invoiceValidator.validateInvoicesById(id);
        return invoiceService.getInvoiceByIdFromDb(id);
    }
    public Invoice createInvoice(InvoiceObjectDto invoiceObjectDto) {
        invoiceValidator.validateCreating(invoiceObjectDto);
        return invoiceService.saveInvoice(invoiceMapper.mapToInvoiceFromInvoiceObjectDto(invoiceObjectDto));
    }
    public Invoice updateInvoice(InvoiceDto invoiceDto) {
        invoiceValidator.validateUpdating(invoiceDto);
        return invoiceService.updateInvoice(invoiceMapper.mapToInvoice(invoiceDto));
    }
    public void deletedById(Long id) {
        invoiceValidator.validateDeletingInvoice(id);
        invoiceService.deleteById(id);
    }


}
