package com.ims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@Autowired
	private CategoryService categoryService;
	
	

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		
		List<Product> listProducts = service.listAll();
		model.addAttribute("listProducts", listProducts);
		
		return "index";
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
		
		return "brands";
	}
	
	
	
	@RequestMapping("/newbrand")
	public String showNewBrandPage(Model model) {
		Brands brand = new Brands();
		model.addAttribute("brand", brand);
		
		return "new_brand";
	}
	
	@RequestMapping(value="/save_brand", method = RequestMethod.POST)
	public String saveBrand(@ModelAttribute ("brand") Brands brand) {
		brandService.save(brand);
		return "redirect:/";
		
	}
	
	//-----------------------------CATEGORY
	
	@RequestMapping("/category")
	public String viewCategoryPage(Model model) {
		
		List<Category> listCategory = categoryService.listAll();
		model.addAttribute("listCategory", listCategory);
		
		return "category";
	}
	
	@RequestMapping("/newcategory")
	public String showNewCategoryPage(Model model) {
		
		Category category = new Category();
		model.addAttribute("category", category);
		
		return "new_category";
	}
	
	@RequestMapping(value="/save_category",method = RequestMethod.POST)
	public String saveCategory(@ModelAttribute ("category") Category category) {
		
		categoryService.save(category);
		return "redirect:/";
	}
	

	
	
	

}
