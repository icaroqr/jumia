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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private int code;

    public Country(String phone){
        if(phone.matches("\\(237\\)\\ ?[2368]\\d{7,8}$")){
            this.name="Cameroon";
            this.code=237;
            return;
        }else
        if(phone.matches("\\(251\\)\\ ?[1-59]\\d{8}$")){
            this.name="Ethiopia";
            this.code=251;
            return;
        }else
        if(phone.matches("\\(212\\)\\ ?[5-9]\\d{8}$")){
            this.name="Morocco";
            this.code=212;
            return;
        }else
        if(phone.matches("\\(258\\)\\ ?[28]\\d{7,8}$")){
            this.name="Mozambique";
            this.code=258;
            return;
        }else
        if(phone.matches("\\(256\\)\\ ?\\d{9}$")){
            this.name="Uganda";
            this.code=256;
            return;
        }else{
            this.name="Invalid country";
            this.code=0;
        }
    }

}
