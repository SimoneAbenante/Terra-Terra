package dto;

import dto.inter.InterfaceDto;

public class TableDto implements InterfaceDto {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -5201848931832508311L;
	private int id;
	private int size;
	
	public TableDto() {
	}
	
	public TableDto(int size) {
		this.size = size;
	}
	
	public TableDto(int id, int size) {
		this.id = id;
		this.size = size;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
