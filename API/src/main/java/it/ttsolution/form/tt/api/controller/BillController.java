package it.ttsolution.form.tt.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.BillDto;
import it.ttsolution.form.tt.api.service.BillService;

@RequestMapping("/bills")
@RestController
public class BillController {
	
	@Autowired
	public BillService billService;

	@GetMapping(value = "/", produces = "application/json")
	public List<BillDto> getAllBill() {
		return billService.getAllBillsAsDtoList();
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public BillDto getBillById(@PathVariable Integer id) {
		return billService.getBillAsDto(id);
	}
	
	@PostMapping(value = "/", produces = "application/json")
	public BillDto saveBill(@RequestBody BillDto dto) {
		return billService.saveBill(dto);
	}
	
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public Boolean deleteBill(@PathVariable Integer id) {
		return billService.deleteBill(id);
	}
	
}
