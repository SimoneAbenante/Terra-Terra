package service.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface InterfaceService {

	static final String getFailMessage = "Oggetto non trovato:\n"
			+ "Impossibile trovare l'oggetto richiesto.\n";
	static final String convertFailMessage = "Conversione non riuscita:\n"
			+ "Impossibile convertire l'oggetto richiesto.\n";
	static final String setFailMessage = "Settaggio non riuscito:\n"
			+ "Impossibile impostare l'oggetto con i valori richiesti.\n";
	static final String invalidIdMessage = "Id non valido:\n"
			+ "Impossibile utilizzare l'id specificato poiché nullo o non presente nel DB.\n";

	Boolean isExistingId(Integer id);

	default Boolean isPositiveId(Integer id){
		if (id != null && id > 0)
			return true;
		return false;
	}
}
