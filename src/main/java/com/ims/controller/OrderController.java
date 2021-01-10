package com.ims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		
		List<Orders> listOrder = orderService.listAll();
		List<Customers> listCustomers = customerService.listAll();
		List<Product> listProducts = productService.listAll();
		
		Orders order = new Orders();
		model.addAttribute("listCustomers", listCustomers);
		model.addAttribute("listOrder", listOrder);
		model.addAttribute("listProducts", listProducts);
		model.addAttribute("order", order);

		
		return "order";
				
	}
	
	@PostMapping("/save_order")
	public String addOrder(@RequestParam("customerId") Integer customerId,
							@RequestParam("productId") Integer productId,
							@RequestParam("qty") Integer qty){
		
		
		orderService.order(customerId, productId, qty);
		
		return "redirect:/orders";
	}
	
	@RequestMapping(value="/delete_order/{id}")
	public String deleteOrder(@PathVariable(name = "id") int id ) {
		
		orderService.delete(id);
		return "redirect:/orders";
	}
	
	@RequestMapping("/findOrder")
	@ResponseBody
	public Orders findOrder(Integer id) {
		return orderService.get(id);
	}
}
