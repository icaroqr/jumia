package com.jumia.apiexercise.controller;

import java.util.List;

import com.jumia.apiexercise.dto.CustomerDto;
import com.jumia.apiexercise.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "customers")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    @GetMapping(value="/", produces="application/json")
    public ResponseEntity<List<CustomerDto>> findAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(customerService.listAll());
    }
}
