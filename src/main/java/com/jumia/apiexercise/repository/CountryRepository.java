package com.jumia.apiexercise.repository;

import java.util.Optional;

import com.jumia.apiexercise.domain.Country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Integer>{


    Optional<Country> findByName(String name);
}
