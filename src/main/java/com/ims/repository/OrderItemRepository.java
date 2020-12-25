package com.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ims.model.OrderItem;

public interface OrderItemRepository  extends JpaRepository<OrderItem, Integer>{

}
