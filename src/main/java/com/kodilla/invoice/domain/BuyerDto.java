package com.kodilla.invoice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.kodilla.invoice.helper.Invoice;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BuyerDto {

    private String api_token;
    private Invoice invoice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyerDto buyerDto = (BuyerDto) o;
        return Objects.equals(api_token, buyerDto.api_token) &&
                Objects.equals(invoice, buyerDto.invoice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(api_token, invoice);
    }

    @Override
    public String toString() {
        return "{" + invoice + '}';
    }
}
