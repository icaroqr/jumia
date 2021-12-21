package com.jumia.apiexercise.specification;

import com.jumia.apiexercise.domain.Customer;

import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification {
      
    public static Specification<Customer> equalCountryName(String countryName){
        if(countryName == null || countryName.equals("")){
            return null;
        }
        return (root, query, cb) -> {
            return cb.equal(root.join("country").get("name"),countryName);
        };
    } 
    
    public static Specification<Customer> equalCountryState(String state){
        if(state == null || state.equals("")){
            return null;
        }
        return (root, query, cb) -> {
            return cb.equal(root.join("country").get("state"),state);
        };
    }
     
}