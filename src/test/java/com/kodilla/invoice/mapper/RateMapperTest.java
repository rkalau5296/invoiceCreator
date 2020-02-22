package com.kodilla.invoice.mapper;

import com.kodilla.invoice.domain.Rate;
import com.kodilla.invoice.domain.RateDto;
import com.kodilla.invoice.domain.RateTable;
import com.kodilla.invoice.domain.RateTableDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RateMapperTest {
    final double DELTA = 1e-15;
    @Autowired
    private RateMapper rateMapper;

    @Test
    public void mapToListRatesTest() {
        //Given
        List<RateDto> rateDto = new ArrayList<>();
        rateDto.add(new RateDto("currency", "code", 10.00));
        //When
        List<Rate> rates = rateMapper.mapToListRates(rateDto);
        //Then
        assertEquals(rateDto.get(0).getCurrency(), rates.get(0).getCurrency());
        assertEquals(rateDto.get(0).getMid(), rates.get(0).getMid(), DELTA);
        assertEquals(rateDto.get(0).getCode(), rates.get(0).getCode());
    }
    @Test
    public void mapToListRatesDtoTest() {
        //Given
        List<Rate> rates = new ArrayList<>();
        rates.add(new Rate("currency", "code", 10.00));
        //When
        List<RateDto> rateDtoList = rateMapper.mapToListRatesDto(rates);
        //Then
        assertEquals(rateDtoList.get(0).getCurrency(), rates.get(0).getCurrency());
        assertEquals(rateDtoList.get(0).getMid(), rates.get(0).getMid(), DELTA);
        assertEquals(rateDtoList.get(0).getCode(), rates.get(0).getCode());
    }
    @Test
    public void mapToRateTablesTest() {
        //Given
        List<RateDto> rates = new ArrayList<>();
        rates.add(new RateDto("currency", "code", 10.00));
        List<RateTableDto> rateTableDto = new ArrayList<>();
        rateTableDto.add(new RateTableDto("table", "no", "effectiveDate", rates));
        //When
        List<RateTable> rateTables = rateMapper.mapToRateTables(rateTableDto);
        //Then
        assertEquals(rateTables.get(0).getTable(), rateTableDto.get(0).getTable());
        assertEquals(rateTables.get(0).getNo(), rateTableDto.get(0).getNo());
        assertEquals(rateTables.get(0).getEffectiveDate(), rateTableDto.get(0).getEffectiveDate());
        assertEquals(rateTables.get(0).getRates().get(0).getCurrency(), rateTableDto.get(0).getRates().get(0).getCurrency());
        assertEquals(rateTables.get(0).getRates().get(0).getCode(), rateTableDto.get(0).getRates().get(0).getCode());
        assertEquals(rateTables.get(0).getRates().get(0).getMid(), rateTableDto.get(0).getRates().get(0).getMid(), DELTA);
    }
    @Test
    public void mapToRateTablesDtoTest() {
        //Given
        List<Rate> rates = new ArrayList<>();
        rates.add(new Rate("currency", "code", 10.00));
        List<RateTable> rateTable = new ArrayList<>();
        rateTable.add(new RateTable("table", "no", "effectiveDate", rates));
        //When
        List<RateTableDto> rateTables = rateMapper.mapToRateTablesDto(rateTable);
        //Then
        assertEquals(rateTables.get(0).getTable(), rateTable.get(0).getTable());
        assertEquals(rateTables.get(0).getNo(), rateTable.get(0).getNo());
        assertEquals(rateTables.get(0).getEffectiveDate(), rateTable.get(0).getEffectiveDate());
        assertEquals(rateTables.get(0).getRates().get(0).getCurrency(), rateTable.get(0).getRates().get(0).getCurrency());
        assertEquals(rateTables.get(0).getRates().get(0).getCode(), rateTable.get(0).getRates().get(0).getCode());
        assertEquals(rateTables.get(0).getRates().get(0).getMid(), rateTable.get(0).getRates().get(0).getMid(), DELTA);
    }
}
