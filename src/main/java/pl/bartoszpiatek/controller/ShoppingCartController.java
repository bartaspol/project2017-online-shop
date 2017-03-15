package pl.bartoszpiatek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.bartoszpiatek.model.dto.ShoppingCart;
import pl.bartoszpiatek.model.entity.Order;
import pl.bartoszpiatek.model.entity.Product;
import pl.bartoszpiatek.model.entity.Profile;
import pl.bartoszpiatek.model.entity.SiteUser;
import pl.bartoszpiatek.service.OrderService;
import pl.bartoszpiatek.service.ProductService;
import pl.bartoszpiatek.service.ProfileService;
import pl.bartoszpiatek.service.SiteUserService;

@Controller
public class ShoppingCartController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ShoppingCart cart;
	
	@Autowired
	private SiteUserService userService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProfileService profileService;
	
	@GetMapping("/orderproduct")
	public ModelAndView addToCart(ModelAndView modelAndView, @RequestParam(name = "id") Long id){
		
		Product product = productService.get(id);
		cart.getList().add(product);
		
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}
	
	@GetMapping("/orderlist")
	public ModelAndView orderListGET(ModelAndView modelAndView){
		
		double sum = cart.totalShoppingCartPrice();
		
		
		modelAndView.getModel().put("sumTotal", sum);
		modelAndView.getModel().put("order", cart.getList());
		modelAndView.setViewName("app.orderList");
		return modelAndView;
	}
	
	@GetMapping("/deleteFromOrder")
	public ModelAndView deleteProductFromOrderGET(ModelAndView modelAndView,
			@RequestParam(name = "pid") Integer productId){
		
		List<Product> list = cart.getList();
		Product product = list.stream()
				.filter(x -> x.getId() == productId.longValue())
				.findFirst()
				.get();
		list.remove(product);
		
		modelAndView.setViewName("redirect:/orderlist");
		return modelAndView;
	}
	
	@GetMapping("/ordersummary")
	public ModelAndView placeOrderGET(ModelAndView modelAndView){
		
		SiteUser user = getUser();
		Profile profile = profileService.getUserProfile(user);
		Profile safeProfile = new Profile();
		safeProfile.safeProfileInformation(profile);
		
		double sum = cart.totalShoppingCartPrice();
		
		modelAndView.getModel().put("sumTotal", sum);
		modelAndView.getModel().put("order", cart.getList());
		modelAndView.getModel().put("profile", safeProfile);
		modelAndView.setViewName("app.orderSummary");
		return modelAndView;
	}
	
	@GetMapping("/pay")
	public ModelAndView payGET(ModelAndView modelAndView){
		
		SiteUser user = getUser();
		List<Order> orders = user.getOrders();
		Order order = new Order(cart.getList(), user);
		orders.add(order);
		
		orderService.save(order);
	
		
		Profile profile = profileService.getUserProfile(user);
		Profile safeProfile = new Profile();
		safeProfile.safeProfileInformation(profile);
		
		cart.getList().clear();
		
		modelAndView.getModel().put("userEmail", user.getEmail());
		modelAndView.getModel().put("profile", safeProfile);
		modelAndView.setViewName("app.pay");
		return modelAndView;
	}
	
	@GetMapping("/orderhistory")
	public ModelAndView orderHistoryGET(ModelAndView modelAndView){
	
		SiteUser user = getUser();
		List<Order> orders = user.getOrders();
		
		
		for(Order o : orders){
			System.out.println("ID: " + o.getId());
			System.out.println("PRODUKTY: " + o.getProducts());
		}
		
		
		modelAndView.getModel().put("orders", orders);
		modelAndView.setViewName("app.orderHistory");
		return modelAndView;
	}
	

	private SiteUser getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		return userService.getByEmail(email);
	}
}
