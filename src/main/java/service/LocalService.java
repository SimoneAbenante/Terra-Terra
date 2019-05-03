package service;

import static dto.JobDto.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.*;
import dto.*;
import dto.inter.InterfaceDto;
import rep.*;
import service.enu.LocalEnum;

@Service
public class LocalService {

	@Autowired
	BillRepository localBill;
	@Autowired
	DishRepository localDish;
	@Autowired
	JobRepository localJob;
	@Autowired
	TableRepository localTable;

	public List<InterfaceDto> getAllDto(LocalEnum local) {
		final List<InterfaceDto> ListDto = new ArrayList<>();
		switch (local) {
		case BILL:
			localBill.findAll().forEach(e -> ListDto.add(new BillDto(e.getId(), e.getPaymentMethod(), e.getTotal())));
			break;
		case DISH:
			localDish.findAll().forEach(e -> ListDto.add(new DishDto(e.getId(), e.getName(), e.getPrice())));
			break;
		case JOB:
			localJob.findAll().forEach(e -> ListDto.add(new JobDto(e.getId(), fromBilltoDto(e.getBill()),
					fromDiningTableToDto(e.getDiningTable()), fromDishToDto(e.getDish()), biteToBoolean(e.getDone()))));
			break;
		case TABLE:
			localTable.findAll().forEach(e -> ListDto.add(new TableDto(e.getId(), e.getSize())));
			break;
		default:
			ListDto.clear();
			break;
		}
		return ListDto;
	}

	// da testare
	public InterfaceDto getDto(Integer id, LocalEnum local) {
		InterfaceDto dto;
		switch (local) {
		case BILL:
			Bill bill = new Bill();
			localBill.findById(id).get();
			dto = new BillDto(bill.getId(), bill.getPaymentMethod(), bill.getTotal());
			break;
		case DISH:
			Dish dish = new Dish();
			localDish.findById(id).get();
			dto = new DishDto(dish.getId(), dish.getName(), dish.getPrice());
			break;
		case JOB:
			Job job = new Job();
			localJob.findById(id).get();
			dto = (new JobDto(job.getId(), fromBilltoDto(job.getBill()), fromDiningTableToDto(job.getDiningTable()),
					fromDishToDto(job.getDish()), biteToBoolean(job.getDone())));
			break;
		case TABLE:
			DiningTable table = new DiningTable();
			localTable.findById(id).get();
			dto = new TableDto(table.getId(), table.getSize());
			break;
		default:
			dto = null;
			break;
		}
		return dto;
	}

	public Boolean deleteDto(Integer id, LocalEnum local) {
		Boolean test;
		switch (local) {
		case BILL:
			localBill.deleteById(id);
			test = true;
			break;
		case DISH:
			localDish.deleteById(id);
			test = true;
			break;
		case JOB:
			localJob.deleteById(id);
			test = true;
			break;
		case TABLE:
			localTable.deleteById(id);
			test = true;
			break;
		default:
			test = false;
			break;
		}
		return test;
	}

	public Serializable saveDto(InterfaceDto dto, LocalEnum local) {
		switch (local) {
		case BILL:
			BillDto billDto = (BillDto) dto;
			Bill bill = new Bill();
			bill.setPaymentMethod(billDto.getPaymentMethod());
			bill.setTotal(billDto.getTotal());
			localBill.save(bill);
			return bill;
		case DISH:
			DishDto dishDto = (DishDto) dto;
			Dish dish = new Dish();
			dish.setName(dishDto.getName());
			dish.setPrice(dishDto.getPrice());
			localDish.save(dish);
			return dish;
		case JOB:
			JobDto jobDto = (JobDto) dto;
			Job job = new Job();
			
			job.setBill(new Bill());
			job.getBill().setId(jobDto.getBill().getId());
			job.getBill().setPaymentMethod(jobDto.getBill().getPaymentMethod());
			job.getBill().setTotal(jobDto.getBill().getTotal());
			
			job.setDiningTable(new DiningTable());
			job.getDiningTable().setId(jobDto.getTable().getId());
			job.getDiningTable().setSize(jobDto.getTable().getSize());
			
			job.setDish(new Dish());
			job.getDish().setId(jobDto.getDish().getId());
			job.getDish().setName(jobDto.getDish().getName());
			job.getDish().setPrice(jobDto.getDish().getPrice());
			
			job.setDone((byte)0);
			
			localJob.save(job);
			return job;
		case TABLE:
			TableDto tableDto = (TableDto) dto;
			DiningTable table = new DiningTable();
			table.setSize(tableDto.getSize());
			localTable.save(table);
			return table;
		default:
			return null;
		}
	}

	public InterfaceDto createNewDto(LocalEnum local) {
		InterfaceDto dto;
		switch (local) {
		case BILL:
			dto = new BillDto();
			break;
		case DISH:
			dto = new DishDto();
			break;
		case JOB:
			dto = new JobDto();
			break;
		case TABLE:
			dto = new TableDto();
			break;
		default:
			dto = null;
			break;
		}
		saveDto(dto, local);
		return dto;
	}

}
