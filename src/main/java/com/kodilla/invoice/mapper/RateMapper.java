package com.kodilla.invoice.mapper;

import com.kodilla.invoice.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class RateMapper {
    public List<Rate> mapToListRates(final List<RateDto> rateDto) {
        return rateDto.stream()
                .map(p -> new Rate(p.getCurrency(), p.getCode(), p.getMid()))
                .collect(toList());
    }
    public List<RateDto> mapToListRatesDto(final List<Rate> rates) {
        return rates.stream()
                .map(p -> new RateDto(p.getCurrency(), p.getCode(), p.getMid()))
                .collect(toList());
    }
    public Rate mapToRate (final RateDto rateDto) {
        return new Rate(
                rateDto.getCurrency(),
                rateDto.getCode(),
                rateDto.getMid()
        );
    }

    public List<RateTable> mapToRateTables(final List<RateTableDto>  rateTableDto) {
        return rateTableDto.stream()
                .map(rateTable -> new RateTable(rateTable.getTable(), rateTable.getNo(), rateTable.getEffectiveDate(), mapToListRates(rateTable.getRates())))
                .collect(toList());
    }
    public List<RateTableDto> mapToRateTablesDto(final List<RateTable>  rateTable) {
        return rateTable.stream()
                .map(rateTableDto -> new RateTableDto(rateTableDto.getTable(), rateTableDto.getNo(), rateTableDto.getEffectiveDate(), mapToListRatesDto(rateTableDto.getRates())))
                .collect(toList());
    }
    public RateTable mapToRateTable(final RateTableDto  rateTableDto) {
        return new RateTable (
                rateTableDto.getTable(),
                rateTableDto.getNo(),
                rateTableDto.getEffectiveDate(),
                mapToListRates(rateTableDto.getRates()));

    }
    public RateTableDto mapToRateTableDto(final RateTable rateTable) {
        return new RateTableDto(
                rateTable.getTable(),
                rateTable.getNo(),
                rateTable.getEffectiveDate(),
                mapToListRatesDto(rateTable.getRates()));

    }
}
