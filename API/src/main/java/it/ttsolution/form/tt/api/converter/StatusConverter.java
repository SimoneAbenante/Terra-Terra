package it.ttsolution.form.tt.api.converter;

import org.springframework.stereotype.Component;

import dto.StatusDto;
import exception.LocalException;
import it.ttsolution.form.tt.api.converter.interfaces.InterfaceConverter;
import it.ttsolution.form.tt.api.entity.Status;

@Component
public class StatusConverter implements InterfaceConverter<Status, StatusDto> {

	@Override
	public StatusDto getDtoFromEntity(Status entity) throws LocalException {
		if (isValidConversion(entity)) {
			StatusDto dto = new StatusDto();
			dto.setId(entity.getId());
			dto.setStatus(entity.getStatus());
			return dto;
		}
		throw new LocalException(conversionDtoFromEntityFailMessage);
	}

	@Override
	public Status getEntityFromDto(StatusDto dto) throws LocalException {
		if (isValidConversion(dto)) {
			Status entity = new Status();
			entity.setId(dto.getId());
			entity.setStatus(dto.getStatus());
			return entity;
		}
		throw new LocalException(conversionEntityFromDtoFailMessage);
	}

}
