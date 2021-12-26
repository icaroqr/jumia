package com.jumia.apiexercise.specification;

import com.jumia.apiexercise.domain.Country_;
import com.jumia.apiexercise.domain.Customer;
import com.jumia.apiexercise.domain.Customer_;
import com.jumia.apiexercise.domain.Phone_;

import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification {
      
    public static Specification<Customer> equalCountryName(String countryName){
        if(countryName == null || countryName.equals("")){
            return null;
        }
        return (root, query, cb) -> {
            return cb.equal(root.join(Customer_.PHONES).join(Phone_.COUNTRY).get(Country_.NAME),countryName);
        };
    } 
    
    public static Specification<Customer> equalPhoneState(String state){
        if(state == null || state.equals("")){
            return null;
        }
        return (root, query, cb) -> {
            return cb.equal(root.join(Customer_.PHONES).get(Phone_.STATE),state);
        };
    }
     
}