package com.ims.service;

import java.util.ArrayList;
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
	
	

	
	public List<Orders> listAll(){
		
		return orderRepo.findAll();
	}
	
	public void save (Orders order) {
		
		/*for(OrderItem items : order.getOrderItems()) {
			
			items.setOrder(order);
		} */
		List<OrderItem> orderedItems = new ArrayList<>();
		double total = 0.0;
		
		for(OrderItem oItems : order.getOrderItems()) {
			
			oItems.setOrder(order);
			oItems.setAmount(oItems.getProduct().getPrice() * oItems.getQty());
			oItems.getProduct().removeStock(oItems.getQty());
			total += oItems.getAmount();
			if(oItems.getQty() != 0) {
			orderedItems.add(oItems);
			}
			
		}
		order.setPaidStatus(false);
		order.setTotal(total);
		order.setOrderItems(orderedItems);
		
		orderRepo.save(order);
	}
	
	public Orders get(int id) {
		
		return orderRepo.findById(id).get();
	}
	
	public void delete (int id) {
		orderRepo.deleteById(id);
	}
	


}
