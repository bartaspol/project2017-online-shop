package pl.bartoszpiatek.model.dto;

import pl.bartoszpiatek.model.entity.Product;

public class SearchResult {

	private Long productId;
	private String name;
	private Double price;
	private String description;

	public SearchResult(Product product) {
		this.productId = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
		this.description = product.getDescription();
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "SearchResult [productId=" + productId + ", name=" + name + ", price=" + price + "]";
	}

}
