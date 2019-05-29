package feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import dto.BillDto;
import dto.DiningTableDto;
import dto.DishDto;
import dto.JobDto;
import dto.StatusDto;
import dto.Table_Dishes;

@FeignClient("TT-API-Client")
public interface TT_API_Feign {

//Bill Controller

	@GetMapping(value = "/", produces = "application/json")
	public List<BillDto> getAllBill();

	@GetMapping(value = "/{id}", produces = "application/json")
	public BillDto getBillById(@PathVariable Integer id);

	@PostMapping(value = "/", produces = "application/json")
	public BillDto saveBill(@RequestBody BillDto dto);

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public Boolean deleteBill(@PathVariable Integer id);

//Dish Controller

	@GetMapping(value = "/", produces = "application/json")
	public List<DishDto> getAllDish();
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public DishDto getDishById(@PathVariable Integer id);
	
	@PostMapping(value = "/", produces = "application/json")
	public DishDto saveDish(@RequestBody DishDto dto);
	
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public Boolean deleteDish(@PathVariable Integer id);
	
//DiningTable Controller
	
	@GetMapping(value = "/", produces = "application/json")
	public List<DiningTableDto> getAllDiningTable();
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public DiningTableDto getDiningTableById(@PathVariable Integer id);
	
	@PostMapping(value = "/", produces = "application/json")
	public DiningTableDto saveDiningTable(@RequestBody DiningTableDto dto);
	
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public Boolean deleteDiningTable(@PathVariable Integer id);
	
	@PostMapping(value = "/status", produces = "application/json")
	public Boolean setAllDiningTableStatus(@RequestParam Integer idStatus);
	
	@PostMapping(value = "/{id}/status/", produces = "application/json")
	public Boolean setDiningTableStatus(@PathVariable Integer idTable, @RequestParam Integer idStatus);
	
//Job Controller
	
	@GetMapping(value = "/", produces = "application/json")
	public List<JobDto> getAllJob();

	@GetMapping(value = "/{id}", produces = "application/json")
	public JobDto getJobById(@PathVariable Integer id);

	@GetMapping(value = "/bill", produces = "application/json")
	public List<JobDto> getAllJobByBillId(@RequestParam Integer idBill);

	@PostMapping(value = "/", produces = "application/json")
	public JobDto saveJobById(@RequestBody JobDto jobDto);

	@PostMapping(value = "/params", produces = "application/json")
	public List<JobDto> saveJobListById(@RequestParam(required = false) Integer idBill,
			@RequestBody Table_Dishes listOfBill_Dishes);

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public Boolean deleteJob(@PathVariable Integer id);
	
//Status Controller
	
	@GetMapping(value = "/", produces = "application/json")
	public List<StatusDto> getAllStatus();
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public StatusDto getStatusById(@PathVariable Integer id);
	
	@PostMapping(value = "/", produces = "application/json")
	public StatusDto saveStatus(@RequestBody StatusDto statusDto);
	
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public Boolean deleteStatus(@PathVariable Integer id);
	
}
