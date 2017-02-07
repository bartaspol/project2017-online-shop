package pl.bartoszpiatek.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.bartoszpiatek.model.entity.Product;
import pl.bartoszpiatek.repository.ProductDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
public class ExampleTest {
	
	@Autowired
	private ProductDao productDao;
	
	@Test
	public void testSave(){
		Product product = new Product("Test name", "Some desc");
		productDao.save(product);
		
		assertNotNull("Non-null ID", product.getId());
		assertNotNull("Non-null Date", product.getAdded());
		
		Product retrieved = productDao.findOne(product.getId());
		assertEquals("Matching Product", product, retrieved);
	}
	
	@Test
	public void findLatest(){
		Calendar calendar =  Calendar.getInstance();
		
		Product latestProduct = null; 
		for(int i = 0; i < 10; i++){
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			Product product = new Product("Product " + i, " desc ", calendar.getTime());
			productDao.save(product);
			latestProduct = product;
		}
		
		Product retrieved = productDao.findFirstByOrderByAddedDesc();
		assertEquals("Matching Product", latestProduct, retrieved);
	}
}
