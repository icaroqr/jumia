package com.jumia.apiexercise.dto;

import java.util.ArrayList;
import java.util.List;

import com.jumia.apiexercise.domain.Customer;

import lombok.Data;
@Data
public class CustomerDto {

    private int id;

    private String name;

    //@NotBlank
    private String phone;

    private String country;

    private String countryCode;

    private String state;

    public CustomerDto(Customer customer){
        this.id = customer.getId();
        this.name = customer.getName();
        this.phone = customer.getPhone();

        CountryDto countryDto = new CountryDto(customer.getPhone());
        this.country = countryDto.getCountry();
        this.countryCode = countryDto.getCountryCode();
        this.state = countryDto.getState();
    }

    public static List<CustomerDto> toList(List<Customer> customerList){
        List<CustomerDto> dtoList = new ArrayList<CustomerDto>();
        for (Customer customer : customerList) {
            dtoList.add(new CustomerDto(customer));
        }
        return dtoList;
    }
}
