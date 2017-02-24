package pl.bartoszpiatek.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pl.bartoszpiatek.validation.PasswordMatch;

@Entity
@Table(name = "USERS")
@PasswordMatch(message="{register.repeatpassword.mismatch}")
public class SiteUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id;

	@Column(name = "USER_EMAIL", unique = true)
	@Email(message = "{register.email.invalid}")
	@NotBlank(message = "{register.email.invalid}")
	private String email;
	
	@Column(name = "USER_ENABLED")
	private Boolean enabled = true;

	@Column(name = "USER_PASSWORD", length = 60)
	private String password;

	@Transient
	@Size(min = 5, max = 15, message = "{register.password.size}")
	private String plainPassword;

	@Transient
	@Size(min = 5, max = 15, message = "{register.password.size}")
	private String repeatPassword;

	@Column(name = "USER_ROLE", length = 20)
	private String role;
	
	@PrePersist
	protected void onCreate() {
		enabled = true;
	}

	public SiteUser() {
	}

	public SiteUser(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	// ENCODE PASSWORD AND SAVE IN PASSWORD
	public void setPlainPassword(String plainPassword) {
		this.password = new BCryptPasswordEncoder().encode(plainPassword);
		this.plainPassword = plainPassword;
	}

}
