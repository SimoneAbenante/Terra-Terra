package dto;

import dao.*;
import dto.inter.InterfaceDto;

public class JobDto implements InterfaceDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2951026190344300972L;
	private int id;
	private BillDto bill;
	private TableDto table;
	private DishDto dish;
	private boolean done;

	public JobDto() {
	}
	
	public JobDto(BillDto bill, TableDto table, DishDto dish, boolean done) {
		this.bill = bill;
		this.table = table;
		this.dish = dish;
		this.done = done;
	}

	public JobDto(int id, BillDto bill, TableDto table, DishDto dish, boolean done) {
		this.id = id;
		this.bill = bill;
		this.table = table;
		this.dish = dish;
		this.done = done;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BillDto getBill() {
		return bill;
	}

	public void setBill(BillDto bill) {
		this.bill = bill;
	}

	public TableDto getTable() {
		return table;
	}

	public void setTable(TableDto table) {
		this.table = table;
	}

	public DishDto getDish() {
		return dish;
	}

	public void setDish(DishDto dish) {
		this.dish = dish;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public static BillDto fromBilltoDto(Bill bill) {
		return new BillDto(bill.getId(), bill.getPaymentMethod(), bill.getTotal());
	}
	
	public static DishDto fromDishToDto(Dish dish) {
		return new DishDto(dish.getId(), dish.getName(), dish.getPrice());
	}
	
	public static TableDto fromDiningTableToDto(DiningTable table) {
		return new TableDto(table.getId(), table.getSize());
	}
	
	public static boolean biteToBoolean(byte b) {
		return b>0?true:false;
	}

}
