package com.jumia.apiexercise.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jumia.apiexercise.domain.Customer;
import com.jumia.apiexercise.dto.CustomerDto;
import com.jumia.apiexercise.exception.NotFoundException;
import com.jumia.apiexercise.repository.CustomerRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.anyInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceTests {

    @TestConfiguration
    static class CustomerServiceImplTestContextConfiguration {
        @Bean
        public CustomerService costumerService() {
            return new CustomerService();
        }
    }

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Before
    public void setUp() {
        Customer jumiaCustomer = new Customer(0,"Jumia Customer",null);
        Customer icaroCustomer = new Customer(99,"Icaro Customer",null);
        
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(jumiaCustomer);
        customers.add(icaroCustomer);
        
        when(customerRepository.findByName(jumiaCustomer.getName())).thenReturn(Optional.of(jumiaCustomer));
        when(customerRepository.findByName("invalid_name")).thenReturn(Optional.empty());
        when(customerRepository.findById(icaroCustomer.getId())).thenReturn(Optional.of(icaroCustomer));
        when(customerRepository.findAll()).thenReturn(customers);
    }

    @Test
    public void whenValidName_thenCustomerShouldBeFound() {
        String name = "Jumia Customer";
        Customer found = customerService.getCustomerByName(name);

        assertThat(found.getName()).isEqualTo(name);
    }

    @Test(expected = NotFoundException.class)
    public void whenInValidName_thenCustomerShouldNotBeFound() {
        Customer fromDb = customerService.getCustomerByName("invalid_name");
        assertThat(fromDb).isNull();

        verifyFindByNameIsCalledOnce("invalid_name");
    }


    @Test
    public void whenValidId_thenCustomerShouldBeFound() {
        Customer fromDb = customerService.getCustomerById(99);
        assertThat(fromDb.getName()).isEqualTo("Icaro Customer");

        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void given3Customers_whengetAll_thenReturn2Records() {
        Customer jumiaCustomer = new Customer(0,"Jumia Customer",null);
        Customer icaroCustomer = new Customer(99,"Icaro Customer",null);
        
        List<CustomerDto> allCustomers = customerService.listAll();
        verifyFindAllCustomersIsCalledOnce();
        assertThat(allCustomers).hasSize(2).extracting(CustomerDto::getName).contains(jumiaCustomer.getName(), icaroCustomer.getName());
    }

    private void verifyFindByNameIsCalledOnce(String name) {
        verify(customerRepository, VerificationModeFactory.times(1)).findByName(name);
        reset(customerRepository);
    }

    private void verifyFindByIdIsCalledOnce() {
        verify(customerRepository, VerificationModeFactory.times(1)).findById(anyInt());
        reset(customerRepository);
    }

    private void verifyFindAllCustomersIsCalledOnce() {
        verify(customerRepository, VerificationModeFactory.times(1)).findAll();
        reset(customerRepository);
    }
    
}
