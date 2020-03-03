package com.example.frontend.client;

import com.example.frontend.config.FrontendConfig;
import dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class FrontendClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FrontendClient.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private FrontendConfig frontendConfig;


    public List<RateTableDto> getRates(String table){
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/rates/" + table)
                .build().encode().toUri();
        try{
            RateTableDto[] rateResponse = restTemplate.getForObject(uri, RateTableDto[].class);
            return Arrays.asList(Optional.ofNullable(rateResponse).orElse(new RateTableDto[0]));
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new ArrayList<>();
        }
    }
    public List<CreatedInvoiceDto> getInvoices(){
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/invoiceObject/invoices")
                .build().encode().toUri();
        try{
            CreatedInvoiceDto[] invoiceResponse = restTemplate.getForObject(uri, CreatedInvoiceDto[].class);
            return Arrays.asList(Optional.ofNullable(invoiceResponse).orElse(new CreatedInvoiceDto[0]));
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new ArrayList<>();
        }
    }

    public CreatedInvoiceDto getInvoicesById(Long id){
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/invoiceObject/invoices/"+ id)
                .build().encode().toUri();
        try{
            CreatedInvoiceDto invoiceResponse = restTemplate.getForObject(uri, CreatedInvoiceDto.class);
            return invoiceResponse;
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new CreatedInvoiceDto();
        }
    }

    public List<ClientDto> getClients(){
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/customers")
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
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/customers" + id)
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
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/productObject/products")
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
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/productObject/products" + id)
                .build().encode().toUri();
        try{
            ProductDto productResponse = restTemplate.getForObject(uri, ProductDto.class);
            return productResponse;
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new ProductDto();
        }
    }



    public List<RateTableDto> getRatesInDateRangeFromTo(String table, String startDate, String endDate){
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/rates/" + table + "/" + startDate + "/" + endDate)
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
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/rates/" + table + "/" + code)
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
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/customers")

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
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/productObject/products")

                .build().encode().toUri();
        try{
            CreatedProductDto clientDtoResponse = restTemplate.postForObject(uri, productObjectDto, CreatedProductDto.class);
            return clientDtoResponse;
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new CreatedProductDto();
        }
    }
    public CreatedInvoiceDto postInvoice(final InvoiceObjectDto invoiceObjectDto){
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/invoiceObject/invoices")

                .build().encode().toUri();
        try{
            CreatedInvoiceDto invoiceDtoResponse = restTemplate.postForObject(uri, invoiceObjectDto, CreatedInvoiceDto.class);
            return invoiceDtoResponse;
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new CreatedInvoiceDto();
        }
    }
}
