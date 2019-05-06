package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DiningTable;
import dto.DiningTableDto;
import rep.DiningTableRepository;

@Service
public class DiningTableService {
	
	@Autowired
	DiningTableRepository localTable;
	@Autowired
	StatusService statusService;

	public List<DiningTableDto> getAllDiningTableAsDtoList() {
		List<DiningTableDto> listTableDto = new ArrayList<>();
		localTable.findAll().forEach(e -> {
			DiningTableDto diningTableDto = new DiningTableDto();
			diningTableDto.setId(e.getId());
			diningTableDto.setSize(e.getSize());
			diningTableDto.setStatus(e.getStatus().getId());
			listTableDto.add(diningTableDto);
		});
		return listTableDto;
	}

	public DiningTableDto getDiningTableAsDto(Integer id) {
		DiningTableDto diningTableDto = new DiningTableDto();
		localTable.findById(id).ifPresent(e -> {
			diningTableDto.setId(e.getId());
			diningTableDto.setSize(e.getSize());
			diningTableDto.setStatus(e.getStatus().getId());
		});
		return diningTableDto;
	}

	public Boolean deleteDiningTable(Integer id) {
		Boolean test = false;
		localTable.deleteById(id);
		test = true;
		return test;
	}

	public DiningTable saveDiningTable(DiningTableDto diningTableDto) {
		DiningTable table = new DiningTable();
		if (diningTableDto.getId() > 0)
			table.setId(diningTableDto.getId());
		table.setSize(diningTableDto.getSize());
		table.setStatus(statusService.getStatusById(diningTableDto.getStatus()));
		localTable.save(table);
		return table;
	}
	
	
	public static DiningTable fromDtoToDiningTable(DiningTableDto diningTableDto) {
		DiningTable table = new DiningTable();
		table.setId(diningTableDto.getId());
		table.setSize(diningTableDto.getSize());
		return table;
	}
	
	public static DiningTableDto fromDiningTableToDto(DiningTable table) {
		DiningTableDto diningTableDto = new DiningTableDto();
		diningTableDto.setId(table.getId());
		diningTableDto.setSize(table.getSize());
		return diningTableDto;
	}

}
