package com.example.frontend.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;


@Route("hello")
public class HelloGui extends HorizontalLayout{

    public HelloGui() {
        //TextField textField = new TextField("Tekścior");
        Button buttonClient = new Button("Klient", new Icon(VaadinIcon.ACADEMY_CAP));
        Button buttonProduct = new Button("Produkt", new Icon(VaadinIcon.ACADEMY_CAP));
        Button buttonInvoice = new Button("Faktura", new Icon(VaadinIcon.ACADEMY_CAP));
        Button buttonExchangeRates = new Button("Kursy Walut", new Icon(VaadinIcon.ACADEMY_CAP));
        //Label label = new Label();

        add(buttonClient, buttonProduct, buttonInvoice, buttonExchangeRates);
    }
}
