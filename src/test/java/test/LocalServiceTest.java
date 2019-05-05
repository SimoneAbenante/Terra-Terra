package test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import dao.*;
import dto.*;
import rep.BillRepository;
import rep.DishRepository;
import rep.JobRepository;
import rep.TableRepository;
import service.enu.LocalEnum;

@RunWith(SpringRunner.class)
public class LocalServiceTest {
	
//	LocalService localservice;
//	
//	@Before
//	public void init() {
//		localservice = new LocalService();
//	}
//	
//	@MockBean
//    public BillRepository billRepository;
//	@MockBean
//    public DishRepository dishRepository;
//	@MockBean
//    public TableRepository tableRepository;
//	@MockBean
//    public JobRepository jobRepository;
//
//	@Test
//	public void saveTableToDB() {
//		TableDto obj = new TableDto();
//		obj.setSize(2);
//		
//		DiningTable test = (DiningTable)localservice.saveDto(obj, LocalEnum.TABLE);
//
//		assertThat(test).isEqualTo(true);
//	}
//	
//	@Test
//	public void saveBillToDB() {
//		BillDto obj = new BillDto();
//		obj.setPaymentMethod("card");
//		obj.setTotal(0);
//		
//		Bill test = (Bill)localservice.saveDto(obj, LocalEnum.BILL);
//
//		assertThat(test).isEqualTo(true);
//	}
//	
//	@Test
//	public void saveDishToDB() {
//		DishDto obj = new DishDto();
//		obj.setName("Polpetta");
//		obj.setPrice(8.5);
//		
//		Dish test = (Dish)localservice.saveDto(obj, LocalEnum.DISH);
//
//		assertThat(test).isEqualTo(true);
//	}
//	
////	@Test
////	public void saveJobToDB() {
////		JobDto obj = new JobDto();
////		
////		BillDto bill = new BillDto();
////		bill.setId(3);
////		bill.setPaymentMethod("contanti");
////		bill.setTotal(100);
////		
////		TableDto table = new TableDto();
////		table.setId(2);
////		table.setSize(2);
////		
////		DishDto dish = new DishDto();
////		dish.setId(4);
////		dish.setName("Salsiccia");
////		dish.setPrice(8);
////		
////		obj.setBill(bill);
////		obj.setTable(table);
////		obj.setDish(dish);
////		
////		Job test = (Job)localservice.saveDto(obj, LocalEnum.JOB);
////
////		assertThat(test).isEqualTo(true);
////	}
//
}
