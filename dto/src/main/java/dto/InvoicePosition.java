package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class InvoicePosition {

    private Long id;

    private int product_id;

    private int quantity;
}
