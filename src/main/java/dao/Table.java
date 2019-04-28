package dao;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the table database table.
 * 
 */
@Entity
@NamedQuery(name="Table.findAll", query="SELECT t FROM Table t")
public class Table implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int size;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="table")
	private List<Job> jobs;

	public Table() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
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
		job.setTable(this);

		return job;
	}

	public Job removeJob(Job job) {
		getJobs().remove(job);
		job.setTable(null);

		return job;
	}

}