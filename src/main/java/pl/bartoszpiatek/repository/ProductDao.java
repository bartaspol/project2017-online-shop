package pl.bartoszpiatek.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pl.bartoszpiatek.model.entity.Product;

@Repository
public interface ProductDao extends PagingAndSortingRepository<Product, Long>{
	public Product findFirstByOrderByAddedDesc();
}
