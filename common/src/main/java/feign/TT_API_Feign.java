package feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dto.BillDto;
import dto.DiningTableDto;
import dto.DishDto;
import dto.JobDto;
import dto.StatusDto;
import dto.Table_Dishes;

@FeignClient("TT-API-Client")
@RequestMapping(value="db")
public interface TT_API_Feign {

//Bill Controller

	@GetMapping(value = "/bills/", produces = "application/json")
	public List<BillDto> getAllBill();

	@GetMapping(value = "/bills/{id}", produces = "application/json")
	public BillDto getBillById(@PathVariable Integer id);

	@PostMapping(value = "/bills/", produces = "application/json")
	public BillDto saveBill(@RequestBody BillDto dto);

	@DeleteMapping(value = "/bills/{id}", produces = "application/json")
	public Boolean deleteBill(@PathVariable Integer id);

//Dish Controller

	@GetMapping(value = "/dishes/", produces = "application/json")
	public List<DishDto> getAllDish();
	
	@GetMapping(value = "/dishes/{id}", produces = "application/json")
	public DishDto getDishById(@PathVariable Integer id);
	
	@PostMapping(value = "/dishes/", produces = "application/json")
	public DishDto saveDish(@RequestBody DishDto dto);
	
	@DeleteMapping(value = "/dishes/{id}", produces = "application/json")
	public Boolean deleteDish(@PathVariable Integer id);
	
//DiningTable Controller
	
	@GetMapping(value = "/tables/", produces = "application/json")
	public List<DiningTableDto> getAllDiningTable();
	
	@GetMapping(value = "/tables/{id}", produces = "application/json")
	public DiningTableDto getDiningTableById(@PathVariable Integer id);
	
	@PostMapping(value = "/tables/", produces = "application/json")
	public DiningTableDto saveDiningTable(@RequestBody DiningTableDto dto);
	
	@DeleteMapping(value = "/tables/{id}", produces = "application/json")
	public Boolean deleteDiningTable(@PathVariable Integer id);
	
	@PostMapping(value = "/tables/status", produces = "application/json")
	public Boolean setAllDiningTableStatus(@RequestParam Integer idStatus);
	
	@PostMapping(value = "/tables/{id}/status/", produces = "application/json")
	public Boolean setDiningTableStatus(@PathVariable Integer idTable, @RequestParam Integer idStatus);
	
//Job Controller
	
	@GetMapping(value = "/jobs/", produces = "application/json")
	public List<JobDto> getAllJob();

	@GetMapping(value = "/jobs/{id}", produces = "application/json")
	public JobDto getJobById(@PathVariable Integer id);

	@GetMapping(value = "/jobs/bill", produces = "application/json")
	public List<JobDto> getAllJobByBillId(@RequestParam Integer idBill);

	@PostMapping(value = "/jobs/", produces = "application/json")
	public JobDto saveJobById(@RequestBody JobDto jobDto);

	@PostMapping(value = "/jobs/params", produces = "application/json")
	public List<JobDto> saveJobListById(@RequestParam(required = false) Integer idBill,
			@RequestBody Table_Dishes listOfBill_Dishes);

	@DeleteMapping(value = "/jobs/{id}", produces = "application/json")
	public Boolean deleteJob(@PathVariable Integer id);
	
//Status Controller
	
	@GetMapping(value = "/statuses/", produces = "application/json")
	public List<StatusDto> getAllStatus();
	
	@GetMapping(value = "/statuses/{id}", produces = "application/json")
	public StatusDto getStatusById(@PathVariable Integer id);
	
	@PostMapping(value = "/statuses/", produces = "application/json")
	public StatusDto saveStatus(@RequestBody StatusDto statusDto);
	
	@DeleteMapping(value = "/statuses/{id}", produces = "application/json")
	public Boolean deleteStatus(@PathVariable Integer id);
	
}
