package com.kodilla.invoice.repository;

import com.kodilla.invoice.domain.ProductDto;
import org.springframework.data.repository.CrudRepository;

public interface ProductDtoRepository extends CrudRepository<ProductDto, Long> {

    @Override
    ProductDto save (ProductDto productDto);

    @Override
    void deleteById(Long id);

}
