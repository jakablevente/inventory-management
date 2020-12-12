package com.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
