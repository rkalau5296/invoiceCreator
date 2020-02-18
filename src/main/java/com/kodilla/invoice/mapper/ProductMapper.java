package com.kodilla.invoice.mapper;

import com.kodilla.invoice.domain.Product;
import com.kodilla.invoice.domain.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ProductMapper {
    public List<Product> mapToListProducts(final List<ProductDto> productDto) {
        return productDto.stream()
                .map(p -> new Product(p.getId(), p.getName(), p.getCode(), p.getPrice_net(), p.getTax()))
                .collect(toList());
    }
    public List<ProductDto> mapToListProductDto(final List<Product> products) {
        return products.stream()
                .map(p -> new ProductDto(p.getId(), p.getName(), p.getCode(), p.getPrice_net(), p.getTax()))
                .collect(toList());
    }
    public Product mapToProduct (final ProductDto productDto) {
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getCode(),
                productDto.getPrice_net(),
                productDto.getTax()
        );
    }
}
