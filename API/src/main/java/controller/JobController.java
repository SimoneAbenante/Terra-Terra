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

import dto.JobDto;
import dto.Bill_Dishes;
import service.JobService;

@RequestMapping("/jobs")
@RestController
public class JobController {

	@Autowired
	public JobService jobService;

	@GetMapping(value = "", produces = "application/json")
	public List<JobDto> getAllJob() {
		return jobService.getAllJobAsDtoList();
	}

	@GetMapping(value = "/id", produces = "application/json")
	public JobDto getJobById(@RequestParam Integer id) {
		return jobService.getJobAsDto(id);
	}

	@GetMapping(value = "/bill", produces = "application/json")
	public List<JobDto> getAllJobAsDtoListByBillId(@RequestParam Integer idBill) {
		return jobService.getAllJobAsDtoListByBillId(idBill);
	}

	@PostMapping(value = "/", produces = "application/json")
	public JobDto saveJobById(@RequestParam(required = false) Integer idBill, @RequestParam Integer idDiningTable,
			@RequestParam Integer idDish) {
		return jobService.saveJobById(idBill, idDiningTable, idDish);
	}

	@PostMapping(value = "/params", produces = "application/json")
	public List<JobDto> saveJobListById(@RequestParam(required = false) Integer idBill,
			@RequestBody Bill_Dishes listOfBill_Dishes) {
		return jobService.saveMultipleJobById(idBill, listOfBill_Dishes);
	}

	@DeleteMapping(value = "/delete", produces = "application/json")
	public Boolean deleteJob(@RequestBody Integer id) {
		return jobService.deleteJob(id);
	}

}
