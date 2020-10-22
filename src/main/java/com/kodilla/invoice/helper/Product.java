package com.kodilla.invoice.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String name;

    private String code;

    private double price_gross;

    private String tax;
}
