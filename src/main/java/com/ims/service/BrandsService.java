package com.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.model.Brands;
import com.ims.repository.BrandsRepository;

@Service
public class BrandsService {
	
	@Autowired
	private BrandsRepository brandRepo;
	
	public List<Brands> listAll(){
		
		return brandRepo.findAll();
	}
	
	public void save (Brands brand) {
		
		brandRepo.save(brand);
	}
	
	public Brands get(int id) {
		
		return brandRepo.findById(id).get();
	}
	
	public void delete (int id) {
		
		brandRepo.deleteById(id);
	}

}
