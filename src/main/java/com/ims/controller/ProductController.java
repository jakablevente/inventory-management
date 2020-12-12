package com.ims.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ims.model.Brands;
import com.ims.model.Category;
import com.ims.model.Product;
import com.ims.service.BrandsService;
import com.ims.service.CategoryService;
import com.ims.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private BrandsService brandService;
	
	

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		
		return "index";
	}
	
	@RequestMapping("/product")
	public String viewProductPage(Model model) {
		
		List<Product> listProducts = service.listAll();
		model.addAttribute("listProducts", listProducts);
		
		return "product";
	}
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		return "new_product";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = service.get(id);
		mav.addObject("product", product);
		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";		
	}
	
	// -------------------------------------------------BRANDS
	
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
	
	@RequestMapping("/edit_brand/{id}")
	public String showEditBrandPage(@PathVariable(name = "id") int id, Model model) {

		Brands brand = brandService.get(id);
		
		model.addAttribute("brand", brand);

		
		return "edit_brand";
	}
	
	
	@RequestMapping("/delete_brand/{id}")
	public String deleteBrand(@PathVariable(name = "id") int id) {
		brandService.delete(id);
		return "redirect:/brands";
	}
	
	//-----------------------------CATEGORY
	
	
	}

	

	

	
	
	


