package com.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ims.model.Customers;

public interface CustomersRepository extends JpaRepository<Customers, Integer>{

}
