package it.ttsolution.form.tt.api.repository.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import it.ttsolution.form.tt.api.entity.interfaces.InterfaceEntity;

@NoRepositoryBean
public interface InterfaceRepository <R extends InterfaceEntity> extends CrudRepository<R, Integer>{

	List<R> findAll();
	
}
