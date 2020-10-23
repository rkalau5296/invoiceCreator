package com.kodilla.invoice.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    String buyer_name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(buyer_name, invoice.buyer_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyer_name);
    }

    @Override
    public String toString() {
        return "buyer_name='" + buyer_name;
    }
}
