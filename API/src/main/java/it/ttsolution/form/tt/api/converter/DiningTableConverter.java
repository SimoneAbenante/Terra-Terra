package it.ttsolution.form.tt.api.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.DiningTableDto;
import exception.LocalException;
import it.ttsolution.form.tt.api.converter.interfaces.InterfaceConverter;
import it.ttsolution.form.tt.api.entity.DiningTable;
import it.ttsolution.form.tt.api.service.StatusService;

@Component
public class DiningTableConverter implements InterfaceConverter<DiningTable, DiningTableDto> {

	@Autowired
	StatusService statusService;

	@Override
	public DiningTableDto getDtoFromEntity(DiningTable entity) throws LocalException {
		if (isValidConversion(entity)) {
			DiningTableDto dto = new DiningTableDto();
			dto.setId(entity.getId());
			dto.setSize(entity.getSize());
			dto.setIdStatus(entity.getStatus().getId());
			return dto;
		}
		throw new LocalException(conversionDtoFromEntityFailMessage);
	}

	@Override
	public DiningTable getEntityFromDto(DiningTableDto dto) throws LocalException {
		if (isValidConversion(dto)) {
			DiningTable entity = new DiningTable();
			entity.setId(dto.getId());
			entity.setSize(dto.getSize());
			entity.setStatus(statusService.getEntity(dto.getIdStatus()));
			return entity;
		}
		throw new LocalException(conversionEntityFromDtoFailMessage);
	}

}
