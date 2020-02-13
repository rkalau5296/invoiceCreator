package com.kodilla.invoice.repository;

import com.kodilla.invoice.domain.ClientDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientDtoRepository extends CrudRepository<ClientDto, Long> {
    @Override
    List<ClientDto> findAll();

    @Override
    Optional<ClientDto> findById(Long id);

    @Override
    ClientDto save (ClientDto clientDto);


    @Override
    void deleteById(Long id);

    @Override
    long count();
}
