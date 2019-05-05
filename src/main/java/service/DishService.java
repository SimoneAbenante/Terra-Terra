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
		DishDto dishDto = new DishDto();
		localDish.findAll().forEach(e -> {
			dishDto.setId(e.getId());
			dishDto.setName(e.getName());
			dishDto.setPrice(e.getPrice());
			listDishDto.add(dishDto);
		});
		return listDishDto;
	}

	public DishDto getDishAsDto(Integer id) {
		DishDto dishDto = new DishDto();
		localDish.findById(id).ifPresent(e -> {
			dishDto.setId(e.getId());
			dishDto.setName(e.getName());
			dishDto.setPrice(e.getPrice());
		});
		return dishDto;
	}

	public Boolean deleteDish(Integer id) {
		Boolean test = false;
		localDish.deleteById(id);
		test = true;
		return test;
	}

	public Dish saveDish(DishDto dishDto) {
		Dish dish = new Dish();
		if (dishDto.getId() > 0)
			dish.setId(dishDto.getId());
		dish.setName(dishDto.getName());
		dish.setPrice(dishDto.getPrice());
		localDish.save(dish);
		return dish;
	}
}
