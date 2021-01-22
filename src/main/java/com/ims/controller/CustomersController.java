package com.ims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.ims.model.Customers;
import com.ims.service.CustomersService;

@Controller
public class CustomersController {

	@Autowired
	private CustomersService customerService;
	
	@RequestMapping("/customers")
	public String viewCustomerPage(Model model) {
		
		return viewPage(model,1,"name","asc");
		
	}
	
	@PostMapping(value="/save_customer")
	public String saveCustomer(@ModelAttribute("customer") Customers customer, RedirectAttributes redirAttrs) {
		
		customerService.save(customer);
		redirAttrs.addFlashAttribute("success", "New Customer added successfully!");
		
		return "redirect:/customers";
	}

	@RequestMapping(value="/delete_customer/{id}")
	public String deleteCustomer(@PathVariable(name="id") int id,RedirectAttributes redirAttrs) {
		try {
		customerService.delete(id);
		redirAttrs.addFlashAttribute("success", "Deleted successfully!");
		} catch (Exception e) {
			redirAttrs.addFlashAttribute("errorMsg", "Can't delete this field!");
		} 
			return "redirect:/customers";
		
		
	}
	
	@RequestMapping("/findCustomer")
	@ResponseBody
	public Customers findCustomer(int id) {
		
		return customerService.get(id);
	}
	
	@RequestMapping("/customers/page/{pageNum}")
	public String viewPage(Model model,
	        @PathVariable(name = "pageNum") int pageNum,
	        @Param("sortField") String sortField,
	        @Param("sortDir") String sortDir) {
	     
	    Page<Customers> page = customerService.listAll(pageNum, sortField, sortDir);
	     
	    List<Customers> listCustomers = page.getContent();

		Customers customer= new Customers();
	     
	    model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    
	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	    
		model.addAttribute("listCustomers", listCustomers);
		
		model.addAttribute("customer", customer);
	    

	     
	    return "customers";
	}
}
