package rep;

import org.springframework.data.repository.CrudRepository;
import dao.Bill;

public interface BillRepository extends CrudRepository<Bill, Integer> {

}
