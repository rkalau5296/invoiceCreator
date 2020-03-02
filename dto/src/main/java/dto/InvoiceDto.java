package dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public class InvoiceDto {


    private Long id;


    private int payment_to_kind;


    private int client_id;


    private List<InvoicePosition> positions;
}
