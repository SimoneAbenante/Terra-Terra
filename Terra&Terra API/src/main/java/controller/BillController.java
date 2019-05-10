package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dao.Bill;
import dto.BillDto;
import service.BillService;

@RequestMapping("/bill")
@RestController
public class BillController {
	
	@Autowired
	public BillService billservice;

	@GetMapping(value = "/get", produces = "application/json")
	public List<BillDto> getAllBill() {
		return billservice.getAllBillAsDtoList();
	}
	
	@PostMapping(value = "/set", produces = "application/json")
	public Bill saveBill(@RequestBody BillDto dto) {
		return billservice.saveBill(dto);
	}
	
	@DeleteMapping(value = "/delete", produces = "application/json")
	public Boolean deleteBill(@RequestParam Integer id) {
		return billservice.deleteBill(id);
	}
	
}
