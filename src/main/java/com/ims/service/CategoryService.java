package com.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ims.model.Category;
import com.ims.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository catRepo;
	
	public List<Category> listAll(){
		
		return catRepo.findAll();
	}
	
	public Page<Category> listAll(int pageNum, String sortField, String sortDir){
		int pageSize = 4;
		
		Pageable pageable = PageRequest.of(pageNum -1, pageSize,
				sortDir.equals("asc") ? Sort.by(sortField).ascending()
										: Sort.by(sortField).descending());
		
		return catRepo.findAll(pageable);
	}
	
	public void save (Category category) {
		
		catRepo.save(category);
	}
	
	
	public Category get(int id) {
		
		return catRepo.findById(id).get();
	}
	
	public void delete (int id) {
		
		catRepo.deleteById(id);
	}
	
	
	


}
