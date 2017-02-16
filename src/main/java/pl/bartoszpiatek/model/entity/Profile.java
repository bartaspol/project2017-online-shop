package pl.bartoszpiatek.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PROFILE")
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(targetEntity = SiteUser.class)
	@JoinColumn(name = "USER_ID", nullable = false)
	private SiteUser user;
	
	@Column(name = "PROFILE_NAME", length = 30)
	@Size(max = 30, message = "{profile.street.size}")
	private String name;
	
	
	@Column(name = "PROFILE_SURNAME", length = 30)
	@Size(max = 30, message = "{profile.street.size}")
	private String surname;

	@Column(name = "PROFILE_ABOUT", length = 500)
	@Size(max = 500, message = "{profile.street.size}")
	private String about;

	
	@Column(name = "PROFILE_STREET", length = 30)
	@Size(max = 30, message = "{profile.street.size}")
	private String street;

	public Profile() {
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public SiteUser getUser() {
		return user;
	}
	

	public void setUser(SiteUser user) {
		this.user = user;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public void safeProfileInformation(Profile other) {
		if (other.about != null) {
			this.about = other.about;
		}
		if(other.street != null){
			this.street = other.street;
		}
		if(other.surname != null){
			this.surname = other.surname;
		}
		if(other.name != null){
			this.name = other.name;
		}
	}

	public void saveEditedFields(Profile profile) {
		if(profile.about != null){
			this.about = profile.about;
		}
		if(profile.street != null){
			this.street = profile.street;
		}
		if(profile.surname != null){
			this.surname = profile.surname;
		}
		if(profile.name != null){
			this.name = profile.name;
		}
	}

}
