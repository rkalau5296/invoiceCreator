package com.kodilla.invoice.mapper;

import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.ClientDto;
import com.kodilla.invoice.domain.CustomerDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ClientMapperTest {

    @Autowired
    private ClientMapper clientMapper;

    @Test
    public void mapToListClientsTest() {
        //Given
        List<ClientDto> clientDto = new ArrayList<>();
        clientDto.add(new ClientDto(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no"));
        //When
        List<Client> clientList = clientMapper.mapToListClients(clientDto);
        //Then
        assertEquals(clientDto.get(0).getName(), clientList.get(0).getName());
        assertEquals(clientDto.get(0).getTax_no(), clientList.get(0).getTax_no());
        assertEquals(clientDto.get(0).getBank(), clientList.get(0).getBank());
        assertEquals(clientDto.get(0).getBank_account(), clientList.get(0).getBank_account());
        assertEquals(clientDto.get(0).getCity(), clientList.get(0).getCity());
        assertEquals(clientDto.get(0).getCountry(), clientList.get(0).getCountry());
        assertEquals(clientDto.get(0).getEmail(), clientList.get(0).getEmail());
        assertEquals(clientDto.get(0).getPerson(), clientList.get(0).getPerson());
        assertEquals(clientDto.get(0).getPost_code(), clientList.get(0).getPost_code());
        assertEquals(clientDto.get(0).getPhone(), clientList.get(0).getPhone());
        assertEquals(clientDto.get(0).getStreet(), clientList.get(0).getStreet());
        assertEquals(clientDto.get(0).getStreet_no(), clientList.get(0).getStreet_no());
    }
    @Test
    public void mapToListClientDtoTest() {
        //Given
        List<Client> client = new ArrayList<>();
        client.add(new Client(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no"));
        //When
        List<ClientDto> clientDtoList = clientMapper.mapToListClientDto(client);
        //Then
        assertEquals(clientDtoList.get(0).getName(), client.get(0).getName());
        assertEquals(clientDtoList.get(0).getTax_no(), client.get(0).getTax_no());
        assertEquals(clientDtoList.get(0).getBank(), client.get(0).getBank());
        assertEquals(clientDtoList.get(0).getBank_account(), client.get(0).getBank_account());
        assertEquals(clientDtoList.get(0).getCity(), client.get(0).getCity());
        assertEquals(clientDtoList.get(0).getCountry(), client.get(0).getCountry());
        assertEquals(clientDtoList.get(0).getEmail(), client.get(0).getEmail());
        assertEquals(clientDtoList.get(0).getPerson(), client.get(0).getPerson());
        assertEquals(clientDtoList.get(0).getPost_code(), client.get(0).getPost_code());
        assertEquals(clientDtoList.get(0).getPhone(), client.get(0).getPhone());
        assertEquals(clientDtoList.get(0).getStreet(), client.get(0).getStreet());
        assertEquals(clientDtoList.get(0).getStreet_no(), client.get(0).getStreet_no());
    }
    @Test
    public void mapToClientFromCustomerDtoTest() {
        //Given
        Client client = new Client(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no");
        CustomerDto customerDto = new CustomerDto(1L, "api_token", client);
        //When
        Client anotherClient = clientMapper.mapToClientFromCustomerDto(customerDto);
        //Then
        assertEquals(anotherClient.getName(), customerDto.getClient().getName());
        assertEquals(anotherClient.getTax_no(), customerDto.getClient().getTax_no());
        assertEquals(anotherClient.getBank(), customerDto.getClient().getBank());
        assertEquals(anotherClient.getBank_account(), customerDto.getClient().getBank_account());
        assertEquals(anotherClient.getCity(), customerDto.getClient().getCity());
        assertEquals(anotherClient.getCountry(), customerDto.getClient().getCountry());
        assertEquals(anotherClient.getEmail(), customerDto.getClient().getEmail());
        assertEquals(anotherClient.getPerson(), customerDto.getClient().getPerson());
        assertEquals(anotherClient.getPost_code(), customerDto.getClient().getPost_code());
        assertEquals(anotherClient.getPhone(), customerDto.getClient().getPhone());
        assertEquals(anotherClient.getStreet(), customerDto.getClient().getStreet());
        assertEquals(anotherClient.getStreet_no(), customerDto.getClient().getStreet_no());
    }
    @Test
    public void mapToClientTest() {
        //Given
        ClientDto clientDto = new ClientDto(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no");

        //When
        Client anotherClient = clientMapper.mapToClient(clientDto);
        //Then
        assertEquals(anotherClient.getName(), clientDto.getName());
        assertEquals(anotherClient.getTax_no(), clientDto.getTax_no());
        assertEquals(anotherClient.getBank(), clientDto.getBank());
        assertEquals(anotherClient.getBank_account(), clientDto.getBank_account());
        assertEquals(anotherClient.getCity(), clientDto.getCity());
        assertEquals(anotherClient.getCountry(), clientDto.getCountry());
        assertEquals(anotherClient.getEmail(), clientDto.getEmail());
        assertEquals(anotherClient.getPerson(), clientDto.getPerson());
        assertEquals(anotherClient.getPost_code(), clientDto.getPost_code());
        assertEquals(anotherClient.getPhone(), clientDto.getPhone());
        assertEquals(anotherClient.getStreet(), clientDto.getStreet());
        assertEquals(anotherClient.getStreet_no(), clientDto.getStreet_no());
    }

}
