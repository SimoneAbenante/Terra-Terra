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
	 
	@GetMapping(value = "/bills/", produces = "application/json")
	public List<BillDto> getAllBill() {
		return client.getAllBill();
	}

	@GetMapping(value = "/bills/{id}", produces = "application/json")
	public BillDto getBillById(@PathVariable Integer id) {
		return client.getBillById(id);
	}

	@PostMapping(value = "/bills/", produces = "application/json")
	public BillDto saveBill(@RequestBody BillDto dto) {
		return client.saveBill(dto);
	}

	@DeleteMapping(value = "/bills/{id}", produces = "application/json")
	public Boolean deleteBill(@PathVariable Integer id) {
		return client.deleteBill(id);
	}
	
	@GetMapping(value = "/dishes/", produces = "application/json")
	public List<DishDto> getAllDish() {
		return client.getAllDish();
	}
	
	@GetMapping(value = "/dishes/{id}", produces = "application/json")
	public DishDto getDishById(@PathVariable Integer id) {
		return getDishById(id);
	}
	
	@PostMapping(value = "/dishes/", produces = "application/json")
	public DishDto saveDish(@RequestBody DishDto dto) {
		return client.saveDish(dto);
	}
	
	@DeleteMapping(value = "/dishes/{id}", produces = "application/json")
	public Boolean deleteDish(@PathVariable Integer id) {
		return client.deleteDish(id);
	}
	
	@GetMapping(value = "/tables/", produces = "application/json")
	public List<DiningTableDto> getAllDiningTable() {
		return client.getAllDiningTable();
	}
	
	@GetMapping(value = "/tables/{id}", produces = "application/json")
	public DiningTableDto getDiningTableById(@PathVariable Integer id) {
		return client.getDiningTableById(id);
	}
	
	@PostMapping(value = "/tables/", produces = "application/json")
	public DiningTableDto saveDiningTable(@RequestBody DiningTableDto dto) {
		return client.saveDiningTable(dto);
	}
	
	@DeleteMapping(value = "/tables/{id}", produces = "application/json")
	public Boolean deleteDiningTable(@PathVariable Integer id) {
		return client.deleteDiningTable(id);
	}
	
	@PostMapping(value = "/tables/status", produces = "application/json")
	public Boolean setAllDiningTableStatus(@RequestParam Integer idStatus) {
		return client.setAllDiningTableStatus(idStatus);
	}
	
	@PostMapping(value = "/tables/{id}/status/", produces = "application/json")
	public Boolean setDiningTableStatus(@PathVariable Integer idTable, @RequestParam Integer idStatus) {
		return client.setDiningTableStatus(idTable, idStatus);
	}
	
	@GetMapping(value = "/jobs/", produces = "application/json")
	public List<JobDto> getAllJob() {
		return client.getAllJob();
	}

	@GetMapping(value = "/jobs/{id}", produces = "application/json")
	public JobDto getJobById(@PathVariable Integer id) {
		return client.getJobById(id);
	}

	@GetMapping(value = "/jobs/bill", produces = "application/json")
	public List<JobDto> getAllJobByBillId(@RequestParam Integer idBill) {
		return client.getAllJobByBillId(idBill);
	}

	@PostMapping(value = "/jobs/", produces = "application/json")
	public JobDto saveJobById(@RequestBody JobDto jobDto) {
		return client.saveJobById(jobDto);
	}

	@PostMapping(value = "/jobs/params", produces = "application/json")
	public List<JobDto> saveJobListById(@RequestParam(required = false) Integer idBill,
			@RequestBody Table_Dishes listOfBill_Dishes) {
		return client.saveJobListById(idBill, listOfBill_Dishes);
	}

	@DeleteMapping(value = "/jobs/{id}", produces = "application/json")
	public Boolean deleteJob(@PathVariable Integer id) {
		return client.deleteJob(id);
	}
	
	@GetMapping(value = "/statuses/", produces = "application/json")
	public List<StatusDto> getAllStatus() {
		return client.getAllStatus();
	}
	
	@GetMapping(value = "/statuses/{id}", produces = "application/json")
	public StatusDto getStatusById(@PathVariable Integer id) {
		return client.getStatusById(id);
	}
	
	@PostMapping(value = "/statuses/", produces = "application/json")
	public StatusDto saveStatus(@RequestBody StatusDto statusDto) {
		return client.saveStatus(statusDto);
	}
	
	@DeleteMapping(value = "/statuses/{id}", produces = "application/json")
	public Boolean deleteStatus(@PathVariable Integer id) {
		return client.deleteStatus(id);
	}
}
