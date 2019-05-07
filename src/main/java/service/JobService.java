package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Bill;
import dao.Job;
import dto.JobDto;
import dto.Bill_Dishes;
import dto.ID;
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
			listJobDto.add(fromJobToJobDto(e));
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

	//Da controllare
	public List<JobDto> getAllJobAsDtoListByBillId(Integer idBill) {
		List<JobDto> listJobDto = new ArrayList<>();
		localJob.findAllByIdBill(idBill).forEach(e -> {
			listJobDto.add(fromJobToJobDto(e));
		});
		return listJobDto;
	}

	public Boolean deleteJob(Integer idJob) {
		Boolean test = false;
		if (idJob != null && localJob.existsById(idJob)) {
			//oggetti contenenti Integer id
			ID idBill = new ID();
			ID idTable = new ID();
			localJob.findById(idJob).ifPresent(e -> {
				// Sottrae valore del piatto al totale
				e.getBill().setTotal(e.getBill().getTotal() - e.getDish().getPrice());
				idBill.setId(e.getBill().getId());
				idTable.setId(e.getDiningTable().getId());
				localJob.delete(e);
			});
			// Se lista vuota togli prenotazione occupazione del tavolo
			if (getAllJobAsDtoListByBillId(idBill.getId()).isEmpty())
				diningTableService.setStatusOfDiningTable(idTable.getId(), 3);
			test = true;
		}
		return test;
	}

	public JobDto saveJobById(Integer idBill, Integer idTable, Integer idDish) {
		Job job = new Job();
		job.setBill(getBillContolled(idBill));
		job.setDiningTable(diningTableService.getDiningTableById(idTable));
		// Cambiare lo status del tavolo ad occupato
		diningTableService.setStatusOfDiningTable(job.getDiningTable().getId(), 1);
		job.setDish(dishService.getDishById(idDish));
		// Aggiunge il valore del piatto al totale
		job.getBill().setTotal(job.getBill().getTotal() + job.getDish().getPrice());
		statusService.localStatus.findById(1).ifPresent(e -> job.setStatus(e));
		localJob.save(job);
		return fromJobToJobDto(job);
	}

	public List<JobDto> saveMultipleJobById(Integer idBill, Bill_Dishes list) {
		List<JobDto> jobDtoList = new ArrayList<>();
		for (Integer dish : list.getDishes()) {
			jobDtoList.add(saveJobById(idBill, list.getIdDiningTable(), dish));
		}
		return jobDtoList;
	}

	public Boolean setJobStatus(Integer idJob, Integer idStatus) {
		Boolean test = false;
		Job job = new Job();
		if (idJob != null & idStatus != null & localJob.existsById(idJob)
				& statusService.localStatus.existsById(idStatus)) {
			localJob.findById(idJob).ifPresent(e -> {
				statusService.localStatus.findById(idStatus).ifPresent(i -> job.setStatus(i));
				job.setId(e.getId());
				job.setBill(e.getBill());
				job.setDiningTable(e.getDiningTable());
				job.setDish(e.getDish());
			});
			localJob.save(job);
		}
		return test;
	}
	
	// Da terminare
	public Bill getBillContolled(Integer id) {
		Bill bill = new Bill();
		if (id == null | id < 0)
			id = 0;
		if (billService.localBill.existsById(id))
			billService.localBill.findById(id).ifPresent(e -> {
				bill.setId(e.getId());
				bill.setPaymentMethod(e.getPaymentMethod());
				bill.setTotal(e.getTotal());
			});
		else {
			bill.setPaymentMethod("Undefined");
			bill.setTotal(0.0);
			billService.localBill.save(bill);
		}
		return bill;
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
