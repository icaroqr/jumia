package com.jumia.apiexercise.repository;

import com.jumia.apiexercise.domain.Phone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone,Integer>, JpaSpecificationExecutor<Phone>{

}
