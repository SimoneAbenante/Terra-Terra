package dao;

import javax.persistence.*;

import dao.inter.InterfaceDao;

import java.util.List;


/**
 * The persistent class for the dish database table.
 * 
 */
@Entity
@NamedQuery(name="Dish.findAll", query="SELECT d FROM Dish d")
public class Dish implements InterfaceDao {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private Double price;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="dish")
	private List<Job> jobs;

	public Dish() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<Job> getJobs() {
		return this.jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Job addJob(Job job) {
		getJobs().add(job);
		job.setDish(this);

		return job;
	}

	public Job removeJob(Job job) {
		getJobs().remove(job);
		job.setDish(null);

		return job;
	}

}