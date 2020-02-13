package com.kodilla.invoice.service;

import com.kodilla.invoice.client.InvoiceClient;
import com.kodilla.invoice.domain.RateDto;
import com.kodilla.invoice.domain.RateTableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateDtoService {

    @Autowired
    private InvoiceClient invoiceClient;

    public List<RateTableDto> fetchRates() {
        return invoiceClient.getRates();
    }
}
