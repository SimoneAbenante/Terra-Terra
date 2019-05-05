package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dao.Job;
import dto.JobDto;
import service.JobService;

@RequestMapping("/job")
@RestController
public class JobController {

	@Autowired
	public JobService jobService;

	@GetMapping(value = "/get", produces = "application/json")
	public List<JobDto> getAllJob() {
		return jobService.getAllJobAsDtoList();
	}

	@PostMapping(value = "/set", produces = "application/json")
	public Job saveJob(@RequestBody Integer idBill, @RequestBody Integer idDiningTable, @RequestBody Integer idDish) {
		return jobService.saveJobById(idBill, idDiningTable, idDish);
	}

	@DeleteMapping(value = "/delete", produces = "application/json")
	public Boolean deleteJob(@RequestBody Integer id) {
		return jobService.deleteJob(id);
	}

}
