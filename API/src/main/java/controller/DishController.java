package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.DishDto;
import service.DishService;


@RequestMapping("/dishes")
@RestController
public class DishController {
	
	@Autowired
	public DishService dishService;

	@GetMapping(value = "/", produces = "application/json")
	public List<DishDto> getAllDish() {
		return dishService.getAllDishesAsDtoList();
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public DishDto getDishById(@PathVariable Integer id) {
		return dishService.getDishAsDto(id);
	}
	
	@PostMapping(value = "/", produces = "application/json")
	public DishDto saveDish(@RequestBody DishDto dto) {
		return dishService.saveDish(dto);
	}
	
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public Boolean deleteDish(@PathVariable Integer id) {
		return dishService.deleteDish(id);
	}
	
}
