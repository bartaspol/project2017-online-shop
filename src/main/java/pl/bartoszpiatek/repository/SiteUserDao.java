package pl.bartoszpiatek.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.bartoszpiatek.model.entity.SiteUser;

@Repository
public interface SiteUserDao extends CrudRepository<SiteUser, Long>{
	public SiteUser findByEmail(String email);
}
