package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dao.Dish;

@Repository
public interface DishRepository extends CrudRepository<Dish, Integer>{
	List<Dish> findAll();
}
