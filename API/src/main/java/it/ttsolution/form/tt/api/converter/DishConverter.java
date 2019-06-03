package it.ttsolution.form.tt.api.converter;

import org.springframework.stereotype.Component;

import dto.DishDto;
import exception.LocalException;
import it.ttsolution.form.tt.api.converter.interfaces.InterfaceConverter;
import it.ttsolution.form.tt.api.entity.Dish;

@Component
public class DishConverter implements InterfaceConverter<Dish, DishDto> {

	@Override
	public DishDto getDtoFromEntity(Dish entity) throws LocalException {
		if (isValidConversion(entity)) {
			DishDto dto = new DishDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setPrice(entity.getPrice());
			return dto;
		}
		throw new LocalException(conversionDtoFromEntityFailMessage);
	}

	@Override
	public Dish getEntityFromDto(DishDto dto) throws LocalException {
		if (isValidConversion(dto)) {
			Dish entity = new Dish();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setPrice(dto.getPrice());
			return entity;
		}
		throw new LocalException(conversionEntityFromDtoFailMessage);
	}

}
