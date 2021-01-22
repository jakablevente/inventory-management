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

import com.ims.model.Role;
import com.ims.model.User;
import com.ims.repository.RoleRepository;
import com.ims.service.UserService;

@Controller
public class UserController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping("/users")
	public String showUserList(Model model) {
	
		return viewPage(model,1,"username","asc");
	}
	
	@PostMapping("/save_user")
	public String saveUser (@ModelAttribute ("user") User user,
							RedirectAttributes redirAttrs) {
		
		userService.save(user);
		redirAttrs.addFlashAttribute("success","Új felhasználó sikeresen hozzáadva!");
		
		return "redirect:/users";
	}
	
	@RequestMapping("/delete_user/{id}")
	public String deleteUser(@PathVariable( name = "id") int id,
							 RedirectAttributes redirAttrs) {
		try {
			userService.delete(id);
			redirAttrs.addFlashAttribute("success", "Felhasználó sikeresen törölve!");
			return "redirect:/users";
		} catch (Exception e) {
			redirAttrs.addFlashAttribute("errorMsg", "Ezt a felhasználót nem lehet törölni!");
			return "redirect:/users";
		}
	}
	
	@RequestMapping("/findUser")
	@ResponseBody
	public User findOne(Integer id) {
		
		return userService.get(id);
	}
	
	
	
	@RequestMapping("/users/page/{pageNum}")
	public String viewPage(Model model,
	        @PathVariable(name = "pageNum") int pageNum,
	        @Param("sortField") String sortField,
	        @Param("sortDir") String sortDir) {
	     
	    Page<User> page = userService.listAll(pageNum, sortField, sortDir);
	    List<User> listUsers = page.getContent();
		List<Role> listRoles = roleRepository.findAll();
		
	    User user = new User();

		
	     
	    model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    
	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	    
		model.addAttribute("listRoles", listRoles);
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("user", user);
	    


	     
	    return "users";
	}
	

}
