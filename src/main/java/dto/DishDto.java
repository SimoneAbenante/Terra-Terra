package dto;

import dao.Dish;
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
	
	public static Dish fromDtoToDish(DishDto dishDto) {
		Dish dish = new Dish();
		dish.setId(dishDto.getId());
		dish.setName(dishDto.getName());
		dish.setPrice(dishDto.getPrice());
		return dish;
	}
	
	public static DishDto fromDishToDto(Dish dish) {
		DishDto dishDto = new DishDto();
		dishDto.setId(dish.getId());
		dishDto.setName(dish.getName());
		dishDto.setPrice(dish.getPrice());
		return dishDto;
	}
	
}
