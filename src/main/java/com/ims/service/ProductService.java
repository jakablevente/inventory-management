package com.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ims.model.Product;
import com.ims.repository.ProductRepository;

@Service
public class ProductService{
	
	@Autowired
	private ProductRepository repo;
	
	public List<Product> listAll(){
		
		return repo.findAll();
	}
	
	public Page<Product> listAll(int pageNum, String sortField, String sortDir){
		int pageSize = 8;
		
		Pageable pageable = PageRequest.of(pageNum -1, pageSize,
				sortDir.equals("asc") ? Sort.by(sortField).ascending()
										: Sort.by(sortField).descending());
		
		return repo.findAll(pageable);
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
