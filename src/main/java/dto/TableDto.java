package dto;

import dto.inter.InterfaceDto;

public class TableDto implements InterfaceDto {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -5201848931832508311L;
	private Integer id;
	private Integer size;
	
	public TableDto() {
	}
	
	public TableDto(Integer size) {
		this.size = size;
	}
	
	public TableDto(Integer id, Integer size) {
		this.id = id;
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
}
