package com.jumia.apiexercise.dto;

import java.util.ArrayList;
import java.util.List;

import com.jumia.apiexercise.domain.Country;

import lombok.Data;

@Data
public class CountryDto {

    private int id;

    private String name;

    public static List<CountryDto> toList(List<Country> list) {
        List<CountryDto> dtoList = new ArrayList<CountryDto>();
        for (Country country : list) {
            dtoList.add(new CountryDto(country));
        }
        return dtoList;
    }

    public CountryDto(Country country) {
        this.id = country.getId();
        this.name = country.getName();
    }
    
}
