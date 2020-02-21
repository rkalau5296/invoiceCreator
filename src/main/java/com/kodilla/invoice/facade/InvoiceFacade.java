package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.domain.InvoiceDto;
import com.kodilla.invoice.domain.InvoiceObjectDto;
import com.kodilla.invoice.mapper.InvoiceMapper;
import com.kodilla.invoice.service.InvoiceDtoService;
import com.kodilla.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceFacade {

    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private InvoiceDtoService invoiceDtoService;
    @Autowired
    private InvoiceService invoiceService;


    public void deletedById(Long id) {
        invoiceDtoService.deleteById(id);
    }

    public Invoice updateInvoice(InvoiceDto invoiceDto) {
        return invoiceService.saveInvoice(invoiceMapper.mapToInvoice(invoiceDto));
    }

    public Invoice createInvoice(InvoiceObjectDto invoiceObjectDto) {
        return invoiceService.saveInvoice(invoiceMapper.mapToInvoiceFromInvoiceObjectDto(invoiceObjectDto));
    }

    public List<Invoice> getAllInvoicesFromDb(){
        return invoiceService.getAllInvoicesFromDb();
    }
    public Invoice getInvoicesFromDbById(Long id){
        return invoiceService.getInvoiceByIdFromDb(id);
    }

}
