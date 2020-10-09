package com.example.frontend.gui;

import com.example.frontend.client.FrontendClient;
import com.example.frontend.config.FrontendConfig;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import dto.ProductDto;
import dto.ProductObjectDto;
import dto.RateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Route
public class ProductDisplayer  extends VerticalLayout{

    private final Grid<ProductDto> rateGrid;
    private final TextField code;
    private final TextField name;
    private final TextField price_net;
    private final TextField tax;
    Button buttonDelete;
    Button buttonUpdate;
    @Autowired
    private FrontendClient frontendClient;
    @Autowired
    private FrontendConfig frontendConfig;
    @Autowired
    public ProductDisplayer() {

        Button getProductButton = new Button("Pobierz produkty z bazy danych.");
        Button getProductButtonFromExternalApi = new Button("Pobierz produkty z fakturownia.pl");
        rateGrid = new Grid<>(ProductDto.class);
        buttonDelete = new Button("USUN");
        buttonUpdate = new Button("EDYTUJ");
        rateGrid.addComponentColumn(this::buttonDelete);
        rateGrid.addComponentColumn(this::buttonUpdate);

        getProductButton.addClickListener(buttonClickEvent-> addToGrid());
        getProductButtonFromExternalApi.addClickListener(buttonClickEvent-> addToGridFromExternalApi());
        code = new TextField("Podaj kod produktu.");
        name = new TextField("Podaj nazwę produktu.");
        price_net = new TextField("Podaj cenę netto produktu.");
        tax = new TextField("Podaj wysokość podatku.");
        Button addProductButton = new Button("Dodaj produkt do bazy fakturownia.pl");
        addProductButton.addClickListener(buttonClickEvent-> frontendClient.postProduct(createProductObjectDto()));
        Button addProductToDbButton = new Button("Dodaj produkt do bazy danych.");
        addProductToDbButton.addClickListener(buttonClickEvent-> frontendClient.postProductToDb(createProductObjectDto()));
        add(getProductButton, getProductButtonFromExternalApi,rateGrid, code, name, price_net,tax, addProductButton, addProductToDbButton);
    }

    public void addToGrid() {
        List<ProductDto> productDtos = frontendClient.getProductsFromDb();
        rateGrid.setItems(productDtos);
    }
    public void addToGridFromExternalApi() {
        List<ProductDto> productDtos = frontendClient.getProducts();
        rateGrid.setItems(productDtos);
    }
    public ProductObjectDto createProductObjectDto() {

        ProductDto productDto = new ProductDto();
        productDto.setCode(code.getValue());
        productDto.setName(name.getValue());
        productDto.setPrice_net(Double.parseDouble(price_net.getValue()));
        productDto.setTax(tax.getValue());

        ProductObjectDto productObjectDto = new ProductObjectDto();
        productObjectDto.setApi_token(frontendConfig.getInvoiceToken());
        productObjectDto.setProduct(productDto);
        return productObjectDto;
    }

    private Button buttonDelete(ProductDto productDto) {

        buttonDelete.addClickListener(e -> deleteProduct(buttonDelete));
        return buttonDelete;
    }

    private Button buttonUpdate(ProductDto productDto) {

        buttonUpdate.addClickListener(e -> updatePerson(buttonUpdate));
        return buttonUpdate;
    }
    private void deleteProduct(Button button) {

        button.addClickListener(buttonClickEvent-> frontendClient.postProductToDb(createProductObjectDto()));
    }
    private void updatePerson(Button button) {

        //rateGrid.setItems();
    }
}
