package dto;

import dto.inter.InterfaceDto;

public class StatusDto implements InterfaceDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6734101703446299791L;
	
	private Integer Id;
	private String status;
	
	public StatusDto() {
		super();
		setId(0);
	}
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
