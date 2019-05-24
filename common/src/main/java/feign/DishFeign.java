package feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import dto.DishDto;

@FeignClient("TT-API-Client")
public interface DishFeign {

	@GetMapping(value = "/db/dishes", produces = "application/json")
	public List<DishDto> getAllDish();
	
	@DeleteMapping(value = "/db/dishes/delete", produces = "application/json")
	public Boolean deleteDish(@RequestParam Integer id);
	
	@PostMapping(value = "/db/dishes/", produces = "application/json")
	public String saveDish(@RequestBody DishDto dto);
}
