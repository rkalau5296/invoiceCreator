package com.kodilla.invoice.repository;

import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.domain.Product;
import com.kodilla.invoice.domain.ProductDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductDtoRepository extends CrudRepository<ProductDto, Long> {
    @Override
    List<ProductDto> findAll();

    @Override
    Optional<ProductDto> findById(Long id);

    @Override
    ProductDto save (ProductDto productDto);


    @Override
    void deleteById(Long id);

    @Override
    long count();
}
