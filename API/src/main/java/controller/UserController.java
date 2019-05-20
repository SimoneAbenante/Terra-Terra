package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import user.*;

@RequestMapping("/users")
@Controller
public class UserController {

	private final String view = "/view/";

	private final String users = Manager.class.getCanonicalName() + "\n" + Cooker.class.getCanonicalName() + "\n"
			+ Waiter.class.getCanonicalName() + "\n" + Customer.class.getCanonicalName() + "\n";

	// User Page

	@RequestMapping("")
	@ResponseBody
	public String getAllUser() {
		return users;
	}

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

}
