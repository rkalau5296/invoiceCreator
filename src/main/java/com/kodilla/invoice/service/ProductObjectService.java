package com.kodilla.invoice.service;

import com.kodilla.invoice.client.InvoiceClient;
import com.kodilla.invoice.domain.CreatedProductDto;
import com.kodilla.invoice.domain.CustomerDto;
import com.kodilla.invoice.domain.ProductObjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductObjectService {
    @Autowired
    private InvoiceClient invoiceClient;


    public CreatedProductDto createProduct(final ProductObjectDto productObjectDto) {
        CreatedProductDto newProduct = invoiceClient.postProduct(productObjectDto);
        //        ofNullable(newCustomer).ifPresent(customer->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
////                "New customer: "+ card.getName() + " has been created on your Trello account")));

        return newProduct;
    }
}
