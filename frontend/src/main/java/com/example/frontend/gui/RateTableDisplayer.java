package com.example.frontend.gui;

import com.example.frontend.client.FrontendClient;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import dto.RateDto;
import dto.RateTable;
import dto.RateTableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Route
public class RateTableDisplayer extends VerticalLayout {

    private TextField textField;
    private Grid<RateTable> tableGrid;
    private Grid<RateDto> rateGrid;
    @Autowired
    private FrontendClient frontendClient;
    @Autowired
    public RateTableDisplayer()
    {
        textField = new TextField("Podaj tabelę.");
        Button buttonGetRates = new Button("Pobierz kursy walut.");
        tableGrid = new Grid<>(RateTable.class);
        rateGrid = new Grid<>(RateDto.class);
        buttonGetRates.addClickListener(buttonClickEvent -> addRatesToGrid());

        add(textField, buttonGetRates, tableGrid, rateGrid);
    }

    public void addRatesToGrid(){

        RateTable rateTable = new RateTable();
        rateTable.setTable(textField.getValue());
        List<RateTableDto> rateTableDtos = frontendClient.getRates(textField.getValue());
        rateTable.setNo(rateTableDtos.get(0).getNo());
        rateTable.setEffectiveDate(rateTableDtos.get(0).getEffectiveDate());
        List<RateDto> rateDtoList = rateTableDtos.get(0).getRates();
        rateGrid.setItems(rateDtoList);
        tableGrid.setItems(rateTable);
    }
}
