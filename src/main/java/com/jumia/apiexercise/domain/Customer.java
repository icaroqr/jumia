package com.jumia.apiexercise.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Customer {

    @Id
    private int id;

    private String name;

    private String phone;

    
}
