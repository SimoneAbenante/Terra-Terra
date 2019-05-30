package it.ttsolution.form.tt.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.JobDto;
import dto.Table_Dishes;
import it.ttsolution.form.tt.api.dao.Job;
import it.ttsolution.form.tt.api.repository.JobRepository;
import it.ttsolution.form.tt.api.service.interfaces.InterfaceService;

@Service
public class JobService implements InterfaceService {

	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	JobService jobService;
	@Autowired
	BillService billService;
	@Autowired
	DiningTableService diningTableService;
	@Autowired
	DishService dishService;
	@Autowired
	StatusService statusService;

//Metodi Controller

	public List<JobDto> getAllJobsAsDtoList() {
		List<JobDto> listJobDto = new ArrayList<>();
		jobRepository.findAll().forEach(e -> listJobDto.add(fromDaoToDto(e)));
		return listJobDto;
	}

	public JobDto getJobAsDto(Integer id) {
		JobDto jobDto = new JobDto();
		jobRepository.findById(id)
				.ifPresent(e -> jobDto.setAll(e.getId(), billService.getIdFromBill(e.getBill()),
						diningTableService.getIdFromDiningTable(e.getDiningTable()),
						dishService.getIdFromDish(e.getDish()), statusService.getIdFromStatus(e.getStatus())));
		return jobDto;
	}

	public List<JobDto> getAllJobsAsDtoListByBillId(Integer idBill) {
		List<JobDto> listJobDto = new ArrayList<>();
		jobRepository.findAllByIdBill(idBill).forEach(e -> listJobDto.add(fromDaoToDto(e)));
		return listJobDto;
	}

	public Boolean deleteJob(Integer idJob) {
		if (isExistingId(idJob)) {
			jobRepository.findById(idJob).ifPresent(e -> {
				jobRepository.delete(e);
				billService.setTotalAndVariation(e.getBill().getId(), 0.0);
				resetDiningTableStatusIfNoJobsForBill(e.getBill().getId(), e.getDiningTable().getId());
			});
			return true;
		}
		return false;
	}

	public JobDto saveJob(JobDto jobDto) {
		Job job;
		job = jobRepository.save(fromDtoToDao(jobDto));
		billService.setTotalAndVariation(jobDto.getIdBill(), 0.0);
		return fromDaoToDto(job);
	}

	public List<JobDto> saveJobList(Integer idBill, Table_Dishes list) {
		List<JobDto> jobDtoList = new ArrayList<>();
		Integer localIdBill = 0;
		if (isPositiveId(idBill))
			localIdBill = idBill;
		if (diningTableService.isExistingId(list.getIdDiningTable())) {
			JobDto jobDto;
			for (Integer dish : list.getDishes()) {
				jobDto = new JobDto();
				if (dishService.isExistingId(dish)) {
					jobDto.setAll(0, localIdBill, list.getIdDiningTable(), dish, 1);
					jobDtoList.add(saveJob(jobDto));
				}
			}
		}
		return jobDtoList;
	}

	public JobDto setJobStatus(Integer idJob, Integer idStatus) {
		JobDto jobDto = new JobDto();
		if (isExistingId(idJob) & statusService.isExistingId(idStatus)) {
			jobDto = getJobAsDto(idJob);
			jobDto = setStatus(idStatus);
			saveJob(jobDto);
		}
		return jobDto;
	}

// Metodi di supporto

	JobDto fromDaoToDto(Job job) {
		JobDto dto = new JobDto();
		dto.setAll(job.getId(), billService.getIdFromBill(job.getBill()),
				diningTableService.getIdFromDiningTable(job.getDiningTable()), dishService.getIdFromDish(job.getDish()),
				statusService.getIdFromStatus(job.getStatus()));
		return dto;
	}

	Job fromDtoToDao(JobDto jobDto) {
		Job dao;
		dao = setAllDaoParams(jobDto.getId(), jobDto.getIdBill(), jobDto.getIdDiningTable(), jobDto.getIdDish(),
				jobDto.getIdStatus());
		return dao;
	}

	Job setAllDaoParams(Integer id, Integer idBill, Integer idDiningTable, Integer idDish, Integer idStatus) {
		Job dao = new Job();
		if (isPositiveId(id))
			dao.setId(id);
		else
			dao.setId(0);
		dao.setBill(billService.getBillFromId(idBill));
		dao.setDiningTable(diningTableService.getDiningTableFromId(idDiningTable));
		dao.setDish(dishService.getDishFromId(idDish));
		dao.setStatus(statusService.getStatusFromId(idStatus));
		return dao;
	}

	public Boolean isExistingId(Integer id) {
		if (id != null && jobRepository.existsById(id))
			return true;
		return false;
	}

	private void resetDiningTableStatusIfNoJobsForBill(Integer idBill, Integer idTable) {
		List<JobDto> jobDtoList = getAllJobsAsDtoListByBillId(idBill);
		if (jobDtoList.isEmpty())
			diningTableService.setStatusOfDiningTable(idTable, 3);
	}

	private JobDto setStatus(Integer idStatus) {
		JobDto jobDto = new JobDto();
		jobDto.setIdStatus(idStatus);
		return jobDto;
	}

}
