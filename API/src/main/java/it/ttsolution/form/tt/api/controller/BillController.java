package it.ttsolution.form.tt.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import dto.BillDto;
import exception.LocalException;
import it.ttsolution.form.tt.api.controller.interfaces.InterfaceController;
import it.ttsolution.form.tt.api.converter.BillConverter;
import it.ttsolution.form.tt.api.service.BillService;

@RequestMapping("/bills")
public class BillController implements InterfaceController<BillDto> {

	@Autowired
	BillService billService;
	@Autowired
	BillConverter billConverter;

	@Override
	public List<BillDto> getAll() throws LocalException {
		return billConverter.getDtoListFromEntityList(billService.getAllEntityAsList());
	}

	@Override
	public BillDto getById(Integer id) throws LocalException {
		return billConverter.getDtoFromEntity(billService.getEntity(id));
	}

	@Override
	public BillDto save(BillDto dto) throws LocalException {
		return billConverter.getDtoFromEntity(billService.saveEntity(billConverter.getEntityFromDto(dto)));
	}

	@Override
	public List<BillDto> saveAll(List<BillDto> listDto) throws LocalException {
		return billConverter
				.getDtoListFromEntityList(billService.saveEntityList(billConverter.getEntityListFromDtoList(listDto)));
	}

	@Override
	public Boolean delete(Integer id) throws LocalException {
		return billService.deleteEntity(id);
	}

	@Override
	public Boolean deleteAll() throws LocalException {
		return billService.deleteAllEntity();
	}

}
