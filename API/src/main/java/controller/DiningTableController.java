package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dao.DiningTable;
import dao.Status;
import dto.DiningTableDto;
import service.DiningTableService;


@RequestMapping("/tables")
@RestController
public class DiningTableController {
	
	@Autowired
	public DiningTableService diningTableService;
	
	@GetMapping(value = "/", produces = "application/json")
	public List<DiningTableDto> getAllDiningTable() {
		return diningTableService.getAllDiningTableAsDtoList();
	}
	
	@GetMapping(value = "/id", produces = "application/json")
	public DiningTableDto getDiningTableById(@RequestParam Integer id) {
		return diningTableService.getDiningTableById(id);
	}
	
	@PostMapping(value = "/", produces = "application/json")
	public DiningTable saveDiningTable(@RequestBody DiningTableDto dto) {
		return diningTableService.saveDiningTable(dto);
	}
	
	@DeleteMapping(value = "/id", produces = "application/json")
	public Boolean deleteDish(@RequestParam Integer id) {
		return diningTableService.deleteDiningTable(id);
	}
	
	@PostMapping(value = "/status", produces = "application/json")
	public Boolean setAllDiningTableStatus(@RequestParam Integer idStatus) {
		return diningTableService.setStatusOfAllDiningTable(idStatus); 
	}
	@PostMapping(value = "/status/id", produces = "application/json")
	public Status setDiningTableStatus(@RequestParam Integer idTable, @RequestParam Integer idStatus) {
		return diningTableService.setStatusOfDiningTable(idTable, idStatus); 
	}
	
}
