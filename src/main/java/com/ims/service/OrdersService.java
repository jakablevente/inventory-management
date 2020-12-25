package com.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.model.Orders;
import com.ims.repository.OrderRepository;

@Service
public class OrdersService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	public List<Orders> listAll(){
		
		return orderRepo.findAll();
	}
	
	public void save (Orders order) {
		
		orderRepo.save(order);
	}
	
	public Orders get(int id) {
		
		return orderRepo.findById(id).get();
	}
	
	public void delete (int id) {
		
		orderRepo.deleteById(id);
	}

}
