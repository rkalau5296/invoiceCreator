package com.kodilla.invoice.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "invoicesDto")
public class InvoiceDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //private String kind;
    @Column
    private String seller_name;

    @Column
    private String status;
}
