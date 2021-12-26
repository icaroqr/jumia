package com.jumia.apiexercise.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CountryFactory {
    
    static Map<String, String> countryRegex = new HashMap<>();
    static {
        countryRegex.put("Cameroon", "\\(237\\)\\ ?[2368]\\d{7,8}$");
        countryRegex.put("Ethiopia", "\\(251\\)\\ ?[1-59]\\d{8}$");
        countryRegex.put("Morocco", "\\(212\\)\\ ?[5-9]\\d{8}$");
        countryRegex.put("Mozambique", "\\(258\\)\\ ?[28]\\d{7,8}$");
        countryRegex.put("Uganda", "\\(256\\)\\ ?\\d{9}$");
    }

    public static Optional<Country> getCountry(String phone) {
        for (Map.Entry<String, String> item : countryRegex.entrySet()) {
            if(phone.matches(item.getValue())){
                return Optional.of(new Country(item.getKey()));
            }
        }
        return Optional.of(new Country("Invalid country"));
    }
}
