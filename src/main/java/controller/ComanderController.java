package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.BillDto;
import dto.Comander;
import dto.JobDto;
import exception.LocalException;
import service.ComanderService;

@RequestMapping("/comand")
@RestController
public class ComanderController {

	@Autowired
	ComanderService comanderService;
	
	@PostMapping(value = "/set", produces = "application/json")
	public Boolean setOrder(@RequestBody Comander comander) {
		return comanderService.setOrder(comander);
	}
	
	@GetMapping(value = "/get", produces = "application/json")
	public Comander getOrder(@RequestBody BillDto bill) throws LocalException {
		return comanderService.getOrder(bill);
	}
	
	@DeleteMapping(value = "/deleteall", produces = "application/json")
	public Boolean deleteOrder(@RequestBody Comander comander) {
		return comanderService.deleteOrder(comander);
	}
	
	@DeleteMapping(value = "/deletejob", produces = "application/json")
	public List<JobDto> deleteJobFromOrder(@RequestBody Comander comander, int id) {
		return comanderService.deleteJob(comander, id);
	}
	
}
