package pl.bartoszpiatek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.bartoszpiatek.model.dto.SearchResult;
import pl.bartoszpiatek.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;

//	@RequestMapping(value = "/search", method = RequestMethod.GET)
//	public ModelAndView searchGET(ModelAndView modelAndView, @RequestParam("s") String text,
//			@RequestParam(name = "p", defaultValue = "1") int pageNumber) {
//
//		Page<SearchResult> results = searchService.search(text.trim(), pageNumber);
//		int size = results.getNumberOfElements();
//
//		modelAndView.getModel().put("resultSize", size);
//		modelAndView.getModel().put("search", text);
//		modelAndView.getModel().put("page", results);
//		modelAndView.setViewName("app.search");
//		return modelAndView;
//	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView customSearchGET(ModelAndView modelAndView, @RequestParam("s") String text,
			@RequestParam(name = "p", defaultValue = "1") int pageNumber,
			@RequestParam(name = "f", required = false) Double priceFrom,
			@RequestParam(name = "t", required = false) Double priceTo) {

		int size = 0;
		Page<SearchResult> results = null; 

		if((priceTo == null) && (priceFrom == null)){
			results = searchService.search(text.trim(), pageNumber);
			size = results.getNumberOfElements();
		}
		
		if (priceTo != null) {
			results = searchService.byNameAndPriceLessThan(text, priceTo, pageNumber);
			size = results.getNumberOfElements();
		}

		if(priceFrom != null) {
			results = searchService.byNameAndPriceGreaterThan(text, priceFrom, pageNumber);
			size = results.getNumberOfElements();
		}

		
		if(text != null && priceTo != null && priceFrom != null){
			results = searchService.byNameAndPriceBetween(text, priceFrom, priceTo, pageNumber);
			size = results.getNumberOfElements();
		}
		
		
		
		modelAndView.getModel().put("priceFrom", priceFrom);
		modelAndView.getModel().put("priceTo", priceTo);
		modelAndView.getModel().put("resultSize", size);
		modelAndView.getModel().put("search", text);
		modelAndView.getModel().put("page", results);
		modelAndView.setViewName("app.search");
		return modelAndView;
	}

}
