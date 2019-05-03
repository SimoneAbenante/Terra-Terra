package controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.BillDto;
import dto.inter.InterfaceDto;
import service.LocalService;
import service.enu.LocalEnum;

@RequestMapping("/bill")
@RestController
public class BillController {
	
	@Autowired
	public LocalService localService;

	@GetMapping(value = "/get", produces = "application/json")
	public List<InterfaceDto> getAllBill() {
		return localService.getAllDto(LocalEnum.BILL);
	}
	
	@PostMapping(value = "/set", produces = "application/json")
	public Serializable saveBill(@RequestBody BillDto dto) {
		return localService.saveDto(dto, LocalEnum.BILL);
	}
	
	@DeleteMapping(value = "/delete", produces = "application/json")
	public Boolean deleteBill(@RequestBody Integer id) {
		return localService.deleteDto(id, LocalEnum.BILL);
	}
	
}
