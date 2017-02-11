package pl.bartoszpiatek.model.entity;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import pl.bartoszpiatek.model.dto.FileInfo;

@Entity
@Table(name = "PRODUCTS")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private Long id;

	@NotNull
	@Size(min = 5, max = 255, message = "{addproduct.name.size}")
	@Column(name = "PRODUCT_NAME")
	private String name;

	@NotNull
	@Size(min = 10, max = 5000, message = "{addproduct.description.size}")
	@Column(name = "PRODUCT_DESC", length = 5000)
	private String description;

	@Column(name = "PRODUCT_ADDED")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
	private Date added;

	@NotNull
	@Min(value = 0, message="{addproduct.price.value}")
	@Column(name = "PRODUCT_PRICE")
	private Double price;

	@Column(name = "PHOTO_DIRECTORY", length = 15)
	private String photoDirectory;

	@Column(name = "PHOTO_NAME", length = 15)
	private String photoName;

	@Column(name = "PHOTO_EXTENSION", length = 5)
	private String photoExtension;

	@PrePersist
	protected void onCreate() {
		if (added == null)
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
	
	public Product(String name, String description, Double price ,Date added) {
		this.name = name;
		this.description = description;
		this.added = added;
		this.price = price;
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

	public String getPhotoDirectory() {
		return photoDirectory;
	}

	public void setPhotoDirectory(String photoDirectory) {
		this.photoDirectory = photoDirectory;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getPhotoExtension() {
		return photoExtension;
	}

	public void setPhotoExtension(String photoExtension) {
		this.photoExtension = photoExtension;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setPhotoDetails(FileInfo info) {
		photoDirectory = info.getSubDirectory();
		photoExtension = info.getExtension();
		photoName = info.getBaseName();
	}

	public Path getPhoto(String baseDirectory) {
		if (photoName == null) {
			return null;
		}

		return Paths.get(baseDirectory, photoDirectory, photoName + "." + photoExtension);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((added == null) ? 0 : added.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((photoDirectory == null) ? 0 : photoDirectory.hashCode());
		result = prime * result + ((photoExtension == null) ? 0 : photoExtension.hashCode());
		result = prime * result + ((photoName == null) ? 0 : photoName.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		if (photoDirectory == null) {
			if (other.photoDirectory != null)
				return false;
		} else if (!photoDirectory.equals(other.photoDirectory))
			return false;
		if (photoExtension == null) {
			if (other.photoExtension != null)
				return false;
		} else if (!photoExtension.equals(other.photoExtension))
			return false;
		if (photoName == null) {
			if (other.photoName != null)
				return false;
		} else if (!photoName.equals(other.photoName))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", added=" + added + ", price="
				+ price + ", photoDirectory=" + photoDirectory + ", photoName=" + photoName + ", photoExtension="
				+ photoExtension + "]";
	}

	
}