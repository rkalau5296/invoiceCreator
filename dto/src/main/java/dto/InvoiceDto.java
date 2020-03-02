package dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "invoicesDto")
public class InvoiceDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int payment_to_kind;

    @Column
    private int client_id;

    @Column
    @OneToMany
    private List<InvoicePosition> positions;
}
