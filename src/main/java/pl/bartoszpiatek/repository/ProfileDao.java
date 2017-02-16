package pl.bartoszpiatek.repository;

import org.springframework.data.repository.CrudRepository;

import pl.bartoszpiatek.model.entity.Profile;
import pl.bartoszpiatek.model.entity.SiteUser;

public interface ProfileDao extends CrudRepository<Profile, Long>{

	public Profile findByUser(SiteUser user);
}
