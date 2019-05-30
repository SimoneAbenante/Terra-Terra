package it.ttsolution.form.tt.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.DiningTableDto;
import it.ttsolution.form.tt.api.service.DiningTableService;


@RequestMapping("/tables")
@RestController
public class DiningTableController {
	
	@Autowired
	public DiningTableService diningTableService;
	
	@GetMapping(value = "/", produces = "application/json")
	public List<DiningTableDto> getAllDiningTable() {
		return diningTableService.getAllDiningTablesAsDtoList();
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public DiningTableDto getDiningTableById(@PathVariable Integer id) {
		return diningTableService.getDiningTableAsDto(id);
	}
	
	@PostMapping(value = "/", produces = "application/json")
	public DiningTableDto saveDiningTable(@RequestBody DiningTableDto dto) {
		return diningTableService.saveDiningTable(dto);
	}
	
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public Boolean deleteDiningTable(@PathVariable Integer id) {
		return diningTableService.deleteDiningTable(id);
	}
	
	@PostMapping(value = "/status", produces = "application/json")
	public Boolean setAllDiningTableStatus(@RequestParam Integer idStatus) {
		return diningTableService.setStatusOfAllDiningTables(idStatus); 
	}
	
	@PostMapping(value = "/{id}/status/", produces = "application/json")
	public Boolean setDiningTableStatus(@PathVariable Integer idTable, @RequestParam Integer idStatus) {
		return diningTableService.setStatusOfDiningTable(idTable, idStatus); 
	}
	
}
