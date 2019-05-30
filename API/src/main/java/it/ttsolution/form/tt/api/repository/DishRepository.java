package it.ttsolution.form.tt.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.ttsolution.form.tt.api.dao.Dish;

@Repository
public interface DishRepository extends CrudRepository<Dish, Integer>{
	List<Dish> findAll();
}
