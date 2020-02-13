package com.kodilla.invoice.client;


import com.kodilla.invoice.config.InvoiceConfig;
import com.kodilla.invoice.domain.InvoiceDto;
import com.kodilla.invoice.domain.ClientDto;
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
    public InvoiceDto getInvoicesById(Long id){

        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/invoices/" + id +".json?&" + invoiceConfig.getInvoiceToken())
                .build().encode().toUri();
        try{
            InvoiceDto invoiceResponse = restTemplate.getForObject(uri, InvoiceDto.class);
            return invoiceResponse;
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new InvoiceDto();
        }

    }
    public List<ClientDto> getClients(){

        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/clients.json?" + invoiceConfig.getInvoiceToken())
                .build().encode().toUri();
        try{
            ClientDto[] clientResponse = restTemplate.getForObject(uri, ClientDto[].class);
            return Arrays.asList(Optional.ofNullable(clientResponse).orElse(new ClientDto[0]));
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new ArrayList<>();
        }

    }
    public ClientDto getClientById(Long id){

        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/clients/" + id + ".json?" + invoiceConfig.getInvoiceToken())
                .build().encode().toUri();
        try{
            ClientDto clientResponse = restTemplate.getForObject(uri, ClientDto.class);
            return clientResponse;
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new ClientDto();
        }

    }

}
