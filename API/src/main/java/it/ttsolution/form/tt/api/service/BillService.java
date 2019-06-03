package it.ttsolution.form.tt.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.LocalException;
import it.ttsolution.form.tt.api.entity.Bill;
import it.ttsolution.form.tt.api.entity.Job;
import it.ttsolution.form.tt.api.repository.BillRepository;
import it.ttsolution.form.tt.api.service.interfaces.InterfaceService;

@Service
public class BillService implements InterfaceService<Bill> {

	static final String totalUpdateMessage = "Totale non Aggiornato:\n"
			+ "Impossibile aggiornare il totale poiché non ci sono ordinazioni per questo conto\n";
	static final String totalNullMessage = "Attenzione totale aggiornato a (0.0):\n"
			+ "Totale Aggiornato ed impostato a (0.0), controllare la variazione\n";

	@Autowired
	BillRepository billRepository;

	@Autowired
	JobService jobService;

	@Override
	public List<Bill> getAllEntityAsList() throws LocalException {
		List<Bill> listBill = billRepository.findAll();
		if (!listBill.isEmpty())
			return listBill;
		throw new LocalException(getAllFailMessage);
	}

	@Override
	public Bill getEntity(Integer id) throws LocalException {
		Bill bill = new Bill();
		if (isValidId(id)) {
			billRepository.findById(id).ifPresent(e -> {
				bill.setId(e.getId());
				bill.setPaymentMethod(e.getPaymentMethod());
				bill.setTotal(e.getTotal());
			});
			return bill;
		}
		throw new LocalException(getFailMessage);
	}

	@Override
	public Boolean deleteEntity(Integer id) throws LocalException {
		if (isValidId(id)) {
			billRepository.deleteById(id);
			return true;
		}
		throw new LocalException(deleteFailMessage);
	}

	@Override
	public Boolean deleteAllEntity() throws LocalException {
		List<Bill> listBill = getAllEntityAsList();
		for (Bill bill : listBill) {
			if (deleteEntity(bill.getId()))
				throw new LocalException(deleteAllFailMessage);
		}
		return true;
	}

	@Override
	public Bill saveEntity(Bill bill) throws LocalException {
		Bill b = billRepository.save(bill);
		if (b != null)
			return b;
		throw new LocalException(setFailMessage);
	}

	@Override
	public List<Bill> saveEntityList(List<Bill> listBill) throws LocalException {
		if (listBill.isEmpty())
			throw new LocalException(deleteFailMessage);
		List<Bill> list = new ArrayList<>();
		for (Bill bill : listBill) {
			list.add(saveEntity(bill));
		}
		return list;
	}

	@Override
	public Boolean isValidId(Integer id) throws LocalException {
		if (isPositiveId(id) & billRepository.existsById(id))
			return true;
		return false;
	}

//Gestione del totale Per il conto

	public Double setTotalAndVariation(Integer idBill, Double variation) throws LocalException {
		Double total = 0.0;
		Bill bill = getEntity(idBill);
		List<Job> jobList = jobService.getAllEntityAsListByBillId(idBill);
		if (jobList.isEmpty())
			throw new LocalException(totalUpdateMessage);
		for (Job job : jobList) {
			total += job.getDish().getPrice();
		}
		if (isValidVariationForTotal(variation, total))
			total += -variation;
		else
			total = 0.0;
		bill.setTotal(total);
		saveEntity(bill);
		if (total != 0)
			return total;
		throw new LocalException(totalNullMessage);
	}

	private Boolean isValidVariationForTotal(Double variation, Double total) {
		if (variation != null && variation < total)
			return true;
		return false;
	}

}
