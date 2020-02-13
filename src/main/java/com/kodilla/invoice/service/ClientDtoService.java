package com.kodilla.invoice.service;

import com.kodilla.invoice.client.InvoiceClient;
import com.kodilla.invoice.domain.InvoiceDto;
import com.kodilla.invoice.domain.ClientDto;
import com.kodilla.invoice.repository.ClientDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientDtoService {
    @Autowired
    private ClientDtoRepository repository;
    @Autowired
    private InvoiceClient invoiceClient;

    public List<ClientDto> fetchProducts() {
        return invoiceClient.getClients();
    }
    public ClientDto fetchProductById(Long id) {
        return invoiceClient.getClientById(id);
    }
    public List<ClientDto> getAllInvoices() {
        return repository.findAll();
    }

    public InvoiceDto getInvoiceById(Long id){
        return invoiceClient.getInvoicesById(id);
    }

    public ClientDto saveProduct(final ClientDto clientDto) {
        return repository.save(clientDto);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}