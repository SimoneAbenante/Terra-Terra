package it.ttsolution.form.tt.api.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.JobDto;
import exception.LocalException;
import it.ttsolution.form.tt.api.converter.interfaces.InterfaceConverter;
import it.ttsolution.form.tt.api.entity.Job;
import it.ttsolution.form.tt.api.service.BillService;
import it.ttsolution.form.tt.api.service.DiningTableService;
import it.ttsolution.form.tt.api.service.DishService;
import it.ttsolution.form.tt.api.service.StatusService;

@Component
public class JobConverter implements InterfaceConverter<Job, JobDto> {

	@Autowired
	BillService billService;
	@Autowired
	DiningTableService diningTableService;
	@Autowired
	DishService dishService;
	@Autowired
	StatusService statusService;

	@Override
	public JobDto getDtoFromEntity(Job entity) throws LocalException {
		if (isValidConversion(entity)) {
			JobDto dto = new JobDto();
			dto.setId(entity.getId());
			dto.setIdBill(entity.getBill().getId());
			dto.setIdDiningTable(entity.getDiningTable().getId());
			dto.setIdDish(entity.getDish().getId());
			dto.setIdStatus(entity.getStatus().getId());
			return dto;
		}
		throw new LocalException(conversionDtoFromEntityFailMessage);
	}

	@Override
	public Job getEntityFromDto(JobDto dto) throws LocalException {
		if (isValidConversion(dto)) {
			Job entity = new Job();
			entity.setId(dto.getId());
			entity.setBill(billService.getEntity(dto.getIdBill()));
			entity.setDiningTable(diningTableService.getEntity(dto.getIdDiningTable()));
			entity.setDish(dishService.getEntity(dto.getIdDish()));
			entity.setStatus(statusService.getEntity(dto.getIdStatus()));
			return entity;
		}
		throw new LocalException(conversionEntityFromDtoFailMessage);
	}

}
