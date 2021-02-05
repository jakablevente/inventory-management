package com.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	public Page<Customers> listAll(int pageNum, String sortField, String sortDir){
		int pageSize = 8;
		
		Pageable pageable = PageRequest.of(pageNum -1, pageSize,
				sortDir.equals("asc") ? Sort.by(sortField).ascending()
										: Sort.by(sortField).descending());
		
		return customerRepo.findAll(pageable);
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
