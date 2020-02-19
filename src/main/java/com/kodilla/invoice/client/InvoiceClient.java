package com.kodilla.invoice.client;

import com.kodilla.invoice.config.InvoiceConfig;
import com.kodilla.invoice.config.RateConfig;
import com.kodilla.invoice.domain.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.slf4j.Logger;
import java.net.URI;
import java.util.*;

@Component
public class InvoiceClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceClient.class);

    @Autowired
    private InvoiceConfig invoiceConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RateConfig rateConfig;

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

    public List<ProductDto> getProducts(){
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/products.json?" + invoiceConfig.getInvoiceToken())
                .build().encode().toUri();
        try{
            ProductDto[] clientResponse = restTemplate.getForObject(uri, ProductDto[].class);
            return Arrays.asList(Optional.ofNullable(clientResponse).orElse(new ProductDto[0]));
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new ArrayList<>();
        }
    }

    public ProductDto getProductById(Long id){
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/products/" + id + ".json?" + invoiceConfig.getInvoiceToken())
                .build().encode().toUri();
        try{
            ProductDto productResponse = restTemplate.getForObject(uri, ProductDto.class);
            return productResponse;
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new ProductDto();
        }
    }

    public List<RateTableDto> getRates(String table){
        URI uri = UriComponentsBuilder.fromHttpUrl(rateConfig.getRateEndPoint() + "exchangerates/tables/" + table)
                .build().encode().toUri();
        try{
            RateTableDto[] rateResponse = restTemplate.getForObject(uri, RateTableDto[].class);
            return Arrays.asList(Optional.ofNullable(rateResponse).orElse(new RateTableDto[0]));
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new ArrayList<>();
        }
    }

    public List<RateTableDto> getRatesInDateRangeFromTo(String table, String startDate, String endDate){
        URI uri = UriComponentsBuilder.fromHttpUrl(rateConfig.getRateEndPoint() + "exchangerates/tables/" + table + "/" + startDate + "/" + endDate)
                .build().encode().toUri();
        try{
            RateTableDto[] rateResponse = restTemplate.getForObject(uri, RateTableDto[].class);
            return Arrays.asList(Optional.ofNullable(rateResponse).orElse(new RateTableDto[0]));
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new ArrayList<>();
        }
    }

    public RateCurrencyDto getRateAPArticularCurrency(String table, String code){
        URI uri = UriComponentsBuilder.fromHttpUrl(rateConfig.getRateEndPoint() + "exchangerates/rates/" + table + "/" + code)
                .build().encode().toUri();
        try{
            RateCurrencyDto rateResponse = restTemplate.getForObject(uri, RateCurrencyDto.class);
            return rateResponse;
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return new RateCurrencyDto();
        }
    }

    public CreatedCustomerDto postCustomer(final CustomerDto customerDto){
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/clients.json")

                .build().encode().toUri();
        try{
            CreatedCustomerDto clientDtoResponse = restTemplate.postForObject(uri, customerDto, CreatedCustomerDto.class);
            return clientDtoResponse;
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new CreatedCustomerDto();
        }
    }
    public CreatedProductDto postProduct(final ProductObjectDto productObjectDto){
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/products.json")

                .build().encode().toUri();
        try{
            CreatedProductDto clientDtoResponse = restTemplate.postForObject(uri, productObjectDto, CreatedProductDto.class);
            return clientDtoResponse;
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new CreatedProductDto();
        }
    }

}
