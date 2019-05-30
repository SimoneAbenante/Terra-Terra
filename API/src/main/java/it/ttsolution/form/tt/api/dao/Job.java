package it.ttsolution.form.tt.api.dao;

import javax.persistence.*;


/**
 * The persistent class for the job database table.
 * 
 */
@Entity
@NamedQuery(name="Job.findAll", query="SELECT j FROM Job j")
public class Job implements it.ttsolution.form.tt.api.dao.interfaces.InterfaceDao {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

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

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="id_status")
	private Status status;

	public Job() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}