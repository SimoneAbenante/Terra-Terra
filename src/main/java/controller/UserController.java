package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

	private final String view = "/view/";

	//User Page
	
	@GetMapping(value = "/cooker", produces = "text/html")
	public String cooker() {
		return view + "cooker";
	}
	

	@GetMapping(value = "/costomer", produces = "text/html")
	public String costomer() {
		return view + "costomer";
	}
	

	@GetMapping(value = "/manager", produces = "text/html")
	public String manager() {
		return view + "manager";
	}
	

	@GetMapping(value = "/waiter", produces = "text/html")
	public String waiter() {
		return view + "waiter";
	}

	// Find All
	
}
