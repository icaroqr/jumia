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
import com.jumia.apiexercise.specification.CustomerSpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public PageModel<CustomerDto> listAllFilteringPagingAndOrdering(PageRequestModel pageRequestModel){
        Pageable pageable = pageRequestModel.toSpringPageRequest();
        
        Specification<Customer> spec = Specification.where(
            CustomerSpecification.equalCountryName(pageRequestModel.getCountry())).and(
            CustomerSpecification.equalCountryState(pageRequestModel.getState()));
        
            Page<Customer> page = customerRepository.findAll(spec,pageable);
        if(!page.isEmpty()){
            return new PageModel<CustomerDto>(
                                        (int) page.getTotalElements(),
                                        page.getSize(), 
                                        page.getTotalPages(),
                                        CustomerDto.toList(page.getContent()));
        }else{
            throw new NotFoundException("No customers were found.");
        }
    }

    @Transactional
    public String fillCustomerCountry() {
        List<Customer> customerList = customerRepository.findAll();
        for (Customer customer : customerList) {
            if(customer.getCountry() == null){
                Country country = new Country(customer.getPhone());
                customer.setCountry(countryRepository.findByName(country.getName()).get());
                customerRepository.save(customer);
            }
        }
        return "Customers Country updated";
    }

    public Customer getCustomerByName(String name) {
        return customerRepository.findByName(name).orElseThrow(()-> new NotFoundException("No customer found with name: "+name));
    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id).orElseThrow(()-> new NotFoundException("No customer found with id: "+id));
    }
}
