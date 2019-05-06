package dto;

import dto.inter.InterfaceDto;

public class DiningTableDto implements InterfaceDto {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -5201848931832508311L;
	private Integer id;
	private Integer size;
	private Integer status;

	public DiningTableDto() {
		super();
		setId(0);
	}
	
	public DiningTableDto(Integer size) {
		this.size = size;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
