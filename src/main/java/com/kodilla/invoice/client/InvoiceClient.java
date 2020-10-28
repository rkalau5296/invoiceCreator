package com.kodilla.invoice.client;

import com.kodilla.invoice.config.InvoiceConfig;
import com.kodilla.invoice.config.RateConfig;
import com.kodilla.invoice.domain.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
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

    //Invoice
    public List<CreatedInvoiceDto> getInvoices(){
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/invoices.json?&" + invoiceConfig.getInvoiceToken())
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
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/invoices/" + id +".json?&" + invoiceConfig.getInvoiceToken())
                .build().encode().toUri();
        try{
            return restTemplate.getForObject(uri, CreatedInvoiceDto.class);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new CreatedInvoiceDto();
        }
    }
    public CreatedInvoiceDto postInvoice(final InvoiceObjectDto invoiceObjectDto){
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/invoices.json")
                .build().encode().toUri();
        try{
            return restTemplate.postForObject(uri, invoiceObjectDto, CreatedInvoiceDto.class);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new CreatedInvoiceDto();
        }
    }
    public void updateInvoice(final BuyerDto buyerDto, Long id) {
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/invoices/" + id + ".json")
                .build().encode().toUri();
        try{
            restTemplate.put(uri, buyerDto);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
        }
    }
    public void deleteInvoice(final Long id) {
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/invoices/" + id + "json?api_token=" + invoiceConfig.getToken())
                .build().encode().toUri();
        try{
            restTemplate.delete(uri);

        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invoice not found" );
        }
    }

    //Product
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
            return restTemplate.getForObject(uri, ProductDto.class);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new ProductDto();
        }
    }
    public CreatedProductDto postProduct(final ProductObjectDto productObjectDto){
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/products.json")
                .build().encode().toUri();
        try{
            return restTemplate.postForObject(uri, productObjectDto, CreatedProductDto.class);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new CreatedProductDto();
        }
    }
    public void updateProduct(final UpdateProductDto productObjectDto, Long id){
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/products/" + id + ".json")
                .build().encode().toUri();
        try{
            restTemplate.put(uri, productObjectDto);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
        }
    }
    public void deleteProductById(final Long id) {
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/products/" + id + "json?api_token=" + invoiceConfig.getToken())
                .build().encode().toUri();
        try{
            restTemplate.delete(uri);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found" );
        }
    }

    //Customer
    public List<ClientDto> getCustomers(){
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
    public ClientDto getCustomerById(Long id){
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/clients/" + id + ".json?" + invoiceConfig.getInvoiceToken())
                .build().encode().toUri();
        try{
            return restTemplate.getForObject(uri, ClientDto.class);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new ClientDto();
        }
    }
    public CreatedCustomerDto postCustomer(final CustomerDto customerDto){
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/clients.json")
                .build().encode().toUri();
        try{
            return restTemplate.postForObject(uri, customerDto, CreatedCustomerDto.class);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return  new CreatedCustomerDto();
        }
    }
    public void updateCustomer(final CustomerDto customerDto, Long id) {
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/clients/" + id + ".json")
                .build().encode().toUri();
        try{
            restTemplate.put(uri, customerDto);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
        }
    }
    public void deleteCustomerById(final Long id) {
        URI uri = UriComponentsBuilder.fromHttpUrl(invoiceConfig.getInvoiceApiEndpoint() + ".fakturownia.pl/clients/" + id + "json?api_token=" + invoiceConfig.getToken())
                .build().encode().toUri();
        try{
            restTemplate.delete(uri);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found" );
        }
    }

    //Rates
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
            return restTemplate.getForObject(uri, RateCurrencyDto.class);
        }catch(RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return new RateCurrencyDto();
        }
    }

}
