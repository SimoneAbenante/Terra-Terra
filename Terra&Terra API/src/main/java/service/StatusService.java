package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Status;
import dto.StatusDto;
import rep.StatusRepository;

@Service
public class StatusService {

	@Autowired
	StatusRepository localStatus;
	
	public List<StatusDto> getAllStatusAsDtoList() {
		List<StatusDto> listDishDto = new ArrayList<>();
		localStatus.findAll().forEach(e -> {
			listDishDto.add(fromStatusToDto(e));
		});
		return listDishDto;
	}

	public Status getStatusById(Integer id) {
		Status status = new Status();
		if (id != null && localStatus.existsById(id))
			localStatus.findById(id).ifPresent(e -> {
				status.setId(e.getId());
				status.setStatus(e.getStatus());
			});
		return status;
	}

	public Status saveStatus(StatusDto statusDto) {
		Status status = new Status();
		if (statusDto.getId() != null && statusDto.getId() > 0)
			status.setId(statusDto.getId());
		status.setStatus(statusDto.getStatus());
		localStatus.save(status);
		return status;
	}
	
	public Boolean deleteStatus(Integer id) {
		Boolean test = false;
		if (id != null && localStatus.existsById(id)) {
			localStatus.deleteById(id);
			test = true;
		}
		return test;
	}

	public static StatusDto fromStatusToDto(Status status) {
		StatusDto statusDto = new StatusDto();
		statusDto.setId(status.getId());
		statusDto.setStatus(status.getStatus());
		return statusDto;
	}

}
