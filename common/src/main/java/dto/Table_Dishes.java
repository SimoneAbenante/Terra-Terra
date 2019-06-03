package dto;

import java.util.ArrayList;
import java.util.List;

import dto.inter.InterfaceDto;
import exception.LocalException;

@Deprecated
public class Table_Dishes implements InterfaceDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5308201633252560079L;

	private Integer id;
	private List<Integer> dishes;

	public Table_Dishes() {
		super();
		setId(defaultId);
		this.dishes = new ArrayList<>();
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		try {
			if (isValidInteger(id))
				this.id = id;
		} catch (LocalException ex) {
			this.id = defaultId;
			ex.setMessage(setFailMessage);
			ex.getMessage();
			ex.getStackTrace();
		}
	}

	public List<Integer> getDishes() {
		return dishes;
	}

	public void setDishes(List<Integer> dishes) {
		try {
			if (isValidList(dishes))
				this.dishes = dishes;
		} catch (LocalException ex) {
			this.dishes = defaultList;
			ex.setMessage(setFailMessage);
			ex.getMessage();
			ex.getStackTrace();
		}
	}

}
