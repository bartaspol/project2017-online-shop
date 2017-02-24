package pl.bartoszpiatek.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import pl.bartoszpiatek.service.SiteUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SiteUserService siteUserService; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.authorizeRequests()
			
//			ALL USERS ACCESS
				.antMatchers(
						"/",
						"/register",
						"/productphoto",
						"/product/*",
						"/search",
						"/cameras",
						"/lenses",
						"/customsearch"
						)
				.permitAll()
				.antMatchers(
						"/js/*",
						"/css/*",
						"/img/*"
//						"/WEB-INF/**"
						)
				.permitAll()
//			ONLY ADMIN ROLE
				.antMatchers(
						"/addproduct",
						"/editproduct",
						"/deleteproduct",
						"/upload-product-photo"
						)
				.hasRole("ADMIN")
//			ONLY FOR LOGGED USERS	
				.antMatchers(
						"/profile",
						"/editprofile",
						"/editabout"
						)
				.authenticated()
				.anyRequest()
				.denyAll()
//			ONLY AUTHENTICATED USERS
			
				.and()
//			LOGIN FORM FOR ALL USERS
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.permitAll()
				.and()
//			LOGOUT
			.logout()
				.permitAll();

//		EXCEPTIONS HANDLING
//		http.exceptionHandling().accessDeniedPage("/403");

	}
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//		auth.inMemoryAuthentication()
//			.withUser("bart")
//			.password("bartaspol")
//			.roles("ADMIN");
//	}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(siteUserService).passwordEncoder(passwordEncoder);
	
	}
	
}