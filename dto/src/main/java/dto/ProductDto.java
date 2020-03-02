package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class ProductDto {

    private Long id;

    private String name;

    private String code;

    private double price_net;

    private String tax;

}
