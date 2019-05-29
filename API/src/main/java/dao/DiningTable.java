package dao;

import javax.persistence.*;


/**
 * The persistent class for the dining_table database table.
 * 
 */
@Entity
@Table(name="dining_table")
@NamedQuery(name="DiningTable.findAll", query="SELECT d FROM DiningTable d")
public class DiningTable implements dao.interfaces.InterfaceDao {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Integer size;
	
	@ManyToOne
	@JoinColumn(name="id_status")
	private Status status;

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}