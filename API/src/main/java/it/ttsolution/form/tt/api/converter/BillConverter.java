package it.ttsolution.form.tt.api.converter;

import org.springframework.stereotype.Component;

import dto.BillDto;
import exception.LocalException;
import it.ttsolution.form.tt.api.converter.interfaces.InterfaceConverter;
import it.ttsolution.form.tt.api.entity.Bill;

@Component
public class BillConverter implements InterfaceConverter<Bill, BillDto> {

	@Override
	public BillDto getDtoFromEntity(Bill entity) throws LocalException {
		if (isValidConversion(entity)) {
			BillDto dto = new BillDto();
			dto.setId(entity.getId());
			dto.setPaymentMethod(entity.getPaymentMethod());
			dto.setTotal(entity.getTotal());
			return dto;
		}
		throw new LocalException(conversionDtoFromEntityFailMessage);
	}

	@Override
	public Bill getEntityFromDto(BillDto dto) throws LocalException {
		if (isValidConversion(dto)) {
			Bill entity = new Bill();
			entity.setId(dto.getId());
			entity.setPaymentMethod(dto.getPaymentMethod());
			entity.setTotal(dto.getTotal());
			return entity;
		}
		throw new LocalException(conversionEntityFromDtoFailMessage);
	}
}
