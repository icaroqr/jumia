package com.jumia.apiexercise.controller;

import java.util.List;

import com.jumia.apiexercise.dto.CountryDto;
import com.jumia.apiexercise.service.CountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "countries")
public class CountryController {
    
    @Autowired
    private CountryService countryService;

    @GetMapping(value = "/", produces="application/json")
    public ResponseEntity<List<CountryDto>> findAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(countryService.listAll());
    }

    @PostMapping(value = "/fillCountryTable", produces="text")
	public ResponseEntity<String> fillCountryTable() {
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.fillCountryTable());
	}
}
