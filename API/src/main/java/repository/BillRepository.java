package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dao.Bill;

@Repository
public interface BillRepository extends CrudRepository<Bill, Integer>{
	List<Bill> findAll();
}
