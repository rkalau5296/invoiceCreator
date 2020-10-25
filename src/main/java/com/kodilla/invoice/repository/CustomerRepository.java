package com.kodilla.invoice.repository;

import com.kodilla.invoice.domain.ClientDto;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends CrudRepository<ClientDto, Long> {

    @Override
    ClientDto save (ClientDto clientDto);

}
