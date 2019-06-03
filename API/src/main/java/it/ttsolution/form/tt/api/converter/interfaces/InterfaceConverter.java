package it.ttsolution.form.tt.api.converter.interfaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dto.inter.InterfaceDto;
import exception.LocalException;
import it.ttsolution.form.tt.api.entity.interfaces.InterfaceEntity;

@Component
public interface InterfaceConverter<E extends InterfaceEntity, D extends InterfaceDto> {

	public static final String conversionNullFailMessage = "Conversione non riuscita:\n"
			+ "Impossibile Convertire il Dto e/o la Entity sono nulli\n";

	public static final String conversionDtoFromEntityFailMessage = "Conversione non riuscita:\n"
			+ "Impossibile Convertire la Entity in un Dto\n";
	public static final String conversionDtoListFromEntityListFailMessage = "Conversione non riuscita:\n"
			+ "Impossibile Convertire la lista di Entity in una lista di Dto\n";

	public static final String conversionEntityFromDtoFailMessage = "Conversione non riuscita:\n"
			+ "Impossibile Convertire il Dto in una Entity\n";
	public static final String conversionEntityListFromDtoListFailMessage = "Conversione non riuscita:\n"
			+ "Impossibile Convertire la lista di Dto in una lista di Entity\n";

	D getDtoFromEntity(E entity) throws LocalException;

	E getEntityFromDto(D dto) throws LocalException;

	default public List<D> getDtoListFromEntityList(List<E> listEntity) throws LocalException {
		if (!listEntity.isEmpty()) {
			List<D> listDto = new ArrayList<>();
			for (E entity : listEntity) {
				listDto.add(getDtoFromEntity(entity));
			}
			return listDto;
		}
		throw new LocalException(conversionDtoListFromEntityListFailMessage);
	}

	default public List<E> getEntityListFromDtoList(List<D> listDto) throws LocalException {
		if (!listDto.isEmpty()) {
			List<E> listEntity = new ArrayList<>();
			for (D dto : listDto) {
				listEntity.add(getEntityFromDto(dto));
			}
			return listEntity;
		}
		throw new LocalException(conversionDtoListFromEntityListFailMessage);
	}

	default <C extends Serializable> Boolean isValidConversion(C context) throws LocalException {
		if (context != null)
			return true;
		throw new LocalException(conversionNullFailMessage);
	}

}
