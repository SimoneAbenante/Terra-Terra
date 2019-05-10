package rep;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dao.Job;

@Repository
public interface JobRepository extends CrudRepository<Job, Integer>{
	List<Job> findAll();
}
