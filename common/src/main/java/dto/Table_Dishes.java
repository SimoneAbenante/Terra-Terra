package dto;

import java.util.ArrayList;
import java.util.List;

import dto.inter.InterfaceDto;
import exception.LocalException;

public class Table_Dishes implements InterfaceDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5308201633252560079L;

	private Integer idDiningTable;
	private List<Integer> dishes;

	public Table_Dishes() {
		super();
		setIdDiningTable(defaultId);
		this.dishes = new ArrayList<>();
	}

	public Integer getIdDiningTable() {
		return idDiningTable;
	}

	public void setIdDiningTable(Integer idDiningTable) {
		try {
			if (isValidInteger(idDiningTable))
				this.idDiningTable = idDiningTable;
		} catch (LocalException ex) {
			this.idDiningTable = defaultId;
			ex.setMessage(setFailMessage);
			ex.getMessage();
			ex.getStackTrace();
		}
	}

	public List<Integer> getDishes() {
		return dishes;
	}

	public void setDishes(List<Integer> dishes) {
		this.dishes = dishes;
	}

	@Override
	public Integer getId() {
		return 0;
	}

}
