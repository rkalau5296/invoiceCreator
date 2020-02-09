package com.kodilla.invoice.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvoiceDto {
    private Long id;
    private String title;
    private String content;
}
