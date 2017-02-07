package pl.bartoszpiatek.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.bartoszpiatek.model.entity.Product;
import pl.bartoszpiatek.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/addproduct", method = RequestMethod.GET)
	ModelAndView addProduct(ModelAndView modelAndView, @ModelAttribute("product") Product product) {
		
		modelAndView.setViewName("app.addProduct");
		return modelAndView;
	}
	
	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	ModelAndView addProduct(ModelAndView modelAndView, @Valid Product product, 
			BindingResult result) {
		
		modelAndView.setViewName("app.addProduct");
		
		if(!result.hasErrors()){
			productService.save(product);
			modelAndView.setViewName("redirect:/");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/deleteproduct", method = RequestMethod.GET)
	ModelAndView deleteProduct(ModelAndView modelAndView, @RequestParam(name="id") Long id) {
		
		productService.delete(id);
		
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}
	
	@RequestMapping(value = "/editproduct", method = RequestMethod.GET)
	ModelAndView editProduct(ModelAndView modelAndView, @RequestParam(name="id") Long id) {
		
		Product product = productService.get(id);
		
		modelAndView.getModel().put("product", product);
		modelAndView.setViewName("app.editProduct");
		return modelAndView;
	}
	
	@RequestMapping(value = "/editproduct", method = RequestMethod.POST)
	ModelAndView editProduct(ModelAndView modelAndView, @Valid Product product, BindingResult result) {
		
		modelAndView.setViewName("app.editProduct");
		if(!result.hasErrors()){
			productService.save(product);
			modelAndView.setViewName("redirect:/");
		}
		
		return modelAndView;
	}
	
}
