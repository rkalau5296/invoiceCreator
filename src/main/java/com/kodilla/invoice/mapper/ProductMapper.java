package com.kodilla.invoice.mapper;

import com.kodilla.invoice.domain.Invoice;
import com.kodilla.invoice.domain.InvoiceDto;
import com.kodilla.invoice.domain.Product;
import com.kodilla.invoice.domain.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ProductMapper {
    public List<Product> mapToListProducts(final List<ProductDto>  productDtos) {
        return productDtos.stream()
                .map(p -> new Product(p.getId(), p.getSeller_name(), p.getStatus()))
                .collect(toList());
    }
    public List<ProductDto> mapToListProductDto(final List<Product> product) {
        return product.stream()
                .map(p -> new ProductDto(p.getId(), p.getSeller_name(), p.getStatus()))
                .collect(toList());
    }
    public Product mapToProduct (final ProductDto productDto) {
        return new Product(
                productDto.getId(),
                productDto.getSeller_name(),
                productDto.getStatus()
        );
    }
    public ProductDto mapToInvoiceDto (final Product product) {
        return new ProductDto(
                product.getId(),
                product.getSeller_name(),
                product.getStatus()
        );
    }
}
