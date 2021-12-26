package com.jumia.apiexercise.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import com.jumia.apiexercise.domain.Country;
import com.jumia.apiexercise.domain.CountryFactory;
import com.jumia.apiexercise.dto.CountryDto;
import com.jumia.apiexercise.dto.PhoneDto;
import com.jumia.apiexercise.exception.NotFoundException;
import com.jumia.apiexercise.repository.CountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    
    @Autowired
    private PhoneService phoneService;

    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    public String fillCountryTable(){
        List<PhoneDto> phoneList = phoneService.listAll();
        List<Country> existingCountriesList = countryRepository.findAll();
        Set<Country> newCountriesList = new HashSet<Country>();
        for (PhoneDto phone : phoneList) {
            Country c = CountryFactory.getCountry(phone.getNumber()).get();
            if(!existingCountriesList.contains(c)){
                newCountriesList.add(c);
            }
        }
        countryRepository.saveAll(newCountriesList);
        return "Country table successful filled";
    }

    @Transactional
    public List<CountryDto> listAll() {
        List<Country> list = countryRepository.findAll();
        if(list.size() > 0){
            return CountryDto.toList(list);
        }else{
            throw new NotFoundException("No countries were found.");
        }
    }

    @Transactional
    public Country findByName(String name) {
        Optional<Country> country = countryRepository.findByName(name);
        return country.orElseThrow(()->new NotFoundException("No country were found by name: "+name));
    }
    
}
