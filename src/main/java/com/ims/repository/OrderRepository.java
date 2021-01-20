package com.ims.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ims.model.Orders;

public interface OrderRepository  extends JpaRepository<Orders, Integer>{


}
