package com.kodilla.invoice.repository;

import com.kodilla.invoice.domain.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {
    @Override
    List<Client> findAll();

    @Override
    Optional<Client> findById(Long id);

    @Override
    Client save (Client client);


    @Override
    void deleteById(Long id);

    @Override
    long count();
}
