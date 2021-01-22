package com.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	public Page<Brands> listAll(int pageNum, String sortField, String sortDir){
		int pageSize = 5;
		
		Pageable pageable = PageRequest.of(pageNum -1, pageSize,
				sortDir.equals("asc") ? Sort.by(sortField).ascending()
										: Sort.by(sortField).descending());
		
		return brandRepo.findAll(pageable);
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
