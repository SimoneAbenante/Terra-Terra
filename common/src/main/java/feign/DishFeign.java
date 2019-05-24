package feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import dto.DishDto;

@FeignClient("dishes")
public interface DishFeign {

	@GetMapping(value = "/db/dishes", produces = "application/json")
	public List<DishDto> getAllDish();
}
