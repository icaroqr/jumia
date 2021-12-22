package com.jumia.apiexercise.repository;

import java.util.Optional;

import com.jumia.apiexercise.domain.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>, JpaSpecificationExecutor<Customer>{

    Page<Customer> findAll(Pageable pageable);

    Optional<Customer> findByName(String name);
}
