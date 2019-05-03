package dto;

import dto.inter.InterfaceDto;

public class DishDto implements InterfaceDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9062991264193651582L;
	private Integer id;
	private String name;
	private Double price;
	
	public DishDto() {
	}
	
	public DishDto(String name, Double price) {
		this.name = name;
		this.price = price;
	}

	public DishDto(Integer id, String name, Double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
