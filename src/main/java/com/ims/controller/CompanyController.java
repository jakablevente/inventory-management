package com.ims.controller;

import java.util.List;

import com.ims.model.Company;
import com.ims.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;
    
    @RequestMapping("/company")
    public String viewCompany(Model model){
        Company company = new Company();
        List<Company> listCompanies = companyService.listAll();
        model.addAttribute("listCompanies", listCompanies);
        model.addAttribute("company",company);

        return "company";
    }

    @PostMapping(value="/save_company")
    public String saveCompany( @ModelAttribute("company") Company company,
                               RedirectAttributes redirAttrs){
        
            companyService.save(company);
            redirAttrs.addFlashAttribute("success", "Company information edited successfully!");
		
            return "redirect:/company";
    }

    @RequestMapping("/findCompany")
	@ResponseBody
	public Company findCompany(Integer id) {
		return companyService.get(id);
	}


}
