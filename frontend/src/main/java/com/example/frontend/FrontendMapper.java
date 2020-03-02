package com.example.frontend;

import dto.Rate;
import dto.RateDto;
import dto.RateTable;
import dto.RateTableDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class FrontendMapper {
    public List<RateTable> mapToRateTables(final List<RateTableDto>  rateTableDto) {
        return rateTableDto.stream()
                .map(rateTable -> new RateTable(rateTable.getTable(), rateTable.getNo(), rateTable.getEffectiveDate(), mapToListRates(rateTable.getRates())))
                .collect(toList());
    }
    public List<Rate> mapToListRates(final List<RateDto> rateDto) {
        return rateDto.stream()
                .map(p -> new Rate(p.getCurrency(), p.getCode(), p.getMid()))
                .collect(toList());
    }
    public List<RateTableDto> mapToRateTablesDto(final List<RateTable>  rateTable) {
        return rateTable.stream()
                .map(rateTableDto -> new RateTableDto(rateTableDto.getTable(), rateTableDto.getNo(), rateTableDto.getEffectiveDate(), mapToListRatesDto(rateTableDto.getRates())))
                .collect(toList());
    }
    public List<RateDto> mapToListRatesDto(final List<Rate> rates) {
        return rates.stream()
                .map(p -> new RateDto(p.getCurrency(), p.getCode(), p.getMid()))
                .collect(toList());
    }

}
