package dao;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dining_table database table.
 * 
 */
@Entity
@Table(name="dining_table")
@NamedQuery(name="DiningTable.findAll", query="SELECT d FROM DiningTable d")
public class DiningTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Integer size;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="diningTable")
	private List<Job> jobs;

	public DiningTable() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSize() {
		return this.size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public List<Job> getJobs() {
		return this.jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Job addJob(Job job) {
		getJobs().add(job);
		job.setDiningTable(this);

		return job;
	}

	public Job removeJob(Job job) {
		getJobs().remove(job);
		job.setDiningTable(null);

		return job;
	}

}