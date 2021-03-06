package com.kodilla.invoice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RateCurrencyDto {

    String table;
    String currency;
    String code;
    List<RatesCurrency> rates;
}
