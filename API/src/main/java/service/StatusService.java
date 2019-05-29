package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Status;
import dto.StatusDto;
import repository.StatusRepository;
import service.interfaces.InterfaceService;

@Service
public class StatusService implements InterfaceService {

	@Autowired
	StatusRepository statusRepository;
	
	@Autowired
	StatusService statusService;

// Metodi Controller

	public List<StatusDto> getAllStatusesAsDtoList() {
		List<StatusDto> listDishDto = new ArrayList<>();
		statusRepository.findAll().forEach(e -> listDishDto.add(fromDaoToDto(e)));
		return listDishDto;
	}

	public StatusDto getStatusAsDto(Integer id) {
		StatusDto statusDto = new StatusDto();
		if (isExistingId(id))
			statusRepository.findById(id).ifPresent(e -> statusDto.setAll(e.getId(), e.getStatus()));
		return statusDto;
	}

	public StatusDto saveStatus(StatusDto statusDto) {
		statusRepository.save(fromDtoToDao(statusDto));
		return statusDto;
	}

	public Boolean deleteStatus(Integer id) {
		if (isExistingId(id)) {
			statusRepository.deleteById(id);
			return true;
		}
		return false;
	}

// Metodi di supporto

	StatusDto fromDaoToDto(Status status) {
		StatusDto dto = new StatusDto();
		dto.setAll(status.getId(), status.getStatus());
		return dto;
	}

	Status fromDtoToDao(StatusDto statusDto) {
		Status dao;
		dao = setAllDaoParams(statusDto.getId(), statusDto.getStatus());
		return dao;
	}

	Status setAllDaoParams(Integer id, String status) {
		Status dao = new Status();
		if (isPositiveId(id))
			dao.setId(id);
		else
			dao.setId(0);
		dao.setStatus(status);
		return dao;
	}

	Integer getIdFromStatus(Status status) {
		return status.getId();
	}

	Status getStatusFromId(Integer id) {
		return fromDtoToDao(getStatusAsDto(id));
	}

	public Boolean isExistingId(Integer id) {
		if (id != null && statusRepository.existsById(id))
			return true;
		return false;
	}

}
