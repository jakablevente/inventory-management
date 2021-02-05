package com.ims.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ims.model.OrderItem;
import com.ims.model.Orders;
import com.ims.repository.OrderRepository;

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
	
	@Transactional
	public void delete (int id) {
		
		Orders order = orderRepo.findById(id).get();
		order.setCustomer(null);
		for(OrderItem item : order.getOrderItems()){

			item.getProduct().addStock(item.getQty());
			item.setProduct(null);
			
			
		}
		
		orderRepo.deleteById(id);
	}

	public Page<Orders> listAll(int pageNum, String sortField, String sortDir){
		int pageSize = 8;
		
		Pageable pageable = PageRequest.of(pageNum -1, pageSize,
				sortDir.equals("asc") ? Sort.by(sortField).ascending()
										: Sort.by(sortField).descending());
		
		return orderRepo.findAll(pageable);
	}
	


}
