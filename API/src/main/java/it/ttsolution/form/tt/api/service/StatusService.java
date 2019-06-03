package it.ttsolution.form.tt.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.LocalException;
import it.ttsolution.form.tt.api.entity.Status;
import it.ttsolution.form.tt.api.repository.StatusRepository;
import it.ttsolution.form.tt.api.service.interfaces.InterfaceService;

@Service
public class StatusService implements InterfaceService<Status> {

	public static final String statusSetFailMessage = "Status non aggiornato:\n"
			+ "Impossibile cambiare lo stato dell'oggetto\n";

	@Autowired
	StatusRepository statusRepository;

	@Override
	public List<Status> getAllEntityAsList() throws LocalException {
		List<Status> listStatus = statusRepository.findAll();
		if (!listStatus.isEmpty())
			return listStatus;
		throw new LocalException(getAllFailMessage);
	}

	@Override
	public Status getEntity(Integer id) throws LocalException {
		if (isValidId(id)) {
			Status status = new Status();
			statusRepository.findById(id).ifPresent(e -> {
				status.setId(e.getId());
				status.setStatus(e.getStatus());
			});
			return status;
		}
		throw new LocalException(getFailMessage);
	}

	@Override
	public Boolean deleteEntity(Integer id) throws LocalException {
		if (isValidId(id)) {
			statusRepository.deleteById(id);
			return true;
		}
		throw new LocalException(deleteFailMessage);
	}

	@Override
	public Boolean deleteAllEntity() throws LocalException {
		List<Status> listStatus = getAllEntityAsList();
		for (Status status : listStatus) {
			if (deleteEntity(status.getId()))
				throw new LocalException(deleteAllFailMessage);
		}
		return true;
	}

	@Override
	public Status saveEntity(Status status) throws LocalException {
		if (status != null) {
			Status s = statusRepository.save(status);
			if (s != null)
				return s;
		}
		throw new LocalException(setFailMessage);
	}

	@Override
	public List<Status> saveEntityList(List<Status> listStatus) throws LocalException {
		if (!listStatus.isEmpty()) {
			List<Status> list = new ArrayList<>();
			for (Status status : listStatus) {
				list.add(saveEntity(status));
			}
			return list;
		}
		throw new LocalException(setListFailMessage);
	}

	@Override
	public Boolean isValidId(Integer id) throws LocalException {
		if (isPositiveId(id) & statusRepository.existsById(id))
			return true;
		return false;
	}

}
