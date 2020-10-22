package com.kodilla.invoice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.kodilla.invoice.helper.Invoice;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BuyerDto {

    private String api_token;
    private Invoice invoice;

}
