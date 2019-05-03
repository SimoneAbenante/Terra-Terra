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

import dto.TableDto;
import dto.inter.InterfaceDto;
import service.LocalService;
import service.enu.LocalEnum;


@RequestMapping("/table")
@RestController
public class TableController {
	
	@Autowired
	public LocalService localService;
	
	@GetMapping(value = "/get", produces = "application/json")
	public List<InterfaceDto> getAllTable() {
		return localService.getAllDto(LocalEnum.TABLE);
	}
	
	@PostMapping(value = "/set", produces = "application/json")
	public Serializable saveTable(@RequestBody TableDto dto) {
		return localService.saveDto(dto, LocalEnum.TABLE);
	}
	
	@DeleteMapping(value = "/delete", produces = "application/json")
	public Boolean deleteTable(@RequestBody Integer id) {
		return localService.deleteDto(id, LocalEnum.TABLE);
	}

}
