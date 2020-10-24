package com.kodilla.invoice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "clients")
public class Client {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(name, client.name) &&
                Objects.equals(tax_no, client.tax_no) &&
                Objects.equals(bank, client.bank) &&
                Objects.equals(bank_account, client.bank_account) &&
                Objects.equals(city, client.city) &&
                Objects.equals(country, client.country) &&
                Objects.equals(email, client.email) &&
                Objects.equals(person, client.person) &&
                Objects.equals(post_code, client.post_code) &&
                Objects.equals(phone, client.phone) &&
                Objects.equals(street, client.street) &&
                Objects.equals(street_no, client.street_no);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tax_no, bank, bank_account, city, country, email, person, post_code, phone, street, street_no);
    }

    @Override
    public String toString() {
        return "name: " + name;
    }
}
