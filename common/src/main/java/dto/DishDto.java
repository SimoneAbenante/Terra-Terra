package dto;

import dto.inter.InterfaceDto;
import exception.LocalException;

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
		setAll(defaultId, defaultString, defaultDouble);
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		try {
			if (isValidInteger(id))
				this.id = id;
		} catch (LocalException e) {
			this.id = defaultId;
			e.setMessage(setFailMessage);
			e.getMessage();
			e.getStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		try {
			if (isValidString(name))
				this.name = name;
		} catch (LocalException e) {
			this.name = defaultString;
			e.setMessage(setFailMessage);
			e.getMessage();
			e.getStackTrace();
		}
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		try {
			if (isValidDouble(price))
				this.price = price;
		} catch (LocalException e) {
			this.price = defaultDouble;
			e.setMessage(setFailMessage);
			e.getMessage();
			e.getStackTrace();
		}
	}

	public void setAll(String name, Double price) {
		setName(name);
		setPrice(price);
	}

	public void setAll(Integer id, String name, Double price) {
		setId(id);
		setName(name);
		setPrice(price);
	}

}
