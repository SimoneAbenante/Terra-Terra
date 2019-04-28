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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private byte done;

	//bi-directional many-to-one association to Bill
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_bill")
	private Bill bill;

	//bi-directional many-to-one association to Dish
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="`id-dish`")
	private Dish dish;

	//bi-directional many-to-one association to Table
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_table")
	private Table table;

	public Job() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getDone() {
		return this.done;
	}

	public void setDone(byte done) {
		this.done = done;
	}

	public Bill getBill() {
		return this.bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Dish getDish() {
		return this.dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public Table getTable() {
		return this.table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

}