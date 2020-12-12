package com.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ims.model.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer>{

}
