package com.jumia.apiexercise.service;

import java.util.List;

import javax.transaction.Transactional;

import com.jumia.apiexercise.domain.Country;
import com.jumia.apiexercise.domain.Customer;
import com.jumia.apiexercise.dto.CustomerDto;
import com.jumia.apiexercise.exception.NotFoundException;
import com.jumia.apiexercise.model.PageModel;
import com.jumia.apiexercise.model.PageRequestModel;
import com.jumia.apiexercise.repository.CountryRepository;
import com.jumia.apiexercise.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    public List<CustomerDto> listAll(){
        List<Customer> list = customerRepository.findAll();
        if(list.size() > 0){
            return CustomerDto.toList(list);
        }else{
            throw new NotFoundException("No customers were found.");
        }
    }

    @Transactional
    public PageModel<CustomerDto> listAllPagingOrderingAndFiltering(PageRequestModel pageModel){
        Pageable pageable = pageModel.toSpringPageRequest();
        Page<Customer> page = customerRepository.findAll(pageable);
        if(!page.isEmpty()){
            return new PageModel<CustomerDto>((int) page.getTotalElements(), page.getSize(), 
                                                page.getTotalPages(),CustomerDto.toList(page.getContent()));
        }else{
            throw new NotFoundException("No customers were found.");
        }
    }

    @Transactional
    public String fillCustomerCountry() {
        List<Customer> customerList = customerRepository.findAll();
        for (Customer customer : customerList) {
            Country country = new Country(customer.getPhone());
            customer.setCountry(countryRepository.findByName(country.getName()).get());
            customerRepository.save(customer);
        }
        return "Customers Country updated";
    }
}
