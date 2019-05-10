package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Status;
import rep.StatusRepository;

@Service
public class StatusService {

	@Autowired
	StatusRepository localStatus;

	public Status getStatusById(Integer id) {
		Status status = new Status();
			localStatus.findById(id).ifPresent(e-> {
				status.setId(e.getId());
				status.setStatus(e.getStatus());
			});
		return status;
	}
	
//	public StatusDto setStatusById(Integer id) {
//		return getStatusById(id);
//	}

}
