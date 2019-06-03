package it.ttsolution.form.tt.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.DishDto;
import exception.LocalException;
import it.ttsolution.form.tt.api.controller.interfaces.InterfaceController;
import it.ttsolution.form.tt.api.converter.DishConverter;
import it.ttsolution.form.tt.api.service.DishService;

@RestController
@RequestMapping("/dishes")
public class DishController implements InterfaceController<DishDto>{
	
	@Autowired
	DishService dishService;
	@Autowired
	DishConverter dishConverter;

	@Override
	public List<DishDto> getAll() throws LocalException {
		return dishConverter.getDtoListFromEntityList(dishService.getAllEntityAsList());
	}
	
	@Override
	public DishDto getById(Integer id) throws LocalException {
		return dishConverter.getDtoFromEntity(dishService.getEntity(id));
	}
	
	@Override
	public DishDto save(DishDto dto) throws LocalException {
		return dishConverter.getDtoFromEntity(dishService.saveEntity(dishConverter.getEntityFromDto(dto)));
	}
	
	@Override
	public List<DishDto> saveAll(List<DishDto> listDto) throws LocalException {
		return dishConverter.getDtoListFromEntityList(dishService.saveEntityList(dishConverter.getEntityListFromDtoList(listDto)));
	}
	
	@Override
	public Boolean delete(Integer id) throws LocalException {
		return dishService.deleteEntity(id);
	}
	
	@Override
	public Boolean deleteAll() throws LocalException {
		return dishService.deleteAllEntity();
	}
	
}
