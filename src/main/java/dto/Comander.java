package dto;

import java.util.List;

import dto.inter.InterfaceDto;
import exception.LocalException;

public class Comander implements InterfaceDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1257160953425579402L;
	private BillDto bill;
	private List<JobDto> jobList;
	private Boolean status;

	public Comander() {
		super();
	}

	public Comander(BillDto bill, List<JobDto> jobList) throws LocalException {
		if (this.testList(bill.getId(), jobList)) {
			this.bill = bill;
			this.jobList = jobList;
			this.status = false;
		} else {
			throw new LocalException("The list of Jobs don't match whit bill id");
		}
	}

	public BillDto getBill() {
		return bill;
	}

	public void setBill(BillDto bill) {
		this.bill = bill;
	}

	public List<JobDto> getJobList() {
		return jobList;
	}

	public void setJobList(List<JobDto> jobList) {
		this.jobList = jobList;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	private boolean testList(int id, List<JobDto> jobList) {
		for (JobDto job : jobList) {
			if (id != job.getBill().getId())
				return false;
		}
		return true;
	}

	@Override
	public Integer getId() {
		return bill.getId();
	}
}


