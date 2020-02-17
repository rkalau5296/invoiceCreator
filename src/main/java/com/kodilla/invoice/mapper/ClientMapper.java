package com.kodilla.invoice.mapper;

import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.ClientDto;
import com.kodilla.invoice.domain.CreateClient;
import com.kodilla.invoice.domain.CreateClientDto;
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
    public CreateClient mapToCreateClient (final CreateClientDto clientDto) {
        return new CreateClient(1L,"kq5eOa6IijlkjpvHV9P/konto-testowe-rk-kodilla", clientDto.getClient());
    }
    public ClientDto mapToClientDto (final Client p) {
        return new ClientDto(
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
