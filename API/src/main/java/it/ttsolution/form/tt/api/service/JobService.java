package it.ttsolution.form.tt.api.service;

import static it.ttsolution.form.tt.api.service.StatusService.statusSetFailMessage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.LocalException;
import it.ttsolution.form.tt.api.entity.Job;
import it.ttsolution.form.tt.api.repository.JobRepository;
import it.ttsolution.form.tt.api.service.interfaces.InterfaceService;

@Service
public class JobService implements InterfaceService<Job> {

	public static final String getListJobByIdFailMessage = "Id del conto non valido:\n"
			+ "Impossibile recuperare la lista di ordini per questo conto\n";

	@Autowired
	JobRepository jobRepository;

	@Autowired
	BillService billService;
	@Autowired
	DiningTableService diningTableService;
	@Autowired
	DishService dishService;
	@Autowired
	StatusService statusService;

	@Override
	public List<Job> getAllEntityAsList() throws LocalException {
		List<Job> listJob = jobRepository.findAll();
		if (!listJob.isEmpty())
			return listJob;
		throw new LocalException(getAllFailMessage);
	}

	@Override
	public Job getEntity(Integer id) throws LocalException {
		if (isValidId(id)) {
			Job job = new Job();
			jobRepository.findById(id).ifPresent(e -> {
				job.setId(e.getId());
				job.setBill(e.getBill());
				job.setDiningTable(e.getDiningTable());
				job.setDish(e.getDish());
				job.setStatus(e.getStatus());
			});
			return job;
		}
		throw new LocalException(getFailMessage);
	}

	@Override
	public Boolean deleteEntity(Integer id) throws LocalException {
		if (isValidId(id)) {
			jobRepository.deleteById(id);
			return true;
		}
		throw new LocalException(deleteFailMessage);
	}

	@Override
	public Boolean deleteAllEntity() throws LocalException {
		List<Job> listJob = getAllEntityAsList();
		for (Job job : listJob) {
			if (deleteEntity(job.getId()))
				throw new LocalException(deleteAllFailMessage);
		}
		return true;
	}

	@Override
	public Job saveEntity(Job job) throws LocalException {
		if (job != null) {
			if (!isPositiveId(job.getStatus().getId()))
				setStatusOfEntity(job.getId(), 3);
			Job j = jobRepository.save(job);
			if (j != null)
				return j;
		}
		throw new LocalException(setFailMessage);
	}

	@Override
	public List<Job> saveEntityList(List<Job> listJob) throws LocalException {
		if (!listJob.isEmpty()) {
			List<Job> list = new ArrayList<>();
			for (Job job : listJob) {
				list.add(saveEntity(job));
			}
			return list;
		}
		throw new LocalException(setListFailMessage);
	}

	@Override
	public Boolean isValidId(Integer id) throws LocalException {
		if (isPositiveId(id) & jobRepository.existsById(id))
			return true;
		return false;
	}

	public List<Job> getAllEntityAsListByBillId(Integer idBill) throws LocalException {
		if (billService.isValidId(idBill)) {
			List<Job> listJob = new ArrayList<>();
			jobRepository.findAllByIdBill(idBill).forEach(e -> {
				listJob.add(e);
			});
			return listJob;
		}
		throw new LocalException(getListJobByIdFailMessage);
	}

//Gestione dello status per i tavoli

	public Job setStatusOfEntity(Integer idJob, Integer idStatus) throws LocalException {
		if (isValidId(idJob) & statusService.isValidId(idStatus)) {
			Job job;
			job = getEntity(idJob);
			job.setStatus(statusService.getEntity(idStatus));
			return saveEntity(job);
		}
		throw new LocalException(statusSetFailMessage);
	}

	public List<Job> setStatusOfAllEntity(Integer idStatus) throws LocalException {
		if (statusService.isValidId(idStatus)) {
			List<Job> listJob = getAllEntityAsList();
			List<Job> list = new ArrayList<>();
			for (Job job : listJob) {
				job.setStatus(statusService.getEntity(idStatus));
				list.add(saveEntity(job));
			}
			return list;
		}
		throw new LocalException(statusSetFailMessage);
	}

}
