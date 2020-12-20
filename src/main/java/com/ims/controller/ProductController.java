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
import com.ims.model.Category;
import com.ims.model.Product;
import com.ims.service.BrandsService;
import com.ims.service.CategoryService;
import com.ims.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BrandsService brandService;
	
	
	@RequestMapping("/product")
	public String viewProductPage(Model model) {
	
		
		List<Brands> listBrands = brandService.listAll();
		List<Category> listCategories = categoryService.listAll();
		List<Product> listProducts = productService.listAll();
		
		Product product = new Product();
		model.addAttribute("listProducts", listProducts);
		model.addAttribute("product", product);
		model.addAttribute("listCategories",listCategories);
		model.addAttribute("listBrands", listBrands);
		
		return "product";
	}
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		productService.save(product);
		
		return "redirect:/";
	}
	
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		productService.delete(id);
		return "redirect:/";		
	}
	
	@RequestMapping("/findProduct")
	@ResponseBody
	public Product findProduct(Integer id) {
		return productService.get(id);
	}
	
	

	
	
	}

	

	

	
	
	


