package com.kodilla.invoice.repository;

import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.CreateClient;
import org.springframework.data.repository.CrudRepository;

public interface CreateClientRepository extends CrudRepository<CreateClient, Long> {
    @Override
    CreateClient save (CreateClient client);
}
