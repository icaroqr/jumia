package com.jumia.apiexercise.specification;

import com.jumia.apiexercise.domain.Phone;

import org.springframework.data.jpa.domain.Specification;

public class PhoneSpecification {
      
    public static Specification<Phone> equalCountryName(String countryName){
        if(countryName == null || countryName.equals("")){
            return null;
        }
        return (root, query, cb) -> {
            return cb.equal(root.join("country").get("name"),countryName);
        };
    } 
    
    public static Specification<Phone> equalState(String state){
        if(state == null || state.equals("")){
            return null;
        }
        return (root, query, cb) -> {
            return cb.equal(root.get("state"),state);
        };
    }
     
}