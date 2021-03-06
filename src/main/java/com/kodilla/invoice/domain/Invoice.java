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
    private int payment_to_kind;

    @Column
    private int client_id;

    @Column
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InvoicePosition> positions;

}
