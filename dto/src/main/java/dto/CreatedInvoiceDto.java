package com.kodilla.invoice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

}
