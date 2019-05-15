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
		super();
		setId(0);
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
