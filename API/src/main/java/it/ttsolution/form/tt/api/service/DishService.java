package it.ttsolution.form.tt.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.LocalException;
import it.ttsolution.form.tt.api.entity.Dish;
import it.ttsolution.form.tt.api.repository.DishRepository;
import it.ttsolution.form.tt.api.service.interfaces.InterfaceService;

@Service
public class DishService implements InterfaceService<Dish> {

	@Autowired
	DishRepository dishRepository;

	@Override
	public List<Dish> getAllEntityAsList() throws LocalException {
		List<Dish> listDish = dishRepository.findAll();
		if (!listDish.isEmpty())
			return listDish;
		throw new LocalException(getAllFailMessage);
	}

	@Override
	public Dish getEntity(Integer id) throws LocalException {
		Dish dish = new Dish();
		if (isValidId(id)) {
			dishRepository.findById(id).ifPresent(e -> {
				dish.setId(e.getId());
				dish.setName(e.getName());
				dish.setPrice(e.getPrice());
			});
			return dish;
		}
		throw new LocalException(getFailMessage);
	}

	@Override
	public Boolean deleteEntity(Integer id) throws LocalException {
		if (isValidId(id)) {
			dishRepository.deleteById(id);
			return true;
		}
		throw new LocalException(deleteFailMessage);
	}

	@Override
	public Boolean deleteAllEntity() throws LocalException {
		List<Dish> listDish = getAllEntityAsList();
		for (Dish dish : listDish) {
			if (deleteEntity(dish.getId()))
				throw new LocalException(deleteAllFailMessage);
		}
		return true;
	}

	@Override
	public Dish saveEntity(Dish dish) throws LocalException {
		Dish d = dishRepository.save(dish);
		if (d != null)
			return d;
		throw new LocalException(setFailMessage);
	}

	@Override
	public List<Dish> saveEntityList(List<Dish> listDish) throws LocalException {
		if (listDish.isEmpty())
			throw new LocalException(deleteFailMessage);
		List<Dish> list = new ArrayList<>();
		for (Dish dish : listDish) {
			list.add(saveEntity(dish));
		}
		return list;
	}

	@Override
	public Boolean isValidId(Integer id) throws LocalException {
		if (isPositiveId(id) & dishRepository.existsById(id))
			return true;
		return false;
	}

}
