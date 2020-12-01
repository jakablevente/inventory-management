package com.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ims.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
