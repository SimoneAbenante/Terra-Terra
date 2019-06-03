package it.ttsolution.form.tt.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.ttsolution.form.tt.api.entity.Job;
import it.ttsolution.form.tt.api.repository.interfaces.InterfaceRepository;

@Repository
public interface JobRepository extends InterfaceRepository<Job>{
	
	@Query("SELECT j FROM Job j WHERE j.bill.id = ?1")
	List<Job> findAllByIdBill(Integer idBill);
	
}
