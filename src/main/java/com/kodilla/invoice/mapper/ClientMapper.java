package com.kodilla.invoice.mapper;

import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.ClientDto;
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
    public Client mapToClient (final ClientDto clientDto) {
        return new Client(
                clientDto.getId(),
                clientDto.getName(),
                clientDto.getTax_no(),
                clientDto.getBank(),
                clientDto.getBank_account(),
                clientDto.getCity(),
                clientDto.getCountry(),
                clientDto.getEmail(),
                clientDto.getPerson(),
                clientDto.getPost_code(),
                clientDto.getPhone(),
                clientDto.getStreet(),
                clientDto.getStreet_no());
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
}
