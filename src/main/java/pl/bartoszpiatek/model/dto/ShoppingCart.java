package pl.bartoszpiatek.model.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import pl.bartoszpiatek.model.entity.Product;

@Component
@SessionScope
public class ShoppingCart {

	private List<Product> list = new ArrayList<>();

	public ShoppingCart(List<Product> list) {
		super();
		this.list = list;
	}

	public ShoppingCart() {
	}

	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}

	public double totalShoppingCartPrice(){
		double sum = 0;
		for(Product p : list){
			sum = sum + p.getPrice();
		}
		
		return sum;
	}
}
