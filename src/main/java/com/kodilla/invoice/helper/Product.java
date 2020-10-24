package com.kodilla.invoice.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String name;

    private String code;

    private double price_gross;

    private String tax;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price_gross, price_gross) == 0 &&
                Objects.equals(name, product.name) &&
                Objects.equals(code, product.code) &&
                Objects.equals(tax, product.tax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, price_gross, tax);
    }

    @Override
    public String toString() {
        return "" + name;
    }
}
