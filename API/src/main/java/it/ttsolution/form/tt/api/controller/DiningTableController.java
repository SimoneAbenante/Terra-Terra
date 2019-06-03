package it.ttsolution.form.tt.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.DiningTableDto;
import exception.LocalException;
import it.ttsolution.form.tt.api.controller.interfaces.InterfaceController;
import it.ttsolution.form.tt.api.converter.DiningTableConverter;
import it.ttsolution.form.tt.api.service.DiningTableService;

@RestController
@RequestMapping("/tables")
public class DiningTableController implements InterfaceController<DiningTableDto> {

	@Autowired
	DiningTableService diningTableService;
	@Autowired
	DiningTableConverter diningTableConverter;

	@Override
	public List<DiningTableDto> getAll() throws LocalException {
		return diningTableConverter.getDtoListFromEntityList(diningTableService.getAllEntityAsList());
	}

	@Override
	public DiningTableDto getById(Integer id) throws LocalException {
		return diningTableConverter.getDtoFromEntity(diningTableService.getEntity(id));
	}

	@Override
	public DiningTableDto save(DiningTableDto dto) throws LocalException {
		return diningTableConverter
				.getDtoFromEntity(diningTableService.saveEntity(diningTableConverter.getEntityFromDto(dto)));
	}

	@Override
	public List<DiningTableDto> saveAll(List<DiningTableDto> listDto) throws LocalException {
		return diningTableConverter.getDtoListFromEntityList(
				diningTableService.saveEntityList(diningTableConverter.getEntityListFromDtoList(listDto)));
	}

	@Override
	public Boolean delete(Integer id) throws LocalException {
		return diningTableService.deleteEntity(id);
	}

	@Override
	public Boolean deleteAll() throws LocalException {
		return diningTableService.deleteAllEntity();
	}

	@PostMapping(value = "/status/all", produces = "application/json")
	public List<DiningTableDto> setAllStatus(@RequestParam Integer idStatus) throws LocalException {
		return diningTableConverter.getDtoListFromEntityList(diningTableService.setStatusOfAllEntity(idStatus));
	}
	
	@Deprecated
	@PostMapping(value = "/dto/status/all", produces = "application/json")
	public Boolean setAllStatusReturnBoolean(@RequestParam Integer idStatus) throws LocalException {
		return diningTableService.setStatusOfAllDiningTablesReturnBoolean(idStatus);
	}

	@PostMapping(value = "/dto/status/{idTable}", produces = "application/json")
	public DiningTableDto setStatus(@PathVariable Integer idTable, @RequestParam Integer idStatus)
			throws LocalException {
		return diningTableConverter.getDtoFromEntity(diningTableService.setStatusOfEntity(idTable, idStatus));
	}
	
	@Deprecated
	@PostMapping(value = "/status/{idTable}", produces = "application/json")
	public Boolean setStatusReturnBoolean(@PathVariable Integer idTable, @RequestParam Integer idStatus)
			throws LocalException {
		return diningTableService.setStatusOfDiningTableReturnBoolean(idTable, idStatus);
	}

}
