package com.ims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ims.model.Category;
import com.ims.service.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	
	@RequestMapping("/category")
	public String viewCategoryPage(Model model) {
			
		return viewPage(model, 1, "categoryName", "asc");
	}
		
	
	@RequestMapping(value="/addcategory",method = RequestMethod.POST)
	public String addCategory(@ModelAttribute ("category") Category category,
	RedirectAttributes redirAttrs) {
		
		categoryService.save(category);
		redirAttrs.addFlashAttribute("success","New category created" +
		"successfuly!");
		
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
	
	@RequestMapping("/category/page/{pageNum}")
	public String viewPage(Model model,
			@PathVariable(name = "pageNum") int pageNum,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir) {
		
		Page<Category> page = categoryService.listAll(pageNum, sortField, sortDir);
		
		List<Category> listCategory = page.getContent();
		Category category = new Category();
		
	    model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
		
	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("category", category);
		
		return "category";
	}

	

}
