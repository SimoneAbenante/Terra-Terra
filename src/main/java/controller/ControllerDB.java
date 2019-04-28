package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.*;
import dto.*;
import rep.*;

@Controller
public class ControllerDB {

	BillRepository bill;
	
	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "test";
	}

	@RequestMapping("/findallbill")
	@ResponseBody
	public Iterable<Bill> findAll() {
		return bill.findAll();
	}

}
