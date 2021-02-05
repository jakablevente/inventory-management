package com.ims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ims.model.Customers;
import com.ims.model.Orders;
import com.ims.model.Product;
import com.ims.service.CustomersService;
import com.ims.service.OrdersService;
import com.ims.service.ProductService;

@Controller
public class OrderController {
	
	@Autowired
	private OrdersService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CustomersService customerService;

	@RequestMapping("/order")
	public String viewOrderPage(Model model) {
		
		return viewPage(model,1,"customer","asc");
				
	}
	
	@PostMapping("/save_order")
	public String saveOrder(@ModelAttribute("order") Orders order,
							RedirectAttributes redirAttrs){
		try{
		orderService.save(order);
	
		redirAttrs.addFlashAttribute("success","New order has been created!");
		return "redirect:/order";
	} catch(Exception e){
			redirAttrs.addFlashAttribute("errorMsg", "Not enough stock from some product!");
			return "redirect:/order";
		}
		
		
	}
	
	@RequestMapping("/delete_order/{id}")
	public String deleteOrder(@PathVariable(name = "id") int id ) {
		
		orderService.delete(id);
		return "redirect:/order";
	}
	
	@RequestMapping("/findOrder")
	@ResponseBody
	public Orders findOrder(Integer id) {
		return orderService.get(id);
	}

	@GetMapping("/orders/details/{id}")
	public String viewOrder(@PathVariable ("id") Integer id, Model model){
		
		Orders order = orderService.get(id);

		model.addAttribute("order", order);
		model.addAttribute("pageTitle","Order Details (ID: " + id + ")");
		return "details";
	}

	@RequestMapping("/order/page/{pageNum}")
	public String viewPage(Model model,
	        @PathVariable(name = "pageNum") int pageNum,
	        @Param("sortField") String sortField,
	        @Param("sortDir") String sortDir) {
	     
	    Page<Orders> page = orderService.listAll(pageNum, sortField, sortDir);
	     
	    List<Orders> listOrder = page.getContent();
		List<Customers> listCustomers = customerService.listAll();
		List<Product> listProducts = productService.listAll();
	     
	    model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    
	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	    

		
		Orders order = new Orders();
		model.addAttribute("listCustomers", listCustomers);
		model.addAttribute("listOrder", listOrder);
		model.addAttribute("listProducts", listProducts);
		model.addAttribute("order", order);
	     
	    return "order";
	}

	
}
