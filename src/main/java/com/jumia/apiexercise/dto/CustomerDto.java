package com.jumia.apiexercise.dto;

import java.util.ArrayList;
import java.util.List;

import com.jumia.apiexercise.domain.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class CustomerDto {

    private int id;
    
    private String name;

    private List<PhoneDto> phones;

    public CustomerDto(Customer customer){
        this.id = customer.getId();
        this.name = customer.getName();
        this.phones = PhoneDto.toList(customer.getPhones());
    }

    public static List<CustomerDto> toList(List<Customer> customerList){
        List<CustomerDto> dtoList = new ArrayList<CustomerDto>();
        for (Customer customer : customerList) {
            dtoList.add(new CustomerDto(customer));
        }
        return dtoList;
    }
}
