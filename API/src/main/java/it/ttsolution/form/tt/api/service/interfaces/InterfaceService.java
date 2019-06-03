package it.ttsolution.form.tt.api.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import exception.LocalException;
import it.ttsolution.form.tt.api.entity.interfaces.InterfaceEntity;

@Service
public interface InterfaceService<E extends InterfaceEntity> {

	static final String getFailMessage = "Oggetto non trovato:\n"
			+ "Impossibile trovare l'oggetto richiesto tramite Id specificato.\n";
	static final String getAllFailMessage = "Lista non trovato:\n" + "Impossibile trovare la lista richiesta.\n";

	static final String setFailMessage = "Salvataggio Oggetto non riuscito:\n"
			+ "Impossibile salvare l'oggetto specificato.\n";
	static final String setListFailMessage = "Salvataggio Lista non riuscito:\n"
			+ "Impossibile salvare la lista di oggetti specificati.\n";

	static final String deleteFailMessage = "Eliminazione Oggetto non riuscita:\n"
			+ "Impossibile cancellare l'oggetto specificato.\n";
	static final String deleteAllFailMessage = "Eliminazione Lista non riuscita:\n"
			+ "Impossibile cancellare la Lista specificata.\n";

	static final String invalidIdMessage = "Id non valido:\n"
			+ "Impossibile utilizzare l'id specificato poiché nullo, negativo o non presente nel DB.\n";

	public List<E> getAllEntityAsList() throws Exception;

	public E getEntity(Integer id) throws Exception;

	public Boolean deleteEntity(Integer id) throws Exception;

	Boolean deleteAllEntity() throws Exception;

	public E saveEntity(E entity) throws Exception;

	public List<E> saveEntityList(List<E> list) throws Exception;
	

	Boolean isValidId(Integer id) throws Exception;

//Defaul

	default Boolean isPositiveId(Integer id) throws LocalException {
		if (id != null && id > 0)
			return true;
		throw new LocalException(invalidIdMessage);
	}

}
