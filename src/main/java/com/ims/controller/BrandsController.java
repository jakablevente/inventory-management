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

import com.ims.model.Brands;
import com.ims.service.BrandsService;

@Controller
public class BrandsController {
	
	@Autowired
	private BrandsService brandService;
	
	
	@RequestMapping("/brands")
	public String viewBrandPage(Model model) {
	
		
		return viewPage(model, 1, "name" , "asc");
	}
	
	
	@RequestMapping(value="/save_brand", method = RequestMethod.POST)
	public String saveBrand(@ModelAttribute ("brand") Brands brand, RedirectAttributes redirAttrs) {
		brandService.save(brand);
		redirAttrs.addFlashAttribute("success","New Brand created successfully!");
		return "redirect:/brands";
		
	}
	
	
	@RequestMapping("/delete_brand/{id}")
	public String deleteBrand(@PathVariable(name = "id") int id,RedirectAttributes redirAttrs) {
		try {
		brandService.delete(id);
		redirAttrs.addFlashAttribute("success","Deleted successfully!");
		return "redirect:/brands";
		} catch (Exception e) {
			redirAttrs.addFlashAttribute("errorMsg","Can't delete this brand, because it's used!");
			return "redirect:/brands";
		}
		
	}
	
	@RequestMapping("/findBrand")
	@ResponseBody
	public Brands findOne(Integer id){
		return brandService.get(id);
	}
	
	@RequestMapping("/brands/page/{pageNum}")
	public String viewPage(Model model,
	        @PathVariable(name = "pageNum") int pageNum,
	        @Param("sortField") String sortField,
	        @Param("sortDir") String sortDir) {
	     
	    Page<Brands> page = brandService.listAll(pageNum, sortField, sortDir);
	     
	    List<Brands> listBrands = page.getContent();
		Brands brand = new Brands();
	     
	    model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    
	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	    
		model.addAttribute("listBrands", listBrands);
		model.addAttribute("brand", brand);
	    


	     
	    return "brands";
	}
	

}
