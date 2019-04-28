package rep;

import org.springframework.data.repository.CrudRepository;
import dao.Dish;

public interface DishRepository extends CrudRepository<Dish, Integer> {

}
