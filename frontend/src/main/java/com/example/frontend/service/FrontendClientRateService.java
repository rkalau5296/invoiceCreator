package com.example.frontend.service;

import com.example.frontend.client.FrontendClient;
import dto.RateTableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrontendClientRateService {
    @Autowired
    private FrontendClient frontendClient;

    public List<RateTableDto> fetchRates(String table) {
        return frontendClient.getRates(table);
    }

}
