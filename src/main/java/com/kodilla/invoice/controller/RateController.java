package com.kodilla.invoice.controller;

import com.kodilla.invoice.domain.RateTableDto;
import com.kodilla.invoice.facade.RateFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class RateController {
    @Autowired
    private RateFacade rateFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/rates")
    public List<RateTableDto> getRates() {
        return rateFacade.fetchRates();
    }

}
