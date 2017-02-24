package pl.bartoszpiatek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.bartoszpiatek.model.dto.ProductCategoryEnum;
import pl.bartoszpiatek.model.dto.SearchResult;
import pl.bartoszpiatek.service.SearchService;

@Controller
public class CategoryController {
	
	@Autowired
	private SearchService searchService;

	@RequestMapping(value = "/cameras", method = RequestMethod.GET)
	public ModelAndView camerasGET(ModelAndView modelAndView, 
			@RequestParam(name="p", defaultValue = "1") int pageNumber){
		
		Page<SearchResult> results = searchService.byCategory(ProductCategoryEnum.APARAT, pageNumber);
		
		modelAndView.getModel().put("url", "/cameras");
		modelAndView.getModel().put("category", ProductCategoryEnum.APARAT.toString());
		modelAndView.getModel().put("page", results);
		modelAndView.setViewName("app.category");
		return modelAndView;
	}
	
	@RequestMapping(value = "/lenses", method = RequestMethod.GET)
	public ModelAndView lensesGET(ModelAndView modelAndView,
			@RequestParam(name="p", defaultValue = "1") int pageNumber){
		
		Page<SearchResult> results = searchService.byCategory(ProductCategoryEnum.OBIEKTYW, pageNumber);
		
		modelAndView.getModel().put("url", "/lenses");
		modelAndView.getModel().put("category", ProductCategoryEnum.OBIEKTYW.toString());
		modelAndView.getModel().put("page", results);
		modelAndView.setViewName("app.category");
		return modelAndView;
	}
}
