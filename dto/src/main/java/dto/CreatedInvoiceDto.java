package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class CreatedInvoiceDto {


    @JsonProperty("id")
    private Long id;


    @JsonProperty("price_net")
    private String price_net;


    @JsonProperty("price_gross")
    private String price_gross;


    @JsonProperty("buyer_name")
    private String buyer_name;


    @JsonProperty("product_cache")
    private String product_cache;

}
