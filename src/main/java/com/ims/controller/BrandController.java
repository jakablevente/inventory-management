package com.ims.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ims.model.Brands;
import com.ims.repository.BrandsRepository;
import com.ims.service.BrandsService;

@Controller
public class BrandController {
	
	private BrandsRepository brandRepo;
	
	@GetMapping
	public String addBrand(Brands brand, Model model) {
		model.addAttribute("brands",(brandRepo.findAll()));
		return "index";
	}

}
