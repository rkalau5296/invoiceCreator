package com.example.frontend.client;

import com.example.frontend.config.FrontendConfig;
import dto.RateTableDto;
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
}
