package pl.bartoszpiatek.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import pl.bartoszpiatek.model.entity.Product;

public interface ProductDao extends PagingAndSortingRepository<Product, Long>{
	public Product findFirstByOrderByAddedDesc();
}
