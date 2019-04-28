package dto;

public class JobDto {

	private int id;
	private int id_bill;
	private int id_table;
	private int id_dish;
	private boolean done;

	public JobDto(int id, int id_bill, int id_table, int id_dish, boolean done) {
		this.id = id;
		this.id_bill = id_bill;
		this.id_table = id_table;
		this.id_dish = id_dish;
		this.done = done;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_bill() {
		return id_bill;
	}

	public void setId_bill(int id_bill) {
		this.id_bill = id_bill;
	}

	public int getId_table() {
		return id_table;
	}

	public void setId_table(int id_table) {
		this.id_table = id_table;
	}

	public int getId_dish() {
		return id_dish;
	}

	public void setId_dish(int id_dish) {
		this.id_dish = id_dish;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
}
