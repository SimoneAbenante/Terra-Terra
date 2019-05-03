package dao;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the job database table.
 * 
 */
@Entity
@NamedQuery(name="Job.findAll", query="SELECT j FROM Job j")
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Byte done;

	//bi-directional many-to-one association to Bill
	@ManyToOne
	@JoinColumn(name="id_bill")
	private Bill bill;

	//bi-directional many-to-one association to DiningTable
	@ManyToOne
	@JoinColumn(name="id_table")
	private DiningTable diningTable;

	//bi-directional many-to-one association to Dish
	@ManyToOne
	@JoinColumn(name="id_dish")
	private Dish dish;

	public Job() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte getDone() {
		return this.done;
	}

	public void setDone(Byte done) {
		this.done = done;
	}

	public Bill getBill() {
		return this.bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public DiningTable getDiningTable() {
		return this.diningTable;
	}

	public void setDiningTable(DiningTable diningTable) {
		this.diningTable = diningTable;
	}

	public Dish getDish() {
		return this.dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

}