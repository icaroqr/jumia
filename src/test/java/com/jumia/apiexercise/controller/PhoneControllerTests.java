package com.jumia.apiexercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import com.jumia.apiexercise.dto.PhoneDto;
import com.jumia.apiexercise.service.PhoneService;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PhoneController.class)
public class PhoneControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PhoneService service;

    @Test
    public void givenPhone_whenGetPhones_thenReturnJsonArray() throws Exception{
        PhoneDto phone = new PhoneDto(0,"(258) 849181828","valid",null);
        List<PhoneDto> phones = new ArrayList<PhoneDto>();
        phones.add(phone);

        given(service.listAll()).willReturn(phones);
        
        mvc.perform(get("/phones/").contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isFound())
           .andExpect(jsonPath("$", hasSize(1)))
           .andExpect(jsonPath("$[0].number", is(phone.getNumber())));
    }
}
