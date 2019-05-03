package controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.JobDto;
import dto.inter.InterfaceDto;
import service.LocalService;
import service.enu.LocalEnum;

@RequestMapping("/job")
@RestController
public class JobController {
	
	@Autowired
	public LocalService localService;

	@GetMapping(value = "/get", produces = "application/json")
	public List<InterfaceDto> getAllJob() {
		return localService.getAllDto(LocalEnum.JOB);
	}
	
	@PostMapping(value = "/set", produces = "application/json")
	public Serializable saveJob(@RequestBody JobDto dto) {
		return localService.saveDto(dto, LocalEnum.JOB);
	}
	
	@DeleteMapping(value = "/delete", produces = "application/json")
	public Boolean deleteJob(@RequestBody Integer id) {
		return localService.deleteDto(id, LocalEnum.JOB);
	}
	
}
