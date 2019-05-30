package it.ttsolution.form.tt.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.StatusDto;
import it.ttsolution.form.tt.api.service.StatusService;

@RequestMapping("/statuses")
@RestController
public class StatusController {
	
	@Autowired
	StatusService statusService;
	
	@GetMapping(value = "/", produces = "application/json")
	public List<StatusDto> getAllStatus() {
		return statusService.getAllStatusesAsDtoList();
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public StatusDto getStatusById(@PathVariable Integer id) {
		return statusService.getStatusAsDto(id);
	}
	
	@PostMapping(value = "/", produces = "application/json")
	public StatusDto saveStatus(@RequestBody StatusDto statusDto) {
		return statusService.saveStatus(statusDto);
	}
	
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public Boolean deleteStatus(@PathVariable Integer id) {
		return statusService.deleteStatus(id);
	}
	
}
