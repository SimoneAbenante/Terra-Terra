package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Dish;
import dto.DishDto;
import rep.DishRepository;

@Service
public class DishService {

	@Autowired
	DishRepository localDish;

	public List<DishDto> getAllDishAsDtoList() {
		List<DishDto> listDishDto = new ArrayList<>();
		localDish.findAll().forEach(e -> {
			listDishDto.add(fromDishToDto(e));
		});
		return listDishDto;
	}

	public DishDto getDishAsDto(Integer id) {
		DishDto dishDto = new DishDto();
		if (id != null && localDish.existsById(id))
		localDish.findById(id).ifPresent(e -> {
			dishDto.setId(e.getId());
			dishDto.setName(e.getName());
			dishDto.setPrice(e.getPrice());
		});
		return dishDto;
	}
	
	public Dish getDishById(Integer id) {
		Dish dish = new Dish();
		if (id != null && localDish.existsById(id))
		localDish.findById(id).ifPresent(e -> {
			dish.setId(e.getId());
			dish.setName(e.getName());
			dish.setPrice(e.getPrice());
		});
		return dish;
	}

	public Boolean deleteDish(Integer id) {
		Boolean test = false;
		if (id != null && localDish.existsById(id)) {
			localDish.deleteById(id);
			test = true;
		}
		return test;
	}

	public Dish saveDish(DishDto dishDto) {
		Dish dish = new Dish();
		if (dishDto.getId() != null && dishDto.getId() > 0)
			dish.setId(dishDto.getId());
		dish.setName(dishDto.getName());
		dish.setPrice(dishDto.getPrice());
		localDish.save(dish);
		return dish;
	}
	
	public static DishDto fromDishToDto(Dish dish) {
		DishDto dishDto = new DishDto();
		dishDto.setId(dish.getId());
		dishDto.setName(dish.getName());
		dishDto.setPrice(dish.getPrice());
		return dishDto;
	}

}
