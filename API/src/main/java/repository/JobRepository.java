package repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dao.Job;

@Repository
public interface JobRepository extends CrudRepository<Job, Integer>{
	List<Job> findAll();
	
	@Query("SELECT j FROM Job j WHERE j.bill.id = ?1")
	List<Job> findAllByIdBill(Integer idBill);
}
