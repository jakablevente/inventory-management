package com.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.model.Product;
import com.ims.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;
	
	public List<Product> listAll(){
		
		return repo.findAll();
	}
	
	public void save(Product product) {
		
		repo.save(product);
	}
	
	public Product get(int id) {
		
		return repo.findById(id).get();
	}
	
	public void delete(int id) {
		
		repo.deleteById(id);
	}

}
