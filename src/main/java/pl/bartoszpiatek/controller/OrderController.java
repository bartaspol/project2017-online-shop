package pl.bartoszpiatek.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.bartoszpiatek.model.entity.Order;
import pl.bartoszpiatek.model.entity.Product;
import pl.bartoszpiatek.model.entity.SiteUser;
import pl.bartoszpiatek.service.OrderService;
import pl.bartoszpiatek.service.ProductService;
import pl.bartoszpiatek.service.SiteUserService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SiteUserService userService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/orderproduct", method = RequestMethod.GET)
	public ModelAndView orderProductPost(ModelAndView modelAndView, @RequestParam(name = "id") Long id){
		
		
		SiteUser user = getUser();
		Product product = productService.get(id);
		
		List<Order> orders = user.getOrders();
		
		
		List<Product> products = new ArrayList<>();
		products.add(product);
		
		if(orders.isEmpty()){
			Order order = new Order(products, user);
			orders.add(order);
			orderService.save(order);
		}
		else{
			Order getOrder = orders.get(orders.size() - 1);
			getOrder.getProducts().add(product);
			orderService.save(getOrder);
		}
		
		
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/orderlist", method = RequestMethod.GET)
	public ModelAndView orderListGET(ModelAndView modelAndView){
	
		SiteUser user = getUser();
		List<Order> orders = user.getOrders();
		Order order = orders.get(orders.size() - 1);
		List<Product> products = order.getProducts();
		
		long orderId = order.getId();
		
		modelAndView.getModel().put("orderId", orderId);
		modelAndView.getModel().put("order", products);
		
		modelAndView.setViewName("app.orderList");
		return modelAndView;
	}
	
	@RequestMapping(value = "/deleteFromOrder", method = RequestMethod.GET)
	public ModelAndView deleteProductFromOrderGET(ModelAndView modelAndView,
			 @RequestParam(name = "pid") Integer productId){
		
		SiteUser user = getUser();
		List<Order> orders = user.getOrders();
		Order order = orders.get(orders.size() - 1);
		List<Product> products = order.getProducts();
		
		products.removeIf(x -> x.getId() == productId.longValue());
		orderService.save(order);
		
		modelAndView.setViewName("app.orderList");
		return modelAndView;
	}
	
	
	private SiteUser getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		return userService.getByEmail(email);
	}
	
}
