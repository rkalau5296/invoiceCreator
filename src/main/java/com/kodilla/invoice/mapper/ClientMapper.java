package com.kodilla.invoice.mapper;

import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.ClientDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ClientMapper {
    public List<Client> mapToListClients(final List<ClientDto> clientDtos) {
        return clientDtos.stream()
                .map(p -> new Client(p.getId(), p.getSeller_name(), p.getStatus()))
                .collect(toList());
    }
    public List<ClientDto> mapToListClientDto(final List<Client> client) {
        return client.stream()
                .map(p -> new ClientDto(p.getId(), p.getSeller_name(), p.getStatus()))
                .collect(toList());
    }
    public Client mapToClient (final ClientDto clientDto) {
        return new Client(
                clientDto.getId(),
                clientDto.getSeller_name(),
                clientDto.getStatus()
        );
    }
    public ClientDto mapToClientDto (final Client client) {
        return new ClientDto(
                client.getId(),
                client.getSeller_name(),
                client.getStatus()
        );
    }
}
