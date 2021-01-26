package com.ims.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ims.helpers.ProductPDFExporter;
import com.ims.helpers.ZXingHelper;
import com.ims.model.Brands;
import com.ims.model.Category;
import com.ims.model.Product;
import com.ims.service.BrandsService;
import com.ims.service.CategoryService;
import com.ims.service.ProductService;
import com.itextpdf.text.DocumentException;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BrandsService brandService;
	
	
	@RequestMapping("/product")
	public String viewProductPage(Model model) {
	
		return viewPage(model,1, "name", "asc");
	}
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product,
							  RedirectAttributes redirAttrs) {
		
		productService.save(product);
		redirAttrs.addFlashAttribute("success","New Product created successfully!");
		
		return "redirect:/product";
	}
	
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id, RedirectAttributes redirAttrs) {
		try {
		productService.delete(id);
		redirAttrs.addFlashAttribute("success","Deleted successfully!");
		} catch (Exception e) {
			redirAttrs.addFlashAttribute("errorMsg","Can't delete this product!");
		}
		return "redirect:/product";
	}
	
	@RequestMapping("/findProduct")
	@ResponseBody
	public Product findProduct(Integer id) {
		return productService.get(id);
	}
	
	@RequestMapping(value = "barcode/{id}", method = RequestMethod.GET)
	public void barcode(@PathVariable(name = "id") int id, HttpServletResponse response) throws Exception {
		String barcode = String.valueOf(id);
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(ZXingHelper.getBarCodeImage(barcode, 200, 200));
		outputStream.flush();
		outputStream.close();
	}
	
	@RequestMapping("/product/page/{pageNum}")
	public String viewPage(Model model,
	        @PathVariable(name = "pageNum") int pageNum,
	        @Param("sortField") String sortField,
	        @Param("sortDir") String sortDir) {
	     
	    Page<Product> page = productService.listAll(pageNum, sortField, sortDir);
	     
	    List<Product> listProducts = page.getContent();
	    List<Brands> listBrands = brandService.listAll();
	    List<Category> listCategories = categoryService.listAll();
		Product product = new Product();
	     
	    model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    
	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	    
	    model.addAttribute("listProducts", listProducts);
		model.addAttribute("listBrands", listBrands);
		model.addAttribute("listCategories",listCategories);
		model.addAttribute("product", product);
	     
	    return "product";
	}
	
	
	  @GetMapping("/product/export/pdf")
	    public void exportToPDF(HttpServletResponse response) throws  DocumentException, IOException{
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=product_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	         
	        List<Product> listProducts = productService.listAll();
	         
	        ProductPDFExporter exporter = new ProductPDFExporter(listProducts);
	        exporter.export(response);
	         
	    }
	
	

	
	
	}

	

	

	
	
	


