package pl.bartoszpiatek.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pl.bartoszpiatek.model.dto.ProductCategoryEnum;
import pl.bartoszpiatek.model.dto.SearchResult;
import pl.bartoszpiatek.model.entity.Product;
import pl.bartoszpiatek.repository.ProductDao;

@Service
public class SearchService {
	
//	number of products on the page
	@Value("${searchresults.pagesize}")
	private int PAGE_SIZE;
	
	@Autowired
	private ProductDao productDao;

	public Page<SearchResult> search(String text, int pageNumber) {
		
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "price");
		Page<Product> results = productDao.findByNameContainingIgnoreCase(text, request);
		
		Converter<Product, SearchResult> converter = new Converter<Product, SearchResult>() {
			
			@Override
			public SearchResult convert(Product product) {
				return new SearchResult(product);
			}
		};
		
		return results.map(converter);
	}
	
	public List<SearchResult> search(String text) {
		return productDao.findByNameContainingIgnoreCase(text).stream().map(SearchResult::new)
				.collect(Collectors.toList());
	}
	
	public Page<SearchResult> byCategory(ProductCategoryEnum category, int pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "price");
		Page<Product> results = productDao.findByCategory(category, request);
		
		Converter<Product, SearchResult> converter = new Converter<Product, SearchResult>() {
			
			@Override
			public SearchResult convert(Product product) {
				return new SearchResult(product);
			}
		};
		return results.map(converter);
	}
	
	
}
