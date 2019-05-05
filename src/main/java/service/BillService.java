package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Bill;
import dto.BillDto;
import rep.BillRepository;

@Service
public class BillService {

	@Autowired
	BillRepository localBill;

	public List<BillDto> getAllBillAsDtoList() {
		List<BillDto> listBillDto = new ArrayList<>();
		BillDto billDto = new BillDto();
		localBill.findAll().forEach(e -> {
			billDto.setId(e.getId());
			billDto.setPaymentMethod(e.getPaymentMethod());
			billDto.setTotal(e.getTotal());
			listBillDto.add(billDto);
		});
		return listBillDto;
	}

	public BillDto getBillAsDto(Integer id) {
		BillDto billDto = new BillDto();
		localBill.findById(id).ifPresent(e -> {
			billDto.setId(e.getId());
			billDto.setPaymentMethod(e.getPaymentMethod());
			billDto.setTotal(e.getTotal());
		});
		return billDto;
	}

	public Boolean deleteBill(Integer id) {
		Boolean test = false;
		localBill.deleteById(id);
		test = true;
		return test;
	}

	public Bill saveBill(BillDto billDto) {
		Bill bill = new Bill();
		if (billDto.getId() > 0)
			bill.setId(billDto.getId());
		bill.setPaymentMethod(billDto.getPaymentMethod());
		bill.setTotal(billDto.getTotal());
		localBill.save(bill);
		return bill;
	}

}
