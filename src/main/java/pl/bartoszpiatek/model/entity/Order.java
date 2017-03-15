package pl.bartoszpiatek.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ORDERS")
public class Order implements Serializable{

	private static final long serialVersionUID = -3506061661666855923L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ORDER")
	private Long id;
	
	@ManyToMany
	@JoinTable(name = "ORDER_PRODUCTS",
			joinColumns = {@JoinColumn(name = "ORDER_ID", referencedColumnName = "ID_ORDER")},
			inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")})
	private List<Product> products = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private SiteUser user;

	public Order(List<Product> products, SiteUser user) {
		super();
		this.products = products;
		this.user = user;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public SiteUser getUser() {
		return user;
	}

	public void setUser(SiteUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", products=" + products + ", user=" + user + "]";
	}
	
	
}
