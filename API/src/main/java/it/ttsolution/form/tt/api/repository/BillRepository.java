package it.ttsolution.form.tt.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.ttsolution.form.tt.api.dao.Bill;

@Repository
public interface BillRepository extends CrudRepository<Bill, Integer>{
	List<Bill> findAll();
}
