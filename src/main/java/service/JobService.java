package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Bill;
import dao.Job;
import dto.JobDto;
import rep.JobRepository;

@Service
public class JobService {

	@Autowired
	JobRepository localJob;
	@Autowired
	BillService billService;
	@Autowired
	DiningTableService diningTableService;
	@Autowired
	DishService dishService;
	@Autowired
	StatusService statusService;

	public List<JobDto> getAllJobAsDtoList() {
		List<JobDto> listJobDto = new ArrayList<>();
		localJob.findAll().forEach(e -> {
			JobDto jobDto = new JobDto();
			jobDto.setId(e.getId());
			jobDto.setId_bill(e.getBill().getId());
			jobDto.setId_diningTable(e.getDiningTable().getId());
			jobDto.setId_dish(e.getDish().getId());
			jobDto.setId_status(e.getStatus().getId());
			listJobDto.add(jobDto);
		});
		return listJobDto;
	}

	public JobDto getJobAsDto(Integer id) {
		JobDto jobDto = new JobDto();
		localJob.findById(id).ifPresent(e -> {
			jobDto.setId(e.getId());
			jobDto.setId_bill(e.getBill().getId());
			jobDto.setId_diningTable(e.getDiningTable().getId());
			jobDto.setId_dish(e.getDish().getId());
			jobDto.setId_status(e.getStatus().getId());
		});
		return jobDto;
	}

	public Boolean deleteJob(Integer id) {
		Boolean test = false;
		if (localJob.existsById(id)) {
			//Subtract dish price to bill total
			localJob.findById(id).ifPresent(e -> e.getBill().setTotal(e.getBill().getTotal() - e.getDish().getPrice()));
			localJob.deleteById(id);
			test = true;
		}
		return test;
	}

	public JobDto saveJobById(Integer idBill, Integer idTable, Integer idDish) {
		Job job = new Job();
		//Scoppia qui
		if (idBill > 0 & billService.localBill.existsById(idBill))
			billService.localBill.findById(idBill).ifPresent(e -> job.setBill(e));
		else {
			Bill bill = new Bill();
			job.setBill(billService.localBill.save(bill));
		}
		diningTableService.localTable.findById(idTable).ifPresent(e -> job.setDiningTable(e));
		dishService.localDish.findById(idDish).ifPresent(e -> job.setDish(e));
		// Add dish price to bill total
		job.getBill().setTotal(job.getBill().getTotal() + job.getDish().getPrice());
		statusService.localStatus.findById(3).ifPresent(e -> job.setStatus(e));
		localJob.save(job);
		return fromJobToJobDto(job);
	}
	
	public static JobDto fromJobToJobDto(Job job) {
		JobDto jobDto = new JobDto();
		jobDto.setId(job.getId());
		jobDto.setId_bill(job.getBill().getId());
		jobDto.setId_diningTable(job.getDiningTable().getId());
		jobDto.setId_dish(job.getDish().getId());
		jobDto.setId_status(job.getStatus().getId());
		return jobDto;
	}

}
