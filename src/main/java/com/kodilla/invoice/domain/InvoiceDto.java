package com.kodilla.invoice.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {
    private Long id;
    //private String kind;
    private String seller_name;
    private String status;
}
