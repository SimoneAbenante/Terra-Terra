package dto;

import dto.inter.InterfaceDto;

public class JobDto implements InterfaceDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2951026190344300972L;
	private Integer id;

	private BillDto bill;

	private DiningTableDto diningTable;

	private DishDto dish;

	private Boolean done;

	public JobDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BillDto getBill() {
		return bill;
	}

	public void setBill(BillDto bill) {
		this.bill = bill;
	}

	public DiningTableDto getDiningTable() {
		return diningTable;
	}

	public void setDiningTable(DiningTableDto table) {
		this.diningTable = table;
	}

	public DishDto getDish() {
		return dish;
	}

	public void setDish(DishDto dish) {
		this.dish = dish;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public static boolean fromByteToBoolean(Byte b) {
		return b > 0 ? true : false;
	}
	
	public static Byte fromBooleanToByte(Boolean b) {
		if(b.booleanValue()) return 1;
		else return 0;
	}

}
