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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ims.model.Category;
import com.ims.repository.CategoryRepository;
import com.ims.service.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	
	@RequestMapping("/category")
	public String viewCategoryPage(Model model) {
		
		List<Category> listCategory = categoryService.listAll();
		model.addAttribute("listCategory", listCategory);
		
		Category category = new Category();
		model.addAttribute("category", category);
		
		
		return "category";
	}
	
	

	
	
	@RequestMapping(value="/addcategory",method = RequestMethod.POST)
	public String addCategory(@ModelAttribute ("category") Category category,RedirectAttributes redirAttrs) {
		
		categoryService.save(category);
		redirAttrs.addFlashAttribute("success","New category created successfuly!");
		
		return "redirect:/category";
	}
	
	
	
	@RequestMapping("/delete_category/{id}")
	public String deleteCategory(@PathVariable(name = "id") int id, RedirectAttributes redirAttrs) {
		try {
		categoryService.delete(id);
		redirAttrs.addFlashAttribute("success","Deleted succesfully!");
		return "redirect:/category";
		} catch (Exception e) {
			String errorMsg = "Can't delete this field!";
			redirAttrs.addFlashAttribute("errorMsg",errorMsg);
			return "redirect:/category";

		}
		
	}
	
	@RequestMapping("/findOne")
	@ResponseBody
	public Category findOne(Integer id){
		return categoryService.get(id);
	}

	

}
