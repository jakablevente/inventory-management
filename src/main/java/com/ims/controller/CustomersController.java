package com.ims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ims.model.Customers;
import com.ims.service.CustomersService;

@Controller
public class CustomersController {

	@Autowired
	private CustomersService customerService;
	
	@RequestMapping("/customers")
	public String viewCustomerPage(Model model) {
		
		List<Customers> listCustomers = customerService.listAll();
		model.addAttribute("listCustomers", listCustomers);
		
		Customers customer = new Customers();
		model.addAttribute("customer", customer);
		
		return "customers";
		
	}
	
	@PostMapping(value="/save_customer")
	public String saveCustomer(@ModelAttribute("customer") Customers customer) {
		
		customerService.save(customer);
		
		return "redirect:/customers";
	}
	
	@RequestMapping(value="/delete_customer/{id}")
	public String deleteCustomer(@PathVariable(name="id") int id) {
		
		customerService.delete(id);
		
		return "redirect:/customers";
	}
	
	@RequestMapping("/findCustomer")
	@ResponseBody
	public Customers findCustomer(int id) {
		
		return customerService.get(id);
	}
}
