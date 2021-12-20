package com.jumia.apiexercise.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int countryCode;

    private String state;

    public Country(String phone){
        if(phone.matches("\\(237\\)\\ ?[2368]\\d{7,8}$")){
            this.name="Cameroon";
            this.countryCode=237;
            this.state="valid";
            return;
        }else
        if(phone.matches("\\(251\\)\\ ?[1-59]\\d{8}$")){
            this.name="Ethiopia";
            this.countryCode=251;
            this.state="valid";
            return;
        }else
        if(phone.matches("\\(212\\)\\ ?[5-9]\\d{8}$")){
            this.name="Morocco";
            this.countryCode=212;
            this.state="valid";
            return;
        }else
        if(phone.matches("\\(258\\)\\ ?[28]\\d{7,8}$")){
            this.name="Mozambique";
            this.countryCode=258;
            this.state="valid";
            return;
        }else
        if(phone.matches("\\(256\\)\\ ?\\d{9}$")){
            this.name="Uganda";
            this.countryCode=256;
            this.state="valid";
            return;
        }else{
            this.name="Invalid country";
            this.countryCode=0;
            this.state="not valid";
        }
    }

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(o instanceof Country){
            Country other = (Country) o;
            if(other.getName().equals(this.name)) return true;
        } 
        return false;
    }

    @Override
    public int hashCode() {
        return (this.name.hashCode() + this.state.hashCode());        
    }
    
}
