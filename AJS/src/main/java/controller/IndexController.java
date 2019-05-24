package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.DishDto;
import feign.DishFeign;

@Controller
public class IndexController {
	
	@Autowired
	private DishFeign dishClient;
	 
	@GetMapping(value="/dishes")
	public List<DishDto> getAllDish() {
		return dishClient.getAllDish();
	}
}
