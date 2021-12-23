package com.jumia.apiexercise.dto;

import java.util.ArrayList;
import java.util.List;

import com.jumia.apiexercise.domain.Phone;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhoneDto {

    private int id;

    private String number;

    private String state;

    private String countryName;

    public static List<PhoneDto> toList(List<Phone> list) {
        List<PhoneDto> dtoList = new ArrayList<PhoneDto>();
        if(list != null){
            for (Phone phone : list) {
                dtoList.add(new PhoneDto(phone));
            }
        }
        return dtoList;
    }

    public PhoneDto(Phone phone) {
        this.id = phone.getId();
        this.number = phone.getNumber();
        this.state = phone.getState();
        this.countryName = phone.getCountry() != null ? phone.getCountry().getName() : null;
    }
    
}
