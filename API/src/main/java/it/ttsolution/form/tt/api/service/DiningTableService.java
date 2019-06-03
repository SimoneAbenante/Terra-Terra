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
		DiningTable diningTable = new DiningTable();
		if (isValidId(id)) {
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
		DiningTable dd = diningTableRepository.save(diningTable);
		if (dd != null)
			return dd;
		throw new LocalException(setFailMessage);
	}

	@Override
	public List<DiningTable> saveEntityList(List<DiningTable> listDiningTable) throws LocalException {
		if (listDiningTable.isEmpty())
			throw new LocalException(deleteFailMessage);
		List<DiningTable> list = new ArrayList<>();
		for (DiningTable diningTable : listDiningTable) {
			list.add(saveEntity(diningTable));
		}
		return list;
	}

	@Override
	public Boolean isValidId(Integer id) throws LocalException {
		if (isPositiveId(id) & diningTableRepository.existsById(id))
			return true;
		return false;
	}

//Gestione dello status per i tavoli

	public DiningTable setStatusOfDiningTable(Integer idTable, Integer idStatus) throws LocalException {
		if (isValidId(idTable) & statusService.isValidId(idStatus)) {
			DiningTable diningTable;
			diningTable = getEntity(idTable);
			diningTable.setStatus(statusService.getEntity(idStatus));
			return saveEntity(diningTable);
		}
		throw new LocalException(statusSetFailMessage);
	}

	public List<DiningTable> setStatusOfAllDiningTables(Integer idStatus) throws LocalException {
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

}
