package dao;

import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the status database table.
 * 
 */
@Entity
@NamedQuery(name="Status.findAll", query="SELECT s FROM Status s")
public class Status implements dao.inter.InterfaceDao {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String status;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="status")
	private List<Job> jobs;

	public Status() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Job> getJobs() {
		return this.jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Job addJob(Job job) {
		getJobs().add(job);
		job.setStatus(this);

		return job;
	}

	public Job removeJob(Job job) {
		getJobs().remove(job);
		job.setStatus(null);

		return job;
	}

}