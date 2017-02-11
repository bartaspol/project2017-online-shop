package pl.bartoszpiatek.repository;

import org.springframework.data.repository.CrudRepository;

import pl.bartoszpiatek.model.entity.ProductDetails;

public interface ProductDetailsDao extends CrudRepository<ProductDetails, Long>{

}
