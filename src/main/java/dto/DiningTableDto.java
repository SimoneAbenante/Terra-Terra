package dto;

import dao.DiningTable;
import dto.inter.InterfaceDto;

public class DiningTableDto implements InterfaceDto {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -5201848931832508311L;
	private Integer id;
	private Integer size;
	
	public DiningTableDto() {
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
	
	public static DiningTable fromDtoToDiningTable(DiningTableDto diningTableDto) {
		DiningTable table = new DiningTable();
		table.setId(diningTableDto.getId());
		table.setSize(diningTableDto.getSize());
		return table;
	}
	
	public static DiningTableDto fromDiningTableToDto(DiningTable table) {
		DiningTableDto diningTableDto = new DiningTableDto();
		diningTableDto.setId(table.getId());
		diningTableDto.setSize(table.getSize());
		return diningTableDto;
	}
	
}
