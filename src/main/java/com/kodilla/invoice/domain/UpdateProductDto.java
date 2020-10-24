package com.kodilla.invoice.domain;

import com.kodilla.invoice.helper.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductDto {

    private String api_token;
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateProductDto that = (UpdateProductDto) o;
        return Objects.equals(api_token, that.api_token) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(api_token, product);
    }

    @Override
    public String toString() {
        return "" + product;
    }
}
