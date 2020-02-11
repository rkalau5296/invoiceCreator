package com.kodilla.invoice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class InvoiceConfig {
    @Value("${api.endpoint.prod}")
    private String invoiceApiEndpoint;

    @Value("${api_token}")
    private String invoiceToken;

}
