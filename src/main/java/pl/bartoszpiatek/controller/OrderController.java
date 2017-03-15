package pl.bartoszpiatek.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.bartoszpiatek.model.entity.Order;
import pl.bartoszpiatek.model.entity.Product;
import pl.bartoszpiatek.model.entity.Profile;
import pl.bartoszpiatek.model.entity.SiteUser;
import pl.bartoszpiatek.service.OrderService;
import pl.bartoszpiatek.service.ProductService;
import pl.bartoszpiatek.service.ProfileService;
import pl.bartoszpiatek.service.SiteUserService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SiteUserService userService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private ProfileService profileService;
	
	@RequestMapping(value = "/orderproduct2", method = RequestMethod.GET)
	public ModelAndView orderProductGET(ModelAndView modelAndView, @RequestParam(name = "id") Long id){
		
		
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
	
	
	@RequestMapping(value = "/orderlist2", method = RequestMethod.GET)
	public ModelAndView orderListGET(ModelAndView modelAndView){
	
		SiteUser user = getUser();
		List<Order> orders = user.getOrders();
		Order order = orders.get(orders.size() - 1);
		List<Product> products = order.getProducts();
		
		long orderId = order.getId();
		
		modelAndView.getModel().put("sumTotal", orderService.totalOrderPrice(order));
		modelAndView.getModel().put("orderId", orderId);
		modelAndView.getModel().put("order", products);
		
		modelAndView.setViewName("app.orderList2");
		return modelAndView;
	}
	
	@RequestMapping(value = "/deleteFromOrder2", method = RequestMethod.GET)
	public ModelAndView deleteProductFromOrderGET(ModelAndView modelAndView,
			 @RequestParam(name = "pid") Integer productId){
		
		SiteUser user = getUser();
		List<Order> orders = user.getOrders();
		Order order = orders.get(orders.size() - 1);
		List<Product> products = order.getProducts();
		
		Product product = products.stream()
								.filter(x -> x.getId() == productId.longValue())
								.findFirst()
								.get();
		
		products.remove(product);
		orderService.save(order);
		
		modelAndView.setViewName("redirect:/orderlist2");
		return modelAndView;
	}
	
	
	@GetMapping("/ordersummary2")
	public ModelAndView placeOrderGET(ModelAndView modelAndView, @RequestParam(name = "orderId") Integer orderId){
		
		SiteUser user = getUser();
		Profile profile = profileService.getUserProfile(user);
		Profile safeProfile = new Profile();
		safeProfile.safeProfileInformation(profile);
		
		List<Order> orders = user.getOrders();
		Order order = orders.stream()
							.filter(x -> x.getId() == orderId.longValue())
							.findFirst()
							.get();
		
		List<Product> products = order.getProducts();
		
		modelAndView.getModel().put("sumTotal", orderService.totalOrderPrice(order));
		modelAndView.getModel().put("order", products);
		modelAndView.getModel().put("profile", safeProfile);
		modelAndView.setViewName("app.orderSummary");
		return modelAndView;
	}
	
	@GetMapping("/pay2")
	public ModelAndView placeOrderGET(ModelAndView modelAndView){
		
		SiteUser user = getUser();
		Profile profile = profileService.getUserProfile(user);
		Profile safeProfile = new Profile();
		safeProfile.safeProfileInformation(profile);
		
		modelAndView.getModel().put("userEmail", user.getEmail());
		modelAndView.getModel().put("profile", safeProfile);
		modelAndView.setViewName("app.pay");
		return modelAndView;
	}
	
	
	private SiteUser getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		return userService.getByEmail(email);
	}
	
}
