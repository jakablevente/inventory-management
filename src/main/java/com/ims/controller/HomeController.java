package com.ims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ims.model.Brands;
import com.ims.model.Category;
import com.ims.model.Customers;
import com.ims.model.Orders;
import com.ims.model.Product;
import com.ims.service.BrandsService;
import com.ims.service.CategoryService;
import com.ims.service.CustomersService;
import com.ims.service.OrdersService;
import com.ims.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private BrandsService brandService;
	@Autowired
	private CustomersService customerService;
	@Autowired
	private OrdersService orderService;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		
		List<Category> listCategory = categoryService.listAll();
		List<Product> listProduct = productService.listAll();
		List<Brands> listBrands = brandService.listAll();
		List<Customers> listCustomers = customerService.listAll();
		List<Orders> listOrders = orderService.listAll();
		
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("listBrands", listBrands);
		model.addAttribute("listCustomers", listCustomers);
		model.addAttribute("listOrders", listOrders);
		return "index";
	}

}
