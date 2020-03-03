package com.example.frontend.gui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;


@Route("MainPage")
public class MainPage extends HorizontalLayout{

    public MainPage() {

        Button buttonClient = new Button("Klient", new Icon(VaadinIcon.ACADEMY_CAP));
        Button buttonProduct = new Button("Produkt", new Icon(VaadinIcon.ACADEMY_CAP));
        Button buttonInvoice = new Button("Faktura", new Icon(VaadinIcon.ACADEMY_CAP));
        Button buttonExchangeRates = new Button("Kursy Walut", new Icon(VaadinIcon.ACADEMY_CAP));

        buttonExchangeRates.addClickListener(buttonClickEvent -> openRateTableDisplayerPage());
        buttonProduct.addClickListener(buttonClickEvent -> openProductDisplayerPage());
        add(buttonClient, buttonProduct, buttonInvoice, buttonExchangeRates);

    }
    public void openRateTableDisplayerPage() {
        UI.getCurrent().navigate("ratetabledisplayer");
    }
    public void openProductDisplayerPage() {
        UI.getCurrent().navigate("productdisplayer");
    }
}
