package pl.bartoszpiatek.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.bartoszpiatek.model.entity.SiteUser;
import pl.bartoszpiatek.repository.SiteUserDao;

@Service
public class SiteUserService implements UserDetailsService{

	@Autowired
	private SiteUserDao siteUserDao;
	
	
	public void register(SiteUser user){
		user.setRole("ROLE_USER");
		siteUserDao.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		SiteUser user = siteUserDao.findByEmail(email);
		if(user == null){
			return null;
		}
		
		List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());
		String password = user.getPassword();
//		Boolean enabled = user.getEnabled();
		user.setEnabled(true);
		Boolean enabled = user.getEnabled();
		
		return new User(email, password, enabled, true, true, true, auth);
	}

	public SiteUser getByEmail(String email) {
		return siteUserDao.findByEmail(email);
	}
}
