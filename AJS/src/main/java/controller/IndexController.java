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

import dto.DishDto;
import feign.DishFeign;

@RestController
@RequestMapping(value="/dishes")
public class IndexController {
	
	@Autowired
	private DishFeign dishClient;
	 
	@GetMapping
	public List<DishDto> getAllDish() {
		return dishClient.getAllDish();
	}
	
	@DeleteMapping(value = "/delete", produces = "application/json")
	public Boolean deleteDish(@RequestParam Integer id) {
		return dishClient.deleteDish(id);
	}
	
	@PostMapping(value = "/db/dishes/", produces = "application/json")
	public String saveDish(@RequestBody DishDto dto) {
		return dishClient.saveDish(dto);
	}
}
