package com.kodilla.invoice.mapper;

import com.kodilla.invoice.config.InvoiceConfig;
import com.kodilla.invoice.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ClientMapper {

    public List<Client> mapToListClients(final List<ClientDto> clientDto) {
        return clientDto.stream()
                .map(p -> new Client( p.getId(),
                        p.getName(),
                        p.getTax_no(),
                        p.getBank(),
                        p.getBank_account(),
                        p.getCity(),
                        p.getCountry(),
                        p.getEmail(),
                        p.getPerson(),
                        p.getPost_code(),
                        p.getPhone(),
                        p.getStreet(),
                        p.getStreet_no()))
                .collect(toList());
    }
    public List<ClientDto> mapToListClientDto(final List<Client> client) {
        return client.stream()
                .map(p -> new ClientDto(
                        p.getId(),
                        p.getName(),
                        p.getTax_no(),
                        p.getBank(),
                        p.getBank_account(),
                        p.getCity(),
                        p.getCountry(),
                        p.getEmail(),
                        p.getPerson(),
                        p.getPost_code(),
                        p.getPhone(),
                        p.getStreet(),
                        p.getStreet_no()))
                .collect(toList());
    }
    public Client mapToClientFromCustomerDto(final CustomerDto clientDto) {
        return new Client(
                clientDto.getClient().getId(),
                clientDto.getClient().getName(),
                clientDto.getClient().getTax_no(),
                clientDto.getClient().getBank(),
                clientDto.getClient().getBank_account(),
                clientDto.getClient().getCity(),
                clientDto.getClient().getCountry(),
                clientDto.getClient().getEmail(),
                clientDto.getClient().getPerson(),
                clientDto.getClient().getPost_code(),
                clientDto.getClient().getPhone(),
                clientDto.getClient().getStreet(),
                clientDto.getClient().getStreet_no()
        );
    }

    public Client mapToClient (final ClientDto p) {
        return new Client(
                p.getId(),
                p.getName(),
                p.getTax_no(),
                p.getBank(),
                p.getBank_account(),
                p.getCity(),
                p.getCountry(),
                p.getEmail(),
                p.getPerson(),
                p.getPost_code(),
                p.getPhone(),
                p.getStreet(),
                p.getStreet_no()
        );
    }

}
