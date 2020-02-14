package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.Rate;
import com.kodilla.invoice.domain.RateDto;
import com.kodilla.invoice.domain.RateTable;
import com.kodilla.invoice.domain.RateTableDto;
import com.kodilla.invoice.mapper.RateMapper;
import com.kodilla.invoice.service.RateDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class RateFacade {

    @Autowired
    private RateMapper rateMapper;
    @Autowired
    private RateDtoService rateDtoService;


    public List<RateTableDto> fetchRates(String table) {
        List<RateTable> rates = rateMapper.mapToRateTables(rateDtoService.fetchRates(table));
        return rateMapper.mapToRateTablesDto(rates);
    }
    public List<RateTableDto> fetchRatesInDateRangeFromTo(String table, String startDate, String endDate) {
        List<RateTable> rates = rateMapper.mapToRateTables(rateDtoService.fetchRatesInDateRangeFromTo(table, startDate, endDate));
        return rateMapper.mapToRateTablesDto(rates);
    }
    public RateTableDto fetchRateAParticularCurrency(String table, String code) {
        RateTable rate = rateMapper.mapToRateTable(rateDtoService.fetchRateAParticularcurrency(table, code));
        return rateMapper.mapToRateTableDto(rate);
    }
}
