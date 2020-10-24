package com.kodilla.invoice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductObjectDto {
    private Long id;
    private String api_token;
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductObjectDto that = (ProductObjectDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(api_token, that.api_token) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, api_token, product);
    }

    @Override
    public String toString() {
        return "" + product;
    }
}
