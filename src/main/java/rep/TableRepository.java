package rep;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dao.DiningTable;

@Repository
public interface TableRepository extends CrudRepository<DiningTable, Integer>{
	List<DiningTable> findAll();
}
