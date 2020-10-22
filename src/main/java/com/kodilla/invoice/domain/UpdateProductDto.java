package com.kodilla.invoice.domain;

import com.kodilla.invoice.helper.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductDto {

    private String api_token;
    private Product product;



}
