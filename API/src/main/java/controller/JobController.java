package controller;

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

import dto.JobDto;
import dto.Table_Dishes;
import service.JobService;

@RequestMapping("/jobs")
@RestController
public class JobController {

	@Autowired
	public JobService jobService;

	@GetMapping(value = "/", produces = "application/json")
	public List<JobDto> getAllJob() {
		return jobService.getAllJobsAsDtoList();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public JobDto getJobById(@PathVariable Integer id) {
		return jobService.getJobAsDto(id);
	}

	@GetMapping(value = "/bill", produces = "application/json")
	public List<JobDto> getAllJobByBillId(@RequestParam Integer idBill) {
		return jobService.getAllJobsAsDtoListByBillId(idBill);
	}

	@PostMapping(value = "/", produces = "application/json")
	public JobDto saveJobById(@RequestBody JobDto jobDto) {
		return jobService.saveJob(jobDto);
	}

	@PostMapping(value = "/params", produces = "application/json")
	public List<JobDto> saveJobListById(@RequestParam(required = false) Integer idBill,
			@RequestBody Table_Dishes listOfBill_Dishes) {
		return jobService.saveJobList(idBill, listOfBill_Dishes);
	}

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public Boolean deleteJob(@PathVariable Integer id) {
		return jobService.deleteJob(id);
	}

}
