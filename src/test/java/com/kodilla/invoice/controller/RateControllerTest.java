package com.kodilla.invoice.controller;

import com.kodilla.invoice.domain.RateCurrencyDto;
import com.kodilla.invoice.domain.RateDto;
import com.kodilla.invoice.domain.RateTableDto;
import com.kodilla.invoice.domain.RatesCurrency;
import com.kodilla.invoice.facade.RateFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RateController.class)
public class RateControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RateFacade rateFacade;

    @Test
    public void shouldGetRates() throws Exception {
        //Given
        List<RateDto> rates = new ArrayList<>();
        rates.add(new RateDto("currency", "code", 10.00));
        List<RateTableDto> rateTableDtoList = new ArrayList<>();
        rateTableDtoList.add(new RateTableDto("table", "no", "2020-02-20", rates));

        when(rateFacade.fetchRates("A")).thenReturn(rateTableDtoList);

        //When & Then
        mockMvc.perform(get("/v1/rates/A").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                //rateTableDtoList fields
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].table", is("table")))
                .andExpect(jsonPath("$[0].no", is("no")))
                .andExpect(jsonPath("$[0].effectiveDate", is("2020-02-20")))
                //rates position
                .andExpect(jsonPath("$[0].rates", hasSize(1)))
                .andExpect(jsonPath("$[0].rates[0].currency", is("currency")))
                .andExpect(jsonPath("$[0].rates[0].code", is("code")))
                .andExpect(jsonPath("$[0].rates[0].mid", is(10.00)));
    }

    @Test
    public void shouldGetRatesInDateRangeFromTo() throws Exception {
        List<RateDto> rates = new ArrayList<>();
        rates.add(new RateDto("currency", "code", 10.00));
        List<RateTableDto> rateTableDtoList = new ArrayList<>();
        rateTableDtoList.add(new RateTableDto("table", "no", "2020-02-20", rates));

        when(rateFacade.fetchRatesInDateRangeFromTo("A", "2020-01-01", "2020-02-20")).thenReturn(rateTableDtoList);

        //When & Then
        mockMvc.perform(get("/v1/rates/A/2020-01-01/2020-02-20").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                //rateTableDtoList fields
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].table", is("table")))
                .andExpect(jsonPath("$[0].no", is("no")))
                .andExpect(jsonPath("$[0].effectiveDate", is("2020-02-20")))
                //rates position
                .andExpect(jsonPath("$[0].rates", hasSize(1)))
                .andExpect(jsonPath("$[0].rates[0].currency", is("currency")))
                .andExpect(jsonPath("$[0].rates[0].code", is("code")))
                .andExpect(jsonPath("$[0].rates[0].mid", is(10.00)));
    }
    @Test
    public void shouldGetRateAParticularCurrency() throws Exception {
        List<RatesCurrency> rates = new ArrayList<>();
        rates.add(new RatesCurrency("no", "2020-02-20", 10.00));
        RateCurrencyDto rateCurrencyDto =  new RateCurrencyDto("table", "currency", "CHF", rates);

        when(rateFacade.fetchRateAParticularCurrency("A", "CHF")).thenReturn(rateCurrencyDto);

        //When & Then
        mockMvc.perform(get("/v1/rates/A/CHF").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                //rateCurrencyDto fields
                .andExpect(jsonPath("$.table", is("table")))
                .andExpect(jsonPath("$.currency", is("currency")))
                .andExpect(jsonPath("$.code", is("CHF")))
                //rates position
                .andExpect(jsonPath("$.rates", hasSize(1)))
                .andExpect(jsonPath("$.rates[0].no", is("no")))
                .andExpect(jsonPath("$.rates[0].effectiveDate", is("2020-02-20")))
                .andExpect(jsonPath("$.rates[0].mid", is(10.00)));

    }
}
