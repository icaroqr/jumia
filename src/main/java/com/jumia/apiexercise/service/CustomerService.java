package com.jumia.apiexercise.service;

import java.util.List;

import javax.transaction.Transactional;

import com.jumia.apiexercise.domain.Customer;
import com.jumia.apiexercise.dto.CustomerDto;
import com.jumia.apiexercise.exception.NotFoundException;
import com.jumia.apiexercise.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public List<CustomerDto> listAll(){
        List<Customer> list = customerRepository.findAll();
        if(list.size() > 0){
            return CustomerDto.toList(list);
        }else{
            throw new NotFoundException("No customers were found.");
        }
    }
}
