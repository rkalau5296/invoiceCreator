package com.kodilla.invoice.service;

import com.kodilla.invoice.client.InvoiceClient;
import com.kodilla.invoice.config.AdminConfig;
import com.kodilla.invoice.domain.CreatedProductDto;
import com.kodilla.invoice.domain.CustomerDto;
import com.kodilla.invoice.domain.Mail;
import com.kodilla.invoice.domain.ProductObjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static java.util.Optional.ofNullable;
@Service
public class ProductObjectService {
    @Autowired
    private InvoiceClient invoiceClient;
    @Autowired
    private SimpleEmailService emailService;
    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "New product";

    public CreatedProductDto createProduct(final ProductObjectDto productObjectDto) {
        CreatedProductDto newProduct = invoiceClient.postProduct(productObjectDto);
                ofNullable(newProduct).ifPresent(product->emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
                "New product: "+ product.getName() + " has been created, and sent to fakturownia.pl.")));

        return newProduct;
    }
}
