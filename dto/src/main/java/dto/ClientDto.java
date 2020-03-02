package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ClientDto {

    private Long id;

    private String name;

    private String tax_no;

    private String bank;

    private String bank_account;

    private String city;

    private String country;

    private String email;

    private String person;

    private String post_code;

    private String phone;

    private String street;

    private String street_no;
}
