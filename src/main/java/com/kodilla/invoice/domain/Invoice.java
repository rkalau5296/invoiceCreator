package com.kodilla.invoice.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String kind;
    @Column
    private String number;
    @Column
    private String sell_date;
    @Column
    private String issue_date;
    @Column
    private String payment_to;
    @Column
    private String seller_name;
    @Column
    private String seller_tax_no;
    @Column
    private String buyer_name;
    @Column
    private String buyer_tax_no;
    @OneToMany
    @Column
    private List<Product> positions;

}
