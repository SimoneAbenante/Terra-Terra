package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DiningTable;
import dto.DiningTableDto;
import rep.TableRepository;

@Service
public class DiningTableService {
	
	@Autowired
	TableRepository localTable;

	public List<DiningTableDto> getAllDiningTableAsDtoList() {
		List<DiningTableDto> listTableDto = new ArrayList<>();
		DiningTableDto diningTableDto = new DiningTableDto();
		localTable.findAll().forEach(e -> {
			diningTableDto.setId(e.getId());
			diningTableDto.setSize(e.getSize());
			listTableDto.add(diningTableDto);
		});
		return listTableDto;
	}

	public DiningTableDto getDiningTableAsDto(Integer id) {
		DiningTableDto diningTableDto = new DiningTableDto();
		localTable.findById(id).ifPresent(e -> {
			diningTableDto.setId(e.getId());
			diningTableDto.setSize(e.getSize());
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
		localTable.save(table);
		return table;
	}

}
