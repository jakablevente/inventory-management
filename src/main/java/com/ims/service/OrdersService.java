package com.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ims.model.Customers;
import com.ims.model.OrderItem;
import com.ims.model.Orders;
import com.ims.model.Product;
import com.ims.repository.CustomersRepository;
import com.ims.repository.OrderRepository;
import com.ims.repository.ProductRepository;

@Service
@Transactional
public class OrdersService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	private CustomersRepository custRepo;
	private ProductRepository prodRepo;
	
	@Transactional
	public Integer order(int customerId, int productId, int qty) {
		
		Customers customer = custRepo.findById(customerId).get();
		Product product = prodRepo.findById(productId).get();
		
		OrderItem orderItem = OrderItem.createOrderItem(product,product.getPrice(), qty);
		
		Orders order = Orders.createOrder(customer, orderItem);
		
		orderRepo.save(order);
		
		return order.getId();
	}
	

	
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
