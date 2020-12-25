package com.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.model.Customers;
import com.ims.repository.CustomersRepository;

@Service
public class CustomersService {
	
	@Autowired
	private CustomersRepository customerRepo;
	
	public List<Customers> listAll(){
		
		return customerRepo.findAll();
				
	}
	
	public void save(Customers customer) {
		
		customerRepo.save(customer);
	}
	
	public Customers get (int id) {
		
		return customerRepo.findById(id).get();
	}
	
	public void delete (int id) {
		
		customerRepo.deleteById(id);
	}

}
