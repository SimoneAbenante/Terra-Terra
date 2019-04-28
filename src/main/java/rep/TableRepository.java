package rep;

import org.springframework.data.repository.CrudRepository;
import dao.Table;

public interface TableRepository extends CrudRepository<Table, Integer> {

}
