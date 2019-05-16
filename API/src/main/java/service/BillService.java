package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Bill;
import dto.BillDto;
import dto.Total;
import repository.BillRepository;

@Service
public class BillService {

	@Autowired
	BillRepository localBill;
	@Autowired
	JobService jobService;

	public List<BillDto> getAllBillAsDtoList() {
		List<BillDto> listBillDto = new ArrayList<>();
		localBill.findAll().forEach(e -> {
			listBillDto.add(fromBillToDto(e));
		});
		return listBillDto;
	}

	public BillDto getBillAsDto(Integer id) {
		BillDto billDto = new BillDto();
		if (id != null && localBill.existsById(id))
		localBill.findById(id).ifPresent(e -> {
			billDto.setId(e.getId());
			billDto.setPaymentMethod(e.getPaymentMethod());
			billDto.setTotal(e.getTotal());
		});
		return billDto;
	}

	public Boolean deleteBill(Integer id) {
		Boolean test = false;
		if (id != null && localBill.existsById(id)) {
			localBill.deleteById(id);
			test = true;
		}
		return test;
	}

	public Bill saveBill(BillDto billDto) {
		Bill bill = new Bill();
		if (billDto.getId() != null && billDto.getId() > 0)
			bill.setId(billDto.getId());
		bill.setPaymentMethod(billDto.getPaymentMethod());
		bill.setTotal(billDto.getTotal());
		localBill.save(bill);
		return bill;
	}

	public Total setTotalById(Integer idBill, Double variation) {
		Total total = new Total();
		if (idBill != null && idBill > 0 & localBill.existsById(idBill))
			localBill.findById(idBill).ifPresent(e -> {
				jobService.localJob.findAllByIdBill(e.getId()).forEach(i -> {
					e.setTotal(e.getTotal() + i.getDish().getPrice());
				});
				if (variation != null && variation != 0)
					e.setTotal(e.getTotal() + variation);
				localBill.save(e);
			});
		return total;
	}

	public static BillDto fromBillToDto(Bill bill) {
		BillDto billDto = new BillDto();
		billDto.setId(bill.getId());
		billDto.setPaymentMethod(bill.getPaymentMethod());
		billDto.setTotal(bill.getTotal());
		return billDto;
	}

}
