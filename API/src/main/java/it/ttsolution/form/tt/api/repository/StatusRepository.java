package it.ttsolution.form.tt.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.ttsolution.form.tt.api.dao.Status;

public interface StatusRepository extends CrudRepository<Status, Integer> {
	List<Status> findAll();
}
