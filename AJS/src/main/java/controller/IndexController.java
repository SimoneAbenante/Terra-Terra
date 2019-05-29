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

import dto.BillDto;
import dto.DiningTableDto;
import dto.DishDto;
import dto.JobDto;
import dto.StatusDto;
import dto.Table_Dishes;
import feign.TT_API_Feign;;

@RestController
@RequestMapping(value="/dishes")
public class IndexController {
	
	@Autowired
	private TT_API_Feign client;
	 
	@GetMapping(value = "/", produces = "application/json")
	public List<BillDto> getAllBill() {
		return client.getAllBill();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public BillDto getBillById(@PathVariable Integer id) {
		return client.getBillById(id);
	}

	@PostMapping(value = "/", produces = "application/json")
	public BillDto saveBill(@RequestBody BillDto dto) {
		return client.saveBill(dto);
	}

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public Boolean deleteBill(@PathVariable Integer id) {
		return client.deleteBill(id);
	}
	
	@GetMapping(value = "/", produces = "application/json")
	public List<DishDto> getAllDish() {
		return client.getAllDish();
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public DishDto getDishById(@PathVariable Integer id) {
		return client.getDishById(id);
	}
	
	@PostMapping(value = "/", produces = "application/json")
	public DishDto saveDish(@RequestBody DishDto dto) {
		return client.saveDish(dto);
	}
	
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public Boolean deleteDish(@PathVariable Integer id) {
		return client.deleteDish(id);
	}
	
	@GetMapping(value = "/", produces = "application/json")
	public List<DiningTableDto> getAllDiningTable() {
		return client.getAllDiningTable();
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public DiningTableDto getDiningTableById(@PathVariable Integer id) {
		return client.getDiningTableById(id);
	}
	
	@PostMapping(value = "/", produces = "application/json")
	public DiningTableDto saveDiningTable(@RequestBody DiningTableDto dto) {
		return client.saveDiningTable(dto);
	}
	
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public Boolean deleteDiningTable(@PathVariable Integer id) {
		return deleteDiningTable(id);
	}
	
	@PostMapping(value = "/status", produces = "application/json")
	public Boolean setAllDiningTableStatus(@RequestParam Integer idStatus) {
		return client.setAllDiningTableStatus(idStatus);
	}
	
	@PostMapping(value = "/{id}/status/", produces = "application/json")
	public Boolean setDiningTableStatus(@PathVariable Integer idTable, @RequestParam Integer idStatus) {
		return client.setDiningTableStatus(idTable, idStatus);
	}
	
	@GetMapping(value = "/", produces = "application/json")
	public List<JobDto> getAllJob() {
		return client.getAllJob();
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public JobDto getJobById(@PathVariable Integer id) {
		return client.getJobById(id);
	}

	@GetMapping(value = "/bill", produces = "application/json")
	public List<JobDto> getAllJobByBillId(@RequestParam Integer idBill) {
		return client.getAllJobByBillId(idBill);
	}

	@PostMapping(value = "/", produces = "application/json")
	public JobDto saveJobById(@RequestBody JobDto jobDto) {
		return client.saveJobById(jobDto);
	}

	@PostMapping(value = "/params", produces = "application/json")
	public List<JobDto> saveJobListById(@RequestParam(required = false) Integer idBill,
			@RequestBody Table_Dishes listOfBill_Dishes) {
		return client.saveJobListById(idBill, listOfBill_Dishes);
	}

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public Boolean deleteJob(@PathVariable Integer id) {
		return client.deleteJob(id);
	}
	
	@GetMapping(value = "/", produces = "application/json")
	public List<StatusDto> getAllStatus() {
		return client.getAllStatus();
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public StatusDto getStatusById(@PathVariable Integer id) {
		return client.getStatusById(id);
	}
	
	@PostMapping(value = "/", produces = "application/json")
	public StatusDto saveStatus(@RequestBody StatusDto statusDto) {
		return client.saveStatus(statusDto);
	}
	
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public Boolean deleteStatus(@PathVariable Integer id) {
		return client.deleteStatus(id);
	}
}
