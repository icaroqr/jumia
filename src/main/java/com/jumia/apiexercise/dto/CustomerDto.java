package com.jumia.apiexercise.dto;

import java.util.ArrayList;
import java.util.List;

import com.jumia.apiexercise.domain.Customer;

import lombok.Data;
@Data
public class CustomerDto {

    private int id;

    private int id_country;

    private String name;

    //@NotBlank
    private String phone;

    private String countryName;

    private String state;

    public CustomerDto(Customer customer){
        this.id = customer.getId();
        this.name = customer.getName();
        this.phone = customer.getPhone();
        this.id_country = customer.getCountry() != null ? customer.getCountry().getId() : 0;
        this.countryName = customer.getCountry() != null ? customer.getCountry().getName() : "";
        this.state = customer.getCountry() != null ? customer.getCountry().getState() : "";
    }

    public static List<CustomerDto> toList(List<Customer> customerList){
        List<CustomerDto> dtoList = new ArrayList<CustomerDto>();
        for (Customer customer : customerList) {
            dtoList.add(new CustomerDto(customer));
        }
        return dtoList;
    }
}
