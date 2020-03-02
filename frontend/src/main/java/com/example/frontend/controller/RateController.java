package com.example.frontend.controller;


import com.example.frontend.facade.RateFacade;
import dto.RateTableDto;
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
}
