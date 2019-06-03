package it.ttsolution.form.tt.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.StatusDto;
import exception.LocalException;
import it.ttsolution.form.tt.api.controller.interfaces.InterfaceController;
import it.ttsolution.form.tt.api.converter.StatusConverter;
import it.ttsolution.form.tt.api.service.StatusService;

@RestController
@RequestMapping("/statuses")
public class StatusController implements InterfaceController<StatusDto>{
	
	@Autowired
	StatusService statusService;
	@Autowired
	StatusConverter statusConverter;
	
	@Override
	public List<StatusDto> getAll() throws LocalException {
		return statusConverter.getDtoListFromEntityList((statusService.getAllEntityAsList()));
	}
	
	@Override	public StatusDto getById(@PathVariable Integer id) throws LocalException {
		return statusConverter.getDtoFromEntity(statusService.getEntity(id));
	}
	
	@Override	public StatusDto save(@RequestBody StatusDto dto) throws LocalException {
		return statusConverter.getDtoFromEntity(statusService.saveEntity(statusConverter.getEntityFromDto(dto)));
	}
	
	@Override	public List<StatusDto> saveAll(@RequestBody List<StatusDto> listDto) throws LocalException {
		return statusConverter.getDtoListFromEntityList(statusService.saveEntityList(statusConverter.getEntityListFromDtoList(listDto)));
	}
	
	@Override	public Boolean delete(@PathVariable Integer id) throws LocalException {
		return statusService.deleteEntity(id);
	}
	
	@Override	public Boolean deleteAll() throws LocalException {
		return statusService.deleteAllEntity();
	}
	
}
