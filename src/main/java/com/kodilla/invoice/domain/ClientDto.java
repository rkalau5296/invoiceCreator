package com.kodilla.invoice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "clientsDto")
public class ClientDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String tax_no;

    @Column
    private String bank;

    @Column
    private String bank_account;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private String email;

    @Column
    private String person;

    @Column
    private String post_code;

    @Column
    private String phone;

    @Column
    private String street;

    @Column
    private String street_no;
}
