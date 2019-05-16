package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import dao.Status;

public interface StatusRepository extends CrudRepository<Status, Integer> {
	List<Status> findAll();
}
