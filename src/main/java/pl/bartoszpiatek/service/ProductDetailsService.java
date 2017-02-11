package pl.bartoszpiatek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.bartoszpiatek.model.entity.ProductDetails;
import pl.bartoszpiatek.repository.ProductDetailsDao;

@Service
public class ProductDetailsService {
	
	@Autowired
	private ProductDetailsDao detailsDao;
	
	public void save(ProductDetails productDetails){
		detailsDao.save(productDetails);
	}
}
