package service;

import static dto.BillDto.*;
import static dto.JobDto.*;
import static dto.DishDto.*;
import static dto.DiningTableDto.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Bill;
import dao.Job;
import dto.JobDto;
import rep.BillRepository;
import rep.DishRepository;
import rep.JobRepository;
import rep.TableRepository;

@Service
public class JobService {

	@Autowired
	BillRepository localBill;
	@Autowired
	JobRepository localJob;
	@Autowired
	TableRepository localTable;
	@Autowired
	DishRepository localDish;

	public List<JobDto> getAllJobAsDtoList() {
		List<JobDto> listJobDto = new ArrayList<>();
		JobDto jobDto = new JobDto();
		localJob.findAll().forEach(e -> {
			jobDto.setId(e.getId());
			jobDto.setBill(fromBillToDto(e.getBill()));
			jobDto.setDiningTable(fromDiningTableToDto(e.getDiningTable()));
			jobDto.setDish(fromDishToDto(e.getDish()));
			jobDto.setDone(fromByteToBoolean(e.getDone()));
			listJobDto.add(jobDto);
		});
		return listJobDto;
	}

	public JobDto getJobAsDto(Integer id) {
		JobDto jobDto = new JobDto();
		localJob.findById(id).ifPresent(e -> {
			jobDto.setId(e.getId());
			jobDto.setBill(fromBillToDto(e.getBill()));
			jobDto.setDiningTable(fromDiningTableToDto(e.getDiningTable()));
			jobDto.setDish(fromDishToDto(e.getDish()));
			jobDto.setDone(fromByteToBoolean(e.getDone()));
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

	public Job saveJobByDto(JobDto jobDto) {
		Job job = new Job();
		if (jobDto.getId() > 0)
			job.setId(jobDto.getId());
		if (localBill.existsById(jobDto.getBill().getId()))
			job.setBill(fromDtoToBill(jobDto.getBill()));
		else
			job.setBill(localBill.save(fromDtoToBill(jobDto.getBill())));
		job.setDiningTable(fromDtoToDiningTable(jobDto.getDiningTable()));
		job.setDish(fromDtoToDish(jobDto.getDish()));
		// Add dish price to bill total
		job.getBill().setTotal(job.getBill().getTotal() + job.getDish().getPrice());
		job.setDone(fromBooleanToByte(jobDto.getDone()));
		localJob.save(job);
		return job;
	}

	public Job saveJobById(Integer idBill, Integer idTable, Integer idDish) {
		Job job = new Job();
		if (idBill > 0 && localBill.existsById(idBill))
			localBill.findById(idBill).ifPresent(e -> job.setBill(e));
		else {
			job.setBill(localBill.save(new Bill()));
		}
		localTable.findById(idTable).ifPresent(e -> job.setDiningTable(e));
		localDish.findById(idDish).ifPresent(e -> job.setDish(e));
		// Add dish price to bill total
		job.getBill().setTotal(job.getBill().getTotal() + job.getDish().getPrice());
		job.setDone(fromBooleanToByte(false));
		localJob.save(job);
		return job;
	}

}
