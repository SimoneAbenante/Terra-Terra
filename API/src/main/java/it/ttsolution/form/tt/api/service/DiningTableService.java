package it.ttsolution.form.tt.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.LocalException;
import it.ttsolution.form.tt.api.entity.DiningTable;
import it.ttsolution.form.tt.api.repository.DiningTableRepository;
import it.ttsolution.form.tt.api.service.interfaces.InterfaceService;

import static it.ttsolution.form.tt.api.service.StatusService.statusSetFailMessage;

@Service
public class DiningTableService implements InterfaceService<DiningTable> {

	@Autowired
	DiningTableRepository diningTableRepository;

	@Autowired
	DiningTableService diningTableService;
	@Autowired
	StatusService statusService;

	@Override
	public List<DiningTable> getAllEntityAsList() throws LocalException {
		List<DiningTable> listDiningTable = diningTableRepository.findAll();
		if (!listDiningTable.isEmpty())
			return listDiningTable;
		throw new LocalException(getAllFailMessage);
	}

	@Override
	public DiningTable getEntity(Integer id) throws LocalException {
		if (isValidId(id)) {
			DiningTable diningTable = new DiningTable();
			diningTableRepository.findById(id).ifPresent(e -> {
				diningTable.setId(e.getId());
				diningTable.setSize(e.getSize());
				diningTable.setStatus(e.getStatus());
			});
			return diningTable;
		}
		throw new LocalException(getFailMessage);
	}

	@Override
	public Boolean deleteEntity(Integer id) throws LocalException {
		if (isValidId(id)) {
			diningTableRepository.deleteById(id);
			return true;
		}
		throw new LocalException(deleteFailMessage);
	}

	@Override
	public Boolean deleteAllEntity() throws LocalException {
		List<DiningTable> listDiningTable = getAllEntityAsList();
		for (DiningTable bill : listDiningTable) {
			if (!deleteEntity(bill.getId()))
				throw new LocalException(deleteAllFailMessage);
		}
		return true;
	}

	@Override
	public DiningTable saveEntity(DiningTable diningTable) throws LocalException {
		if (diningTable != null) {
			if (!isPositiveId(diningTable.getStatus().getId()))
				setStatusOfEntity(diningTable.getId(), 3);
			DiningTable dt = diningTableRepository.save(diningTable);
			if (dt != null)
				return dt;
		}
		throw new LocalException(setFailMessage);
	}

	@Override
	public List<DiningTable> saveEntityList(List<DiningTable> listDiningTable) throws LocalException {
		if (!listDiningTable.isEmpty()) {
			List<DiningTable> list = new ArrayList<>();
			for (DiningTable diningTable : listDiningTable) {
				list.add(saveEntity(diningTable));
			}
			return list;
		}
		throw new LocalException(setListFailMessage);
	}

	@Override
	public Boolean isValidId(Integer id) throws LocalException {
		if (isPositiveId(id) & diningTableRepository.existsById(id))
			return true;
		return false;
	}

//Gestione dello status per i tavoli

	public DiningTable setStatusOfEntity(Integer idTable, Integer idStatus) throws LocalException {
		if (isValidId(idTable) & statusService.isValidId(idStatus)) {
			DiningTable diningTable;
			diningTable = getEntity(idTable);
			diningTable.setStatus(statusService.getEntity(idStatus));
			return saveEntity(diningTable);
		}
		throw new LocalException(statusSetFailMessage);
	}

	@Deprecated
	public Boolean setStatusOfDiningTableReturnBoolean(Integer idTable, Integer idStatus) throws LocalException {
		if (isValidId(idTable) & statusService.isValidId(idStatus)) {
			DiningTable diningTable;
			diningTable = getEntity(idTable);
			diningTable.setStatus(statusService.getEntity(idStatus));
			saveEntity(diningTable);
			return true;
		}
		throw new LocalException(statusSetFailMessage);
	}

	public List<DiningTable> setStatusOfAllEntity(Integer idStatus) throws LocalException {
		if (statusService.isValidId(idStatus)) {
			List<DiningTable> listDiningTable = getAllEntityAsList();
			List<DiningTable> list = new ArrayList<>();
			for (DiningTable diningTable : listDiningTable) {
				diningTable.setStatus(statusService.getEntity(idStatus));
				list.add(saveEntity(diningTable));
			}
			return list;
		}
		throw new LocalException(statusSetFailMessage);
	}

	@Deprecated
	public Boolean setStatusOfAllDiningTablesReturnBoolean(Integer idStatus) throws LocalException {
		if (statusService.isValidId(idStatus)) {
			List<DiningTable> listDiningTable = getAllEntityAsList();
			for (DiningTable diningTable : listDiningTable) {
				diningTable.setStatus(statusService.getEntity(idStatus));
				saveEntity(diningTable);
			}
			return true;
		}
		throw new LocalException(statusSetFailMessage);
	}

}
