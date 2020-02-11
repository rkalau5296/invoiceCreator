package com.kodilla.invoice.client;


import com.kodilla.invoice.config.InvoiceConfig;
import com.kodilla.invoice.domain.InvoiceDto;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.slf4j.Logger;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class InvoiceClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceClient.class);

    @Autowired
    private InvoiceConfig invoiceConfig;
    @Autowired
    private RestTemplate restTemplate;


    public List<InvoiceDto> getInvoices(){

        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/invoices.json?&" + invoiceConfig.getInvoiceToken())
                .build().encode().toUri();
        try{
            InvoiceDto[] invoiceResponse = restTemplate.getForObject(uri, InvoiceDto[].class);
            return Arrays.asList(Optional.ofNullable(invoiceResponse).orElse(new InvoiceDto[0]));
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new ArrayList<>();
        }

    }

}
