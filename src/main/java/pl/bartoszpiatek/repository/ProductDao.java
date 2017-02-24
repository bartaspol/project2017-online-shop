package pl.bartoszpiatek.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pl.bartoszpiatek.model.dto.ProductCategoryEnum;
import pl.bartoszpiatek.model.entity.Product;

@Repository
public interface ProductDao extends PagingAndSortingRepository<Product, Long>{
	public Product findFirstByOrderByAddedDesc();

	public List<Product> findByNameContainingIgnoreCase(String text);
	
	public Page<Product> findByNameContainingIgnoreCase(String text, Pageable request);

	public Page<Product> findByCategory(ProductCategoryEnum text, Pageable request);
	
	public Page<Product> findByNameContainingIgnoreCaseAndPriceBetween(String text, Double low, Double high, Pageable request);
	
	public Page<Product> findByNameContainingIgnoreCaseAndPriceLessThan(String text, Double high, Pageable request);
	
	public Page<Product> findByNameContainingIgnoreCaseAndPriceGreaterThan(String text, Double low,Pageable request);
}
