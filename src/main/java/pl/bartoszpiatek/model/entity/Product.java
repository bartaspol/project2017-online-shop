package pl.bartoszpiatek.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="PRODUCTS")
public class Product implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private Long id;
	
	@NotNull
	@Size(min = 5, max = 255, message="{addproduct.name.size}")
	@Column(name = "PRODUCT_NAME")
	private String name;
	
	@NotNull
	@Size(min = 10, max = 5000, message="{addproduct.description.size}")
	@Column(name = "PRODUCT_DESC", length = 5000)
	private String description;
	
	@Column(name = "PRODUCT_ADDED")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
	private Date added;
	
	@PrePersist
	protected void onCreate(){
		if(added == null)
			added = new Date();
	}
	
	public Product(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Product(String name, String description, Date added) {
		this.name = name;
		this.description = description;
		this.added = added;
	}

	
	public Product() {
	}


	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((added == null) ? 0 : added.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (added == null) {
			if (other.added != null)
				return false;
		} else if (!added.equals(other.added))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", added=" + added + "]";
	}
	
	
}
