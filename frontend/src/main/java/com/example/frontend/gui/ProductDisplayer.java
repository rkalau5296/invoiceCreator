package com.example.frontend.gui;

import com.example.frontend.client.FrontendClient;
import com.example.frontend.config.FrontendConfig;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import dto.ProductDto;
import dto.ProductObjectDto;
import dto.RateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Route
public class ProductDisplayer  extends VerticalLayout{

    private Grid<ProductDto> rateGrid;
    private TextField code;
    private TextField name;
    private TextField price_net;
    private TextField tax;
    private Button addProductButton;
    private Button addProductToDbButton;

    @Autowired
    private FrontendClient frontendClient;
    @Autowired
    private FrontendConfig frontendConfig;
    @Autowired
    public ProductDisplayer() {
        Button getProductButton = new Button("Pobierz produkty z bazy danych.");
        Button getProductButtonFromExternalApi = new Button("Pobierz produkty z fakturownia.pl");
        rateGrid = new Grid<>(ProductDto.class);
        getProductButton.addClickListener(buttonClickEvent-> addToGrid());
        getProductButtonFromExternalApi.addClickListener(buttonClickEvent-> addToGridFromExternalApi());
        code = new TextField("Podaj kod produktu.");
        name = new TextField("Podaj nazwę produktu.");
        price_net = new TextField("Podaj cenę netto produktu.");
        tax = new TextField("Podaj wysokość podatku.");
        addProductButton = new Button("Dodaj produkt do bazy fakturownia.pl");
        addProductButton.addClickListener(buttonClickEvent-> addProductToFakturowniaPl());
        addProductToDbButton = new Button("Dodaj produkt do bazy danych.");
        addProductToDbButton.addClickListener(buttonClickEvent-> addProductToDb());
        add(getProductButton, getProductButtonFromExternalApi,rateGrid, code, name, price_net,tax, addProductButton,addProductToDbButton);
    }

    public void addToGrid() {
        List<ProductDto> productDtos = frontendClient.getProductsFromDb();
        rateGrid.setItems(productDtos);
    }
    public void addToGridFromExternalApi() {
        List<ProductDto> productDtos = frontendClient.getProducts();
        rateGrid.setItems(productDtos);
    }
    public void addProductToFakturowniaPl() {

        ProductDto productDto = new ProductDto();
        productDto.setCode(code.getValue());
        productDto.setName(name.getValue());
        productDto.setPrice_net(Double.parseDouble(price_net.getValue()));
        productDto.setTax(tax.getValue());

        ProductObjectDto productObjectDto = new ProductObjectDto();
        productObjectDto.setApi_token(frontendConfig.getInvoiceToken());
        productObjectDto.setProduct(productDto);
        frontendClient.postProduct(productObjectDto);
    }
    public void addProductToDb() {
        ProductDto productDto = new ProductDto();
        productDto.setCode(code.getValue());
        productDto.setName(name.getValue());
        productDto.setPrice_net(Double.parseDouble(price_net.getValue()));
        productDto.setTax(tax.getValue());

        ProductObjectDto productObjectDto = new ProductObjectDto();
        productObjectDto.setApi_token(frontendConfig.getInvoiceToken());
        productObjectDto.setProduct(productDto);
        frontendClient.postProductToDb(productObjectDto);

    }
}
