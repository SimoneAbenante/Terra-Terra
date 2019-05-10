package dto;

import dto.inter.InterfaceDto;

public class JobDto implements InterfaceDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2951026190344300972L;
	
	private Integer id;
	private Integer id_bill;
	private Integer id_diningTable;
	private Integer id_dish;
	private Integer id_status;

	public JobDto() {
		super();
		setId(0);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_bill() {
		return id_bill;
	}

	public void setId_bill(Integer id_bill) {
		this.id_bill = id_bill;
	}

	public Integer getId_diningTable() {
		return id_diningTable;
	}

	public void setId_diningTable(Integer id_table) {
		this.id_diningTable = id_table;
	}

	public Integer getId_dish() {
		return id_dish;
	}

	public void setId_dish(Integer id_dish) {
		this.id_dish = id_dish;
	}

	public Integer getId_status() {
		return id_status;
	}

	public void setId_status(Integer id_status) {
		this.id_status = id_status;
	}

}
