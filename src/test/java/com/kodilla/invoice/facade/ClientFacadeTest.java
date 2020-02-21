package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.ClientDto;
import com.kodilla.invoice.domain.Customer;
import com.kodilla.invoice.domain.CustomerDto;
import com.kodilla.invoice.mapper.ClientMapper;
import com.kodilla.invoice.service.ClientService;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientFacadeTest {
    @InjectMocks
    private ClientFacade clientFacade;
    @Mock
    private ClientMapper clientMapper;
    @Mock
    private ClientService clientService;

    @Test
    public void shouldGetAllClientsFromDb() {
        //Given
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no"));

        when(clientFacade.getAllClientsFromDb()).thenReturn(clients);

        //When
        List<Client> clientList =  clientFacade.getAllClientsFromDb();
        assertNotNull(clientList);
        assertEquals(1, clientList.size());
        assertEquals("name", clientList.get(0).getName());
        assertEquals("tax_no", clientList.get(0).getTax_no());
        assertEquals("bank", clientList.get(0).getBank());
        assertEquals("bank_account", clientList.get(0).getBank_account());
        assertEquals("city", clientList.get(0).getCity());
        assertEquals("country", clientList.get(0).getCountry());
        assertEquals("email", clientList.get(0).getEmail());
        assertEquals("person", clientList.get(0).getPerson());
        assertEquals("post_code", clientList.get(0).getPost_code());
        assertEquals("phone", clientList.get(0).getPhone());
        assertEquals("street", clientList.get(0).getStreet());
        assertEquals("street_no", clientList.get(0).getStreet_no());

    }
    @Test
    public void shouldGetClientFromDbById() {
        //Given
        Client client = new Client(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no");
        when(clientFacade.getClientFromDbById(1L)).thenReturn(client);

        //When
        Client anotherClient =  clientFacade.getClientFromDbById(1L);
        assertNotNull(anotherClient);

        assertEquals("name", anotherClient.getName());
        assertEquals("tax_no", anotherClient.getTax_no());
        assertEquals("bank", anotherClient.getBank());
        assertEquals("bank_account", anotherClient.getBank_account());
        assertEquals("city", anotherClient.getCity());
        assertEquals("country", anotherClient.getCountry());
        assertEquals("email", anotherClient.getEmail());
        assertEquals("person", anotherClient.getPerson());
        assertEquals("post_code", anotherClient.getPost_code());
        assertEquals("phone", anotherClient.getPhone());
        assertEquals("street", anotherClient.getStreet());
        assertEquals("street_no", anotherClient.getStreet_no());

    }

    @Test
    public void shouldCreateClient() throws Exception{
        //Given
        Client client = new Client(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no");
        CustomerDto customerDto = new CustomerDto(1L, "api_token", client);
        when(clientFacade.createClient(customerDto)).thenReturn(client);

        //When
        Client anotherClient = clientFacade.createClient(customerDto);
        //Then
        assertNotNull(anotherClient);
        assertEquals("name", anotherClient.getName());
        assertEquals("tax_no", anotherClient.getTax_no());
        assertEquals("bank", anotherClient.getBank());
        assertEquals("bank_account", anotherClient.getBank_account());
        assertEquals("city", anotherClient.getCity());
        assertEquals("country", anotherClient.getCountry());
        assertEquals("email", anotherClient.getEmail());
        assertEquals("person", anotherClient.getPerson());
        assertEquals("post_code", anotherClient.getPost_code());
        assertEquals("phone", anotherClient.getPhone());
        assertEquals("street", anotherClient.getStreet());
        assertEquals("street_no", anotherClient.getStreet_no());
    }
    @Test
    public void shouldUpdateClient() throws Exception {
        //Given
        ClientDto clientDto = new ClientDto(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no");
        Client client = new Client(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no");
        when(clientFacade.updateClient(clientDto)).thenReturn(client);

        //When
        Client anotherClient = clientFacade.updateClient(clientDto);
        //Then
        assertNotNull(anotherClient);
        assertEquals("name", anotherClient.getName());
        assertEquals("tax_no", anotherClient.getTax_no());
        assertEquals("bank", anotherClient.getBank());
        assertEquals("bank_account", anotherClient.getBank_account());
        assertEquals("city", anotherClient.getCity());
        assertEquals("country", anotherClient.getCountry());
        assertEquals("email", anotherClient.getEmail());
        assertEquals("person", anotherClient.getPerson());
        assertEquals("post_code", anotherClient.getPost_code());
        assertEquals("phone", anotherClient.getPhone());
        assertEquals("street", anotherClient.getStreet());
        assertEquals("street_no", anotherClient.getStreet_no());
    }

}
