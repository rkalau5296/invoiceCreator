package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InvoiceObjectDto {

    private Long id;
    private String api_token;
    private Invoice invoice;
}
