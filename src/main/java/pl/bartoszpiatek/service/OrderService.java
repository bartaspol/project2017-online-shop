package pl.bartoszpiatek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.bartoszpiatek.model.entity.Order;
import pl.bartoszpiatek.repository.OrderDao;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	
	public void save(Order order){
		orderDao.save(order);
	}
	
	public void delete(Long id){
		orderDao.delete(id);
	}
}
