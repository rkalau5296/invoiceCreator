package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.*;
import com.kodilla.invoice.mapper.RateMapper;
import com.kodilla.invoice.service.RateDtoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RateFacadeTest {

    final double DELTA = 1e-15;

    @InjectMocks
    private RateFacade rateFacade;
    @Mock
    private RateMapper rateMapper;
    @Mock
    private RateDtoService rateDtoService;

    @Test
    public void shouldFetchRates() throws Exception {
        //Given
        List<RateDto> rates = new ArrayList<>();
        rates.add(new RateDto("currency", "code", 1.00) );
        List<RateTableDto> rateTableDtoList = new ArrayList<>();
        rateTableDtoList.add(new RateTableDto("table", "no", "effectiveDate", rates));
        List<Rate> rateList = new ArrayList<>();
        rateList.add(new Rate("currency", "code", 1.00) );
        List<RateTable> rateTableList = new ArrayList<>();
        rateTableList.add(new RateTable("table", "no", "effectiveDate", rateList));

        when(rateDtoService.fetchRates("table")).thenReturn(rateTableDtoList);
        when(rateMapper.mapToRateTables(rateTableDtoList)).thenReturn(rateTableList);
        when(rateMapper.mapToRateTablesDto(rateTableList)).thenReturn(rateTableDtoList);

        //when
        List<RateTableDto> tableDtos = rateFacade.fetchRates("table");

        //Then
        assertNotNull(tableDtos);
        assertEquals(1, tableDtos.size());
        assertEquals("table", tableDtos.get(0).getTable());
        assertEquals("no", tableDtos.get(0).getNo());
        assertEquals("effectiveDate", tableDtos.get(0).getEffectiveDate());
        assertEquals("currency", tableDtos.get(0).getRates().get(0).getCurrency());
        assertEquals("code", tableDtos.get(0).getRates().get(0).getCode());
        assertEquals(1.00, tableDtos.get(0).getRates().get(0).getMid(), DELTA);
    }
    @Test
    public void shouldFetchRatesInDateRangeFromTo() throws Exception {
        //Given
        List<RateDto> rates = new ArrayList<>();
        rates.add(new RateDto("currency", "code", 1.00) );
        List<RateTableDto> rateTableDtoList = new ArrayList<>();
        rateTableDtoList.add(new RateTableDto("table", "no", "effectiveDate", rates));
        List<Rate> rateList = new ArrayList<>();
        rateList.add(new Rate("currency", "code", 1.00) );
        List<RateTable> rateTableList = new ArrayList<>();
        rateTableList.add(new RateTable("table", "no", "effectiveDate", rateList));

        when(rateMapper.mapToRateTables(rateTableDtoList)).thenReturn(rateTableList);
        when(rateDtoService.fetchRatesInDateRangeFromTo("table", "2020-01-01", "2020-02-20")).thenReturn(rateTableDtoList);
        when(rateMapper.mapToRateTablesDto(rateTableList)).thenReturn(rateTableDtoList);

        //When
        List<RateTableDto> tableDtos = rateFacade.fetchRatesInDateRangeFromTo("table","2020-01-01", "2020-02-20");

        //Then
        assertNotNull(tableDtos);
        assertEquals(1, tableDtos.size());
        assertEquals("table", tableDtos.get(0).getTable());
        assertEquals("no", tableDtos.get(0).getNo());
        assertEquals("effectiveDate", tableDtos.get(0).getEffectiveDate());
        assertEquals("currency", tableDtos.get(0).getRates().get(0).getCurrency());
        assertEquals("code", tableDtos.get(0).getRates().get(0).getCode());
        assertEquals(1.00, tableDtos.get(0).getRates().get(0).getMid(), DELTA);
    }
    @Test
    public void shouldFetchRateAParticularCurrency() throws Exception {
        //Given
        List<RatesCurrency> rates = new ArrayList<>();
        rates.add(new RatesCurrency("no", "effectiveDate", 10.00));
        RateCurrencyDto rateCurrencyDto = new RateCurrencyDto("table", "currency", "code", rates);

        when(rateDtoService.fetchRateAParticularcurrency("table", "code")).thenReturn(rateCurrencyDto);

        //when
        RateCurrencyDto currencyDto = rateFacade.fetchRateAParticularCurrency("table", "code");

        //Then
        assertNotNull(currencyDto);
        assertEquals("table", currencyDto.getTable());
        assertEquals("currency", currencyDto.getCurrency());
        assertEquals("code", currencyDto.getCode());
        assertEquals("no", currencyDto.getRates().get(0).getNo());
        assertEquals("effectiveDate", currencyDto.getRates().get(0).getEffectiveDate());
        assertEquals(10.00, currencyDto.getRates().get(0).getMid(), DELTA);
    }
}
