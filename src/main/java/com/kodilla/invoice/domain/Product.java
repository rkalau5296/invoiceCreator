package com.kodilla.invoice.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String code;
    @Column
    private double price_net;
    @Column
    private String tax;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price_net, price_net) == 0 &&
                Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(code, product.code) &&
                Objects.equals(tax, product.tax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, price_net, tax);
    }

    @Override
    public String toString() {
        return "" + name;
    }
}
