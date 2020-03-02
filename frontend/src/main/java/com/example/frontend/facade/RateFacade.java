package com.example.frontend.facade;

import com.example.frontend.FrontendMapper;
import com.example.frontend.service.FrontendClientRateService;
import dto.RateTable;
import dto.RateTableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RateFacade {

    @Autowired
    private FrontendMapper rateMapperper;
    @Autowired
    private FrontendClientRateService frontendClientRateService;

    public List<RateTableDto> fetchRates(String table) {
        List<RateTable> rates = rateMapperper.mapToRateTables(frontendClientRateService.fetchRates(table));
        return rateMapperper.mapToRateTablesDto(rates);
    }
}
