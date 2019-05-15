package dto;

import java.util.List;

public class Bill_Dishes{
	
	private Integer idDiningTable;
	private List<Integer> dishes;
	
	public Integer getIdDiningTable() {
		return idDiningTable;
	}
	public void setIdDiningTable(Integer idDiningTable) {
		this.idDiningTable = idDiningTable;
	}
	public List<Integer> getDishes() {
		return dishes;
	}
	public void setDishes(List<Integer> dishes) {
		this.dishes = dishes;
	}
	
}
