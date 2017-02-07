package pl.bartoszpiatek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pl.bartoszpiatek.model.entity.Product;
import pl.bartoszpiatek.repository.ProductDao;

@Service
public class ProductService {

//	number of products on the page
	private final static int PAGE_SIZE = 6;
	
	@Autowired
	private ProductDao productDao; 
	
	public void save(Product product){
		productDao.save(product);
	}
	
	public Product getLatest(){
		return productDao.findFirstByOrderByAddedDesc();
	}
	
	public Page<Product> getPage(int pageNumber){
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "added");
		
		return productDao.findAll(request);
	}

	public void delete(Long id) {
		productDao.delete(id);
	}

	public Product get(Long id) {
		return productDao.findOne(id);
	}
}
