package dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomerDto {

    private Long id;
    private String api_token;
    private ClientDto client;
}
