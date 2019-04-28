package rep;

import org.springframework.data.repository.CrudRepository;
import dao.Job;

public interface JobRepository extends CrudRepository<Job, Integer> {

}
