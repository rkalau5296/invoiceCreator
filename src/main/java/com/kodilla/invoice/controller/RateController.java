package com.kodilla.invoice.controller;

import com.kodilla.invoice.domain.RateCurrencyDto;
import com.kodilla.invoice.domain.RateTableDto;
import com.kodilla.invoice.facade.RateFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class RateController {
    @Autowired
    private RateFacade rateFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/rates/{table}")
    public List<RateTableDto> getRates(@PathVariable String table) {
        return rateFacade.fetchRates(table);
    }
    //format date RRRR-MM-DD

    @RequestMapping(method = RequestMethod.GET, value = "/rates/{table}/{startDate}/{endDate}")
    public List<RateTableDto> getRatesInDateRangeFromTo(@PathVariable String table, @PathVariable String startDate, @PathVariable String endDate) {
        return rateFacade.fetchRatesInDateRangeFromTo(table, startDate, endDate);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rates/{table}/{code}")
    public RateCurrencyDto getRateAParticularCurrency(@PathVariable String table, @PathVariable String code) {
        return rateFacade.fetchRateAParticularCurrency(table, code);
    }
}
