package pl.bartoszpiatek.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.bartoszpiatek.model.entity.Order;

@Repository
public interface OrderDao extends CrudRepository<Order, Long>{

}
