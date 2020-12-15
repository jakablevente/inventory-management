package com.ims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ims.model.Brands;
import com.ims.service.BrandsService;

@Controller
public class BrandsController {
	
	@Autowired
	private BrandsService brandService;
	
	
	@RequestMapping("/brands")
	public String viewBrandPage(Model model) {
		
		List<Brands> listBrands = brandService.listAll();
		model.addAttribute("listBrands", listBrands);
		
		Brands brand = new Brands();
		model.addAttribute("brand", brand);
		
		
		
		return "brands";
	}
	
	
	@RequestMapping(value="/save_brand", method = RequestMethod.POST)
	public String saveBrand(@ModelAttribute ("brand") Brands brand) {
		brandService.save(brand);
		return "redirect:/brands";
		
	}
	
	
	@RequestMapping("/delete_brand/{id}")
	public String deleteBrand(@PathVariable(name = "id") int id) {
		brandService.delete(id);
		return "redirect:/brands";
	}
	
	@RequestMapping("/findBrand")
	@ResponseBody
	public Brands findOne(Integer id){
		return brandService.get(id);
	}
	

}
