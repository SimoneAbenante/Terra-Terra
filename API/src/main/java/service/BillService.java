package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Bill;
import dto.BillDto;
import dto.JobDto;
import repository.BillRepository;
import service.interfaces.InterfaceService;

@Service
public class BillService implements InterfaceService {

	@Autowired
	BillRepository billRepository;
	
	@Autowired
	BillService billService;
	@Autowired
	DishService dishService;
	@Autowired
	JobService jobService;

//Metodi Controller

	public List<BillDto> getAllBillsAsDtoList() {
		List<BillDto> listBillDto = new ArrayList<>();
		billRepository.findAll().forEach(e -> listBillDto.add(fromDaoToDto(e)));
		return listBillDto;
	}

	public BillDto getBillAsDto(Integer id) {
		BillDto billDto = new BillDto();
		if (isExistingId(id))
			billRepository.findById(id).ifPresent(e -> billDto.setAll(e.getId(), e.getPaymentMethod(), e.getTotal()));
		return billDto;
	}

	public Boolean deleteBill(Integer id) {
		if (isExistingId(id)) {
			billRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public BillDto saveBill(BillDto billDto) {
		billRepository.save(fromDtoToDao(billDto));
		return billDto;
	}

	public Double setTotalAndVariation(Integer idBill, Double variation) {
		Double total = 0.0;
		BillDto billDto = getBillAsDto(idBill);
		List<JobDto> jobDtoList = jobService.getAllJobsAsDtoListByBillId(idBill);
		for (JobDto j : jobDtoList) {
			total += dishService.getPriceFromDishId(j.getIdDish());
		}
		billDto = getBillUpdateWhithTotal(billDto, total);
		saveBill(billDto);
		return total;
	}

// Metodi di supporto

	BillDto fromDaoToDto(Bill bill) {
		BillDto dto = new BillDto();
		dto.setAll(bill.getId(), bill.getPaymentMethod(), bill.getTotal());
		return dto;
	}

	Bill fromDtoToDao(BillDto billDto) {
		Bill dao;
		dao = setAllDaoParams(billDto.getId(), billDto.getPaymentMethod(), billDto.getTotal());
		return dao;
	}

	Bill setAllDaoParams(Integer id, String paymentMethod, Double total) {
		Bill dao = new Bill();
		if (isPositiveId(id))
			dao.setId(id);
		else
			dao.setId(0);
		dao.setPaymentMethod(paymentMethod);
		dao.setTotal(total);
		return dao;
	}

	Integer getIdFromBill(Bill bill) {
		return bill.getId();
	}

	Bill getBillFromId(Integer id) {
		return fromDtoToDao(getBillAsDto(id));
	}

	BillDto getBillUpdateWhithTotal(BillDto billDto, Double total) {
		billDto.setTotal(total);
		return billDto;
	}

	public Boolean isExistingId(Integer id) {
		if (id != null && billRepository.existsById(id))
			return true;
		return false;
	}

}
