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
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchGET(ModelAndView modelAndView, @RequestParam("s") String text,
			@RequestParam(name="p", defaultValue = "1") int pageNumber){
		
		Page<SearchResult> results = searchService.search(text.trim(), pageNumber);
		int size = searchService.search(text).size();
		
		modelAndView.getModel().put("resultSize", size);
		modelAndView.getModel().put("search", text);
		modelAndView.getModel().put("page", results);
		modelAndView.setViewName("app.search");
		return modelAndView;
	}
}
