package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.Rate;
import com.kodilla.invoice.domain.RateDto;
import com.kodilla.invoice.domain.RateTable;
import com.kodilla.invoice.domain.RateTableDto;
import com.kodilla.invoice.mapper.RateMapper;
import com.kodilla.invoice.service.RateDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RateFacade {

    @Autowired
    private RateMapper rateMapper;
    @Autowired
    private RateDtoService rateDtoService;


    public List<RateTableDto> fetchRates() {
        List<RateTable> rates = rateMapper.mapToRateTables(rateDtoService.fetchRates());
        return rateMapper.mapToRateTablesDto(rates);
    }
}
