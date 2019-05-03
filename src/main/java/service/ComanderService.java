package service;

import static dto.JobDto.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.*;
import exception.LocalException;
import service.enu.LocalEnum;

@Service
public class ComanderService {

	@Autowired
	LocalService localService;

	public Boolean setOrder(Comander comander) {
		Boolean test = false;
		comander.setBill((BillDto) localService.createNewDto(LocalEnum.BILL));
		comander.getJobList().forEach(e -> {
			localService.saveDto(e, LocalEnum.JOB);
			comander.getBill().setTotal(comander.getBill().getTotal()
					+ ((DishDto) localService.getDto(e.getDish().getId(), LocalEnum.DISH)).getPrice());
		});
		test = true;
		return test;
	}

	public Comander getOrder(BillDto bill) throws LocalException {
		Comander comander = new Comander(bill, createJobList(bill));
		return comander;
	}

	public Boolean deleteOrder(Comander comander) {
		boolean test = true;
		comander.getJobList().forEach(e -> localService.deleteDto(e.getId(), LocalEnum.JOB));
		if (localService.deleteDto(comander.getBill().getId(), LocalEnum.BILL))
			;
		else
			test = false;
		return test;
	}

	private List<JobDto> createJobList(BillDto bill) {
		List<JobDto> list = new ArrayList<>();
		localService.localJob.findAll().forEach(e -> {
			if (e.getBill().getId() == bill.getId())
				list.add(new JobDto(e.getId(), fromBilltoDto(e.getBill()), fromDiningTableToDto(e.getDiningTable()),
						fromDishToDto(e.getDish()), biteToBoolean(e.getDone())));
			else
				;
		});
		return list;
	}

	public List<JobDto> deleteJob(Comander comander, int id) {
		List<JobDto> jobList = comander.getJobList();
		jobList.remove(id);
		localService.localJob.deleteById(id);
		return jobList;
	}

}
