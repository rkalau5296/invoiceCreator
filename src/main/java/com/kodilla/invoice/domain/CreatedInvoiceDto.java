package com.kodilla.invoice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "createdInvoiceDto")
public class CreatedInvoiceDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @Column
    @JsonProperty("price_net")
    private String price_net;

    @Column
    @JsonProperty("price_gross")
    private String price_gross;

    @Column
    @JsonProperty("buyer_name")
    private String buyer_name;

    @Column
    @JsonProperty("product_cache")
    private String product_cache;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatedInvoiceDto that = (CreatedInvoiceDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(price_net, that.price_net) &&
                Objects.equals(price_gross, that.price_gross) &&
                Objects.equals(buyer_name, that.buyer_name) &&
                Objects.equals(product_cache, that.product_cache);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price_net, price_gross, buyer_name, product_cache);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", price_net='" + price_net + '\'' +
                ", price_gross='" + price_gross + '\'' +
                ", buyer_name='" + buyer_name + '\'' +
                ", product_cache='" + product_cache + '\'' +
                '}';
    }
}
