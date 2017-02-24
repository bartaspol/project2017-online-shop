package pl.bartoszpiatek.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.bartoszpiatek.model.entity.Profile;
import pl.bartoszpiatek.model.entity.SiteUser;

@Repository
public interface ProfileDao extends CrudRepository<Profile, Long>{

	public Profile findByUser(SiteUser user);
}
