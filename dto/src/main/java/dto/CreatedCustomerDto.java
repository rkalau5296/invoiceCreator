package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedCustomerDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("tax_no")
    private String tax_no;
    @JsonProperty("bank")
    private String bank;
    @JsonProperty("bank_account")
    private String bank_account;
    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private String country;
    @JsonProperty("email")
    private String email;
    @JsonProperty("person")
    private String person;
    @JsonProperty("post_code")
    private String post_code;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("street")
    private String street;
    @JsonProperty("street_no")
    private String street_no;
}
