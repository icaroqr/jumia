package com.jumia.apiexercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import com.jumia.apiexercise.dto.CustomerDto;
import com.jumia.apiexercise.service.CustomerService;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService service;

    @Test
    public void givenCustomer_whenGetCustomers_thenReturnJsonArray() throws Exception{
        CustomerDto customer = new CustomerDto(0,0,"Jumia Customer","(211) 704244430",null,null);
        List<CustomerDto> customers = new ArrayList<CustomerDto>();
        customers.add(customer);

        given(service.listAll()).willReturn(customers);
        
        mvc.perform(get("/customers/").contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isFound())
           .andExpect(jsonPath("$", hasSize(1)))
           .andExpect(jsonPath("$[0].name", is(customer.getName())));
    }
}
