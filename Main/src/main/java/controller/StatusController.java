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

import dao.Status;
import dto.StatusDto;
import service.StatusService;

@RequestMapping("/status")
@RestController
public class StatusController {
	
	@Autowired
	StatusService statusService;
	
	@GetMapping(value = "/get", produces = "application/json")
	public List<StatusDto> getAllJob() {
		return statusService.getAllStatusAsDtoList();
	}
	
	@GetMapping(value = "/getById", produces = "application/json")
	public Status getStatusById(@RequestParam Integer id) {
		return statusService.getStatusById(id);
	}
	
	@PostMapping(value = "/set", produces = "application/json")
	public Status saveStatus(@RequestParam StatusDto statusDto) {
		return statusService.saveStatus(statusDto);
	}
	
	@DeleteMapping(value = "/delete", produces = "application/json")
	public Boolean deleteStatus(@RequestBody Integer id) {
		return statusService.deleteStatus(id);
	}
	
}
