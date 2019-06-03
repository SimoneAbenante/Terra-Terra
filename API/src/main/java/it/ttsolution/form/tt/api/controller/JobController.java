package it.ttsolution.form.tt.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.JobDto;
import dto.Table_Dishes;
import exception.LocalException;
import it.ttsolution.form.tt.api.controller.interfaces.InterfaceController;
import it.ttsolution.form.tt.api.converter.JobConverter;
import it.ttsolution.form.tt.api.service.DiningTableService;
import it.ttsolution.form.tt.api.service.DishService;
import it.ttsolution.form.tt.api.service.JobService;

@RestController
@SuppressWarnings("deprecation")
@RequestMapping("/jobs")
public class JobController implements InterfaceController<JobDto> {

	@Autowired
	JobService jobService;
	@Autowired
	DiningTableService diningTableService;
	@Autowired
	DishService dishService;

	@Autowired
	JobConverter jobConverter;

	@Override
	public List<JobDto> getAll() throws LocalException {
		return jobConverter.getDtoListFromEntityList((jobService.getAllEntityAsList()));
	}

	@Override
	public JobDto getById(@PathVariable Integer id) throws LocalException {
		return jobConverter.getDtoFromEntity(jobService.getEntity(id));
	}

	@Override
	public JobDto save(@RequestBody JobDto dto) throws LocalException {
		return jobConverter.getDtoFromEntity(jobService.saveEntity(jobConverter.getEntityFromDto(dto)));
	}

	@Override
	public List<JobDto> saveAll(@RequestBody List<JobDto> listDto) throws LocalException {
		return jobConverter
				.getDtoListFromEntityList(jobService.saveEntityList(jobConverter.getEntityListFromDtoList(listDto)));
	}

	@Override
	public Boolean delete(@PathVariable Integer id) throws LocalException {
		return jobService.deleteEntity(id);
	}

	@Override
	public Boolean deleteAll() throws LocalException {
		return jobService.deleteAllEntity();
	}

	@PostMapping(value = "/status/all", produces = "application/json")
	public List<JobDto> setAllStatus(@RequestParam Integer idStatus) throws LocalException {
		return jobConverter.getDtoListFromEntityList(jobService.setStatusOfAllEntity(idStatus));
	}

	@PostMapping(value = "/status/{idTable}", produces = "application/json")
	public JobDto setStatus(@PathVariable Integer idTable, @RequestParam Integer idStatus) throws LocalException {
		return jobConverter.getDtoFromEntity(jobService.setStatusOfEntity(idTable, idStatus));
	}

	@Deprecated
	@PostMapping(value = "/jobs/params", produces = "application/json")
	public List<JobDto> saveJobListById(@RequestParam(required = false) Integer idBill,
			@RequestBody Table_Dishes table_Dishes) throws LocalException {
		Integer id = 0;
		if (jobService.isValidId(idBill))
			id = idBill;
		if (diningTableService.isValidId(table_Dishes.getId()) && !table_Dishes.getDishes().isEmpty()) {
			List<JobDto> listJob = new ArrayList<>();
			for (Integer dish : table_Dishes.getDishes()) {
				if (dishService.isValidId(dish)) {
					JobDto job = new JobDto();
					job.setIdBill(id);
					job.setIdDiningTable(table_Dishes.getId());
					job.setIdDish(dish);
					listJob.add(
							jobConverter.getDtoFromEntity(jobService.saveEntity(jobConverter.getEntityFromDto(job))));
				} else throw new LocalException(DishService.setListFailMessage);
			}
			return listJob;
		}
		throw new LocalException(DiningTableService.setListFailMessage);
	}

}
