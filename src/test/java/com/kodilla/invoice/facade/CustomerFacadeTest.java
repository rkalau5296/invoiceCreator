package com.kodilla.invoice.facade;

import com.kodilla.invoice.domain.Client;
import com.kodilla.invoice.domain.ClientDto;
import com.kodilla.invoice.domain.CreatedCustomerDto;
import com.kodilla.invoice.domain.CustomerDto;
import com.kodilla.invoice.mapper.ClientMapper;
import com.kodilla.invoice.repository.ClientDtoRepository;
import com.kodilla.invoice.service.CustomerService;
import com.kodilla.invoice.validator.ClientValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerFacadeTest {

    @InjectMocks
    private CustomerFacade customerFacade;
    @Mock
    private ClientMapper clientMapper;
    @Mock
    private ClientValidator clientValidator;
    @Mock
    private CustomerService customerService;
    @Mock
    private ClientDtoRepository clientDtoRepository;
    @Test
    public void shouldFetchClients() throws Exception {
        //Given
        List<ClientDto> clientsDto = new ArrayList<>();
        clientsDto.add(new ClientDto(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no"));
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no"));

        when(customerService.fetchClients()).thenReturn(clientsDto);
        when(clientMapper.mapToListClients(clientsDto)).thenReturn(clients);
        when(clientMapper.mapToListClientDto(clients)).thenReturn(clientsDto);
        when(clientValidator.validateClients(clients)).thenReturn(clients);
        for(ClientDto clientDtos : clientsDto) {
            when(clientDtoRepository.save(clientDtos)).thenReturn(clientDtos);
        }
        //When

        List<ClientDto> anotherClientsDto = customerFacade.fetchClients();

        //then
        assertNotNull(anotherClientsDto);
        assertEquals(1, anotherClientsDto.size());
        assertEquals("name", anotherClientsDto.get(0).getName());
        assertEquals("tax_no", anotherClientsDto.get(0).getTax_no());
        assertEquals("bank", anotherClientsDto.get(0).getBank());
        assertEquals("bank_account", anotherClientsDto.get(0).getBank_account());
        assertEquals("city", anotherClientsDto.get(0).getCity());
        assertEquals("country", anotherClientsDto.get(0).getCountry());
        assertEquals("email", anotherClientsDto.get(0).getEmail());
        assertEquals("person", anotherClientsDto.get(0).getPerson());
        assertEquals("post_code", anotherClientsDto.get(0).getPost_code());
        assertEquals("phone", anotherClientsDto.get(0).getPhone());
        assertEquals("street", anotherClientsDto.get(0).getStreet());
        assertEquals("street_no", anotherClientsDto.get(0).getStreet_no());

    }

    @Test
    public void shouldFetchClientById() throws Exception {
        //Given
        ClientDto clientDto = new ClientDto(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no");
        when(customerService.fetchClientById(1L)).thenReturn(clientDto);

        //when
        ClientDto anotherClientDto = customerFacade.fetchClientById(1L);
        //Then
        assertNotNull(anotherClientDto);

        assertEquals("name", anotherClientDto.getName());
        assertEquals("tax_no", anotherClientDto.getTax_no());
        assertEquals("bank", anotherClientDto.getBank());
        assertEquals("bank_account", anotherClientDto.getBank_account());
        assertEquals("city", anotherClientDto.getCity());
        assertEquals("country", anotherClientDto.getCountry());
        assertEquals("email", anotherClientDto.getEmail());
        assertEquals("person", anotherClientDto.getPerson());
        assertEquals("post_code", anotherClientDto.getPost_code());
        assertEquals("phone", anotherClientDto.getPhone());
        assertEquals("street", anotherClientDto.getStreet());
        assertEquals("street_no", anotherClientDto.getStreet_no());
    }

    @Test
    public void shouldCreateCustomer() throws Exception{
        //Given
        CreatedCustomerDto createdCustomerDto = new CreatedCustomerDto(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no");
        Client client = new Client(1L, "name", "tax_no", "bank", "bank_account", "city", "country", "email", "person", "post_code", "phone", "street", "street_no");
        CustomerDto customerDto = new CustomerDto(1L, "token_id", client);
        when(customerService.createCustomer(customerDto)).thenReturn(createdCustomerDto);
        //when
        CreatedCustomerDto anotherCreatedCustomerDto = customerFacade.createCustomer(customerDto);
        //Then
        assertNotNull(anotherCreatedCustomerDto);
        assertEquals("name", anotherCreatedCustomerDto.getName());
        assertEquals("tax_no", anotherCreatedCustomerDto.getTax_no());
        assertEquals("bank", anotherCreatedCustomerDto.getBank());
        assertEquals("bank_account", anotherCreatedCustomerDto.getBank_account());
        assertEquals("city", anotherCreatedCustomerDto.getCity());
        assertEquals("country", anotherCreatedCustomerDto.getCountry());
        assertEquals("email", anotherCreatedCustomerDto.getEmail());
        assertEquals("person", anotherCreatedCustomerDto.getPerson());
        assertEquals("post_code", anotherCreatedCustomerDto.getPost_code());
        assertEquals("phone", anotherCreatedCustomerDto.getPhone());
        assertEquals("street", anotherCreatedCustomerDto.getStreet());
        assertEquals("street_no", anotherCreatedCustomerDto.getStreet_no());
    }
}
