package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Dish;
import dto.DishDto;
import repository.DishRepository;
import service.interfaces.InterfaceService;

@Service
public class DishService implements InterfaceService {

	@Autowired
	DishRepository dishRepository;
	
//Metodi Controller

	public List<DishDto> getAllDishesAsDtoList() {
		List<DishDto> listDishDto = new ArrayList<>();
		dishRepository.findAll().forEach(e -> listDishDto.add(fromDaoToDto(e)));
		return listDishDto;
	}

	public DishDto getDishAsDto(Integer id) {
		DishDto dishDto = new DishDto();
		if (isExistingId(id))
			dishRepository.findById(id).ifPresent(e -> dishDto.setAll(e.getId(), e.getName(), e.getPrice()));
		return dishDto;
	}

	public Boolean deleteDish(Integer id) {
		if (isExistingId(id)) {
			dishRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public DishDto saveDish(DishDto dishDto) {
		dishRepository.save(fromDtoToDao(dishDto));
		return dishDto;
	}

// Metodi di supporto

	DishDto fromDaoToDto(Dish dish) {
		DishDto dto = new DishDto();
		dto.setAll(dish.getId(), dish.getName(), dish.getPrice());
		return dto;
	}

	Dish fromDtoToDao(DishDto dishDto) {
		Dish dao;
		dao = setAllDaoParams(dishDto.getId(), dishDto.getName(), dishDto.getPrice());
		return dao;
	}

	Dish setAllDaoParams(Integer id, String name, Double price) {
		Dish dao = new Dish();
		if (isPositiveId(id))
			dao.setId(id);
		else
			dao.setId(0);
		dao.setName(name);
		dao.setPrice(price);
		return dao;
	}

	Integer getIdFromDish(Dish dish) {
		return dish.getId();
	}

	Dish getDishFromId(Integer id) {
		return fromDtoToDao(getDishAsDto(id));
	}

	Double getPriceFromDishId(Integer idDish) {
		return getDishAsDto(idDish).getPrice();
	}

	public Boolean isExistingId(Integer id) {
		if (id != null && dishRepository.existsById(id))
			return true;
		return false;
	}

}
