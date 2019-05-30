package it.ttsolution.form.tt.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.DiningTableDto;
import it.ttsolution.form.tt.api.dao.DiningTable;
import it.ttsolution.form.tt.api.repository.DiningTableRepository;
import it.ttsolution.form.tt.api.service.interfaces.InterfaceService;

@Service
public class DiningTableService implements InterfaceService {

	@Autowired
	DiningTableRepository diningTableRepository;
	
	@Autowired
	DiningTableService diningTableService;
	@Autowired
	StatusService statusService;

// Metodi Controller

	public List<DiningTableDto> getAllDiningTablesAsDtoList() {
		List<DiningTableDto> listTableDto = new ArrayList<>();
		diningTableRepository.findAll().forEach(e -> listTableDto.add(fromDaoToDto(e)));
		return listTableDto;
	}

	public DiningTableDto getDiningTableAsDto(Integer id) {
		DiningTableDto diningTableDto = new DiningTableDto();
		if (isExistingId(id))
			diningTableRepository.findById(id).ifPresent(
					e -> diningTableDto.setAll(e.getId(), e.getSize(), statusService.getIdFromStatus(e.getStatus())));
		return diningTableDto;
	}

	public Boolean deleteDiningTable(Integer id) {
		if (isExistingId(id)) {
			diningTableRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public DiningTableDto saveDiningTable(DiningTableDto diningTableDto) {
		return fromDaoToDto(diningTableRepository.save(fromDtoToDao(diningTableDto)));
	}

	public Boolean setStatusOfDiningTable(Integer idTable, Integer idStatus) {
		if (isExistingId(idTable) & statusService.isExistingId(idStatus)) {
			DiningTableDto table;
			table = getDiningTableAsDto(idTable);
			table = setStatus(table, idStatus);
			diningTableRepository.save(fromDtoToDao(table));
			return true;
		}
		return false;
	}

	public Boolean setStatusOfAllDiningTables(Integer idStatus) {
		if (statusService.isExistingId(idStatus)) {
			List<DiningTableDto> list = getAllDiningTablesAsDtoList();
			for (DiningTableDto table : list) {
				setStatusOfDiningTable(table.getId(), idStatus);
			}
			return true;
		}
		return false;
	}

// Metodi di supporto

	DiningTableDto fromDaoToDto(DiningTable table) {
		DiningTableDto dto = new DiningTableDto();
		dto.setAll(table.getId(), table.getSize(), statusService.getIdFromStatus(table.getStatus()));
		return dto;
	}

	DiningTable fromDtoToDao(DiningTableDto tableDto) {
		DiningTable dao;
		dao = setAllDaoParams(tableDto.getId(), tableDto.getSize(), tableDto.getStatus());
		return dao;
	}

	DiningTable setAllDaoParams(Integer id, Integer size, Integer idStatus) {
		DiningTable dao = new DiningTable();
		if (isPositiveId(id))
			dao.setId(id);
		else
			dao.setId(0);
		dao.setSize(size);
		dao.setStatus(statusService.getStatusFromId(idStatus));
		return dao;
	}

	Integer getIdFromDiningTable(DiningTable table) {
		return table.getId();
	}

	DiningTable getDiningTableFromId(Integer id) {
		return fromDtoToDao(getDiningTableAsDto(id));
	}

	public Boolean isExistingId(Integer id) {
		if (id != null && diningTableRepository.existsById(id))
			return true;
		return false;
	}

	private DiningTableDto setStatus(DiningTableDto dto, Integer idStatus) {
		if (statusService.isExistingId(idStatus))
			dto.setStatus(idStatus);
		return dto;
	}

}
