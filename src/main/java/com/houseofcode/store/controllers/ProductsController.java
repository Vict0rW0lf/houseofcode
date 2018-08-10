package com.houseofcode.store.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.houseofcode.store.dao.ProductDAO;
import com.houseofcode.store.infra.FileSaver;
import com.houseofcode.store.models.Product;
import com.houseofcode.store.models.TypePrice;
import com.houseofcode.store.validation.ProductValidation;

@Controller
@RequestMapping("products")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class ProductsController {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private FileSaver fileSaver;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProductValidation());
	}

	@RequestMapping("form")
	public ModelAndView form(Product product) {
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", TypePrice.values());
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView save (MultipartFile summary, @Valid Product product, BindingResult result, RedirectAttributes redirectAttributes) { 
		
		System.out.println(summary.getOriginalFilename());
		
		if (result.hasErrors()) {
			return form(product);
		}
		
		String path = fileSaver.write("files-summary", summary);
		product.setSummaryPath(path);
		productDAO.save(product);
		redirectAttributes.addFlashAttribute("success", "Product was successfully registered!");
		
		return new ModelAndView("redirect:products");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list() {
		List<Product> products = productDAO.list();
		ModelAndView modelAndView = new ModelAndView("products/list");
		
		modelAndView.addObject("products", products);
		
		return modelAndView;
	}
	
	@RequestMapping("/details/{id}")
	public ModelAndView details(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("products/details");
		Product product = productDAO.findBy(id);
		modelAndView.addObject("product", product);
		
		return modelAndView;
	}
	
}
