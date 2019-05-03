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

import dto.DishDto;
import dto.inter.InterfaceDto;
import service.LocalService;
import service.enu.LocalEnum;


@RequestMapping("/dish")
@RestController
public class DishController {
	
	@Autowired
	public LocalService localService;

	@GetMapping(value = "/get", produces = "application/json")
	public List<InterfaceDto> getAllDish() {
		return localService.getAllDto(LocalEnum.DISH);
	}
	
	@PostMapping(value = "/set", produces = "application/json")
	public Serializable saveDish(@RequestBody DishDto dto) {
		return localService.saveDto(dto, LocalEnum.DISH);
	}
	
	@DeleteMapping(value = "/delete", produces = "application/json")
	public Boolean deletDish(@RequestBody Integer id) {
		return localService.deleteDto(id, LocalEnum.DISH);
	}
	
	
	
}
