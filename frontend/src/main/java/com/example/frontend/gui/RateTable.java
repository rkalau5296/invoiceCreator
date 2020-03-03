package com.example.frontend.gui;

import dto.RateDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RateTable {
    private String table;
    private String no;
    private String effectiveDate;
    private List<RateDto> rates;
}
