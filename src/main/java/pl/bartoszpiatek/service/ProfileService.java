package pl.bartoszpiatek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.bartoszpiatek.model.entity.Profile;
import pl.bartoszpiatek.model.entity.SiteUser;
import pl.bartoszpiatek.repository.ProfileDao;

@Service
public class ProfileService {

	@Autowired
	private ProfileDao profileDao;
	
	public void save(Profile profile){
		profileDao.save(profile);
	}
	
	public Profile getUserProfile(SiteUser user){
		return profileDao.findByUser(user);
	}

	public Profile get(Long id) {
		return profileDao.findOne(id);
	}
}
