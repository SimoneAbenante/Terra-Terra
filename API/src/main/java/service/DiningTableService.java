package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DiningTable;
import dao.Status;
import dto.DiningTableDto;
import repository.DiningTableRepository;

@Service
public class DiningTableService {

	@Autowired
	DiningTableRepository localTable;
	@Autowired
	StatusService statusService;

	public List<DiningTableDto> getAllDiningTableAsDtoList() {
		List<DiningTableDto> listTableDto = new ArrayList<>();
		localTable.findAll().forEach(e -> {
			listTableDto.add(fromDiningTableToDto(e));
		});
		return listTableDto;
	}

	public DiningTableDto getDiningTableAsDto(Integer id) {
		DiningTableDto diningTableDto = new DiningTableDto();
		if (id != null && localTable.existsById(id))
			localTable.findById(id).ifPresent(e -> {
				diningTableDto.setId(e.getId());
				diningTableDto.setSize(e.getSize());
				diningTableDto.setStatus(e.getStatus().getId());
			});
		return diningTableDto;
	}

	public DiningTableDto getDiningTableById(Integer id) {
		DiningTable diningTable = new DiningTable();
		if (id != null && localTable.existsById(id))
			localTable.findById(id).ifPresent(e -> {
				diningTable.setId(e.getId());
				diningTable.setSize(e.getSize());
				diningTable.setStatus(e.getStatus());
			});
		return fromDiningTableToDto(diningTable);
	}

	public Boolean deleteDiningTable(Integer id) {
		Boolean test = false;
		if (id != null && localTable.existsById(id)) {
			localTable.deleteById(id);
			test = true;
		}
		return test;
	}

	public DiningTable saveDiningTable(DiningTableDto diningTableDto) {
		DiningTable table = new DiningTable();
		if (diningTableDto.getId() != null && diningTableDto.getId() > 0)
			table.setId(diningTableDto.getId());
		table.setSize(diningTableDto.getSize());
		table.setStatus(statusService.getStatusById(diningTableDto.getStatus()));
		localTable.save(table);
		return table;
	}

	public Status setStatusOfDiningTable(Integer idTable, Integer idStatus) {
		DiningTable table = new DiningTable();
		if ((idTable != null & idStatus != null)
				&& (localTable.existsById(idTable) & statusService.localStatus.existsById(idStatus))) {
			localTable.findById(idTable).ifPresent(e -> {
				statusService.localStatus.findById(idStatus).ifPresent(i -> {
					table.setStatus(i);
				});
				table.setId(e.getId());
				table.setSize(e.getSize());
			});
			localTable.save(table);
		}
		return table.getStatus();
	}

	public Boolean setStatusOfAllDiningTable(Integer idStatus) {
		Boolean test = false;
		List<DiningTableDto> list = getAllDiningTableAsDtoList();
		if (idStatus != null) {
			for (DiningTableDto table : list) {
				setStatusOfDiningTable(table.getId(), idStatus);
			}
			test = true;
		}
		return test;
	}

	public static DiningTableDto fromDiningTableToDto(DiningTable table) {
		DiningTableDto diningTableDto = new DiningTableDto();
		diningTableDto.setId(table.getId());
		diningTableDto.setSize(table.getSize());
		diningTableDto.setStatus(table.getStatus().getId());
		return diningTableDto;
	}

}
