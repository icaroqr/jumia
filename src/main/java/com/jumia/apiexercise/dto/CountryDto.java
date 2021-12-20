package com.jumia.apiexercise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class CountryDto {
    
    private String country;

    private String countryCode;

    private String state;

    public CountryDto(String phone){
        if(phone.matches("\\(237\\)\\ ?[2368]\\d{7,8}$")){
            this.country="Cameroon";
            this.countryCode="+237";
            this.state="valid";
        }else
        if(phone.matches("\\(251\\)\\ ?[1-59]\\d{8}$")){
            this.country="Ethiopia";
            this.countryCode="+251";
            this.state="valid";
        }else
        if(phone.matches("\\(212\\)\\ ?[5-9]\\d{8}$")){
            this.country="Morocco";
            this.countryCode="+212";
            this.state="valid";
        }else
        if(phone.matches("\\(258\\)\\ ?[28]\\d{7,8}$")){
            this.country="Mozambique";
            this.countryCode="+258";
            this.state="valid";
        }else
        if(phone.matches("\\(256\\)\\ ?\\d{9}$")){
            this.country="Uganda";
            this.countryCode="+256";
            this.state="valid";
        }else{
            this.country="Invalid country";
            this.countryCode="Invalid code";
            this.state="not valid";
        }
    }
}
