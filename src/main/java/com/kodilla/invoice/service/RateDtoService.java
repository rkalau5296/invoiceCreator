package com.kodilla.invoice.service;

import com.kodilla.invoice.client.InvoiceClient;
import com.kodilla.invoice.domain.RateTableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RateDtoService {

    @Autowired
    private InvoiceClient invoiceClient;

    public List<RateTableDto> fetchRates(String table) {
        return invoiceClient.getRates(table);
    }

    public List<RateTableDto> fetchRatesInDateRangeFromTo(String table, String startDate, String endDate) {
        return invoiceClient.getRatesInDateRangeFromTo(table, startDate, endDate);
    }

    public RateTableDto fetchRateAParticularcurrency(String table, String code) {
        return invoiceClient.getRateAPArticularCurrency(table, code);
    }
}
