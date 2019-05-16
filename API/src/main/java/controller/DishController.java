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

import dao.Dish;
import dto.DishDto;
import service.DishService;


@RequestMapping("/dishes")
@RestController
public class DishController {
	
	@Autowired
	public DishService dishService;

	@GetMapping(value = "/", produces = "application/json")
	public List<DishDto> getAllDish() {
		return dishService.getAllDishAsDtoList();
	}
	
	@GetMapping(value = "/id", produces = "application/json")
	public DishDto getDishById(@RequestParam Integer id) {
		return dishService.getDishAsDto(id);
	}
	
	@PostMapping(value = "/", produces = "application/json")
	public Dish saveDish(@RequestBody DishDto dto) {
		return dishService.saveDish(dto);
	}
	
	@DeleteMapping(value = "/delete", produces = "application/json")
	public Boolean deleteDish(@RequestParam Integer id) {
		return dishService.deleteDish(id);
	}
	
}
