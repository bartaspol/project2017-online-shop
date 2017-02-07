package pl.bartoszpiatek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.bartoszpiatek.model.entity.Product;
import pl.bartoszpiatek.service.ProductService;

@Controller
public class PageController {
	
	@Autowired
	private ProductService productService;

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	ModelAndView home(ModelAndView modelAndView,
			@RequestParam(name = "p", defaultValue = "1") int pageNumber) {
		
		Product product = productService.getLatest();
		Page<Product> page = productService.getPage(pageNumber);
		
		modelAndView.getModel().put("page", page);
		modelAndView.getModel().put("product", product);
		modelAndView.setViewName("app.homepage");
		return modelAndView;
	}
	
}
