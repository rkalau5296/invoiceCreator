package com.kodilla.invoice.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RateTableDto {

    @JsonProperty("table")
    private String table;
    @JsonProperty("no")
    private String no;
    @JsonProperty("effectiveDate")
    private String effectiveDate;
    @JsonProperty("rates")
    private List<com.kodilla.invoice.domain.RateDto> rates;
}
